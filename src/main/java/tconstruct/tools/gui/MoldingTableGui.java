package tconstruct.tools.gui;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;
import cpw.mods.fml.common.Optional;
import tconstruct.TConstruct;
import tconstruct.library.client.MoldGuiElement;
import tconstruct.library.client.TConstructClientRegistry;
import tconstruct.library.crafting.MoldBuilder;
import tconstruct.tools.TinkerTools;
import tconstruct.tools.inventory.MoldingTableContainer;
import tconstruct.tools.logic.MoldingTableLogic;
import tconstruct.util.network.MoldingTablePacket;
import tconstruct.weaponry.TinkerWeaponry;

@Optional.Interface(iface = "codechicken.nei.api.INEIGuiHandler", modid = "NotEnoughItems")
public class MoldingTableGui extends GuiContainer implements INEIGuiHandler {

    int[] buttonsLeftRect = new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };
    int[] buttonsRightRect = new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE };
    MoldingTableLogic logic;
    int activeButton;

    public MoldingTableGui(InventoryPlayer inventoryplayer, MoldingTableLogic molder, World world, int x, int y,
            int z) {
        super(new MoldingTableContainer(inventoryplayer, molder));
        logic = molder;
        activeButton = 0;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString(StatCollector.translateToLocal("crafters.MoldingTable"), 50, 6, 0x404040);
        fontRendererObj
                .drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
    }

    private static final ResourceLocation background = new ResourceLocation("tinker", "textures/gui/patternshaper.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int cornerX = (this.width - this.xSize) / 2;
        int cornerY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(cornerX, cornerY, 0, 0, this.xSize, this.ySize);
        if (!logic.isStackInSlot(0)) {
            this.drawTexturedModalRect(cornerX + 47, cornerY + 34, 176, 0, 18, 18);
        }
    }

    @Override
    public void initGui() {
        super.initGui();

        int bpr = 4; // buttons per row!
        int cornerX = this.guiLeft - 22 * bpr;
        int cornerY = this.guiTop + 2;

        this.buttonList.clear();
        this.buttonsLeftRect = new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };
        this.buttonsRightRect = new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE };

        int id = 0;
        for (int iter = 0; iter < TConstructClientRegistry.moldButtons.size(); iter++) {
            MoldGuiElement element = TConstructClientRegistry.moldButtons.get(iter);
            if (element.moldIndex == -1) continue;
            GuiButtonMold button = new GuiButtonMold(
                    id++,
                    cornerX + 22 * (iter % bpr),
                    cornerY + 22 * (iter / bpr),
                    element.buttonIconX,
                    element.buttonIconY,
                    element.domain,
                    element.texture,
                    element);
            this.buttonList.add(button);
            this.buttonsLeftRect[0] = Math.min(button.xPosition, this.buttonsLeftRect[0]);
            this.buttonsLeftRect[1] = Math.max(button.yPosition + button.height, this.buttonsLeftRect[1]);
        }

        // secondary buttons, yay!
        // these are to use for other mods :I
        cornerX = this.guiLeft + this.xSize + 4;
        for (int iter = 0; iter < TConstructClientRegistry.moldButtons2.size(); iter++) {
            MoldGuiElement element = TConstructClientRegistry.moldButtons2.get(iter);
            if (element.moldIndex == -1) continue;
            GuiButtonMold button = new GuiButtonMold(
                    id++,
                    cornerX + 22 * (iter % bpr),
                    cornerY + 22 * (iter / bpr),
                    element.buttonIconX,
                    element.buttonIconY,
                    element.domain,
                    element.texture,
                    element);
            this.buttonList.add(button);
            this.buttonsRightRect[0] = Math.max(button.xPosition + button.width, this.buttonsRightRect[0]);
            this.buttonsRightRect[1] = Math.max(button.yPosition + button.height, this.buttonsRightRect[1]);
        }

        // get the correct setting :I
        ItemStack stack;
        ItemStack setting = logic.getStackInSlot(1);

        boolean isValidTemplate = setting != null
                && (setting.getItem() == TinkerTools.clayPattern || setting.getItem() == TinkerWeaponry.clayPattern);

        if (isValidTemplate) {
            activeButton = MoldBuilder.getId(setting);
            setActiveButton(activeButton);
            stack = MoldBuilder.getMold(((GuiButtonMold) this.buttonList.get(activeButton)).element.moldIndex);
        } else stack = null;

        logic.setSelectedMold(stack);
        updateServer(stack);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        ItemStack mold = logic.getStackInSlot(0);
        if (mold != null && MoldBuilder.isBlank(mold)) {
            int id = ((GuiButtonMold) button).element.moldIndex;
            ItemStack stack = MoldBuilder.getMold(id);
            if (stack != null) {
                logic.setSelectedMold(stack);
                updateServer(stack);
            }
        }

        setActiveButton(button.id);
    }

    private void setActiveButton(int id) {
        if (id > this.buttonList.size()) {
            TConstruct.logger.error(
                    "Invalid button ID for Molding Table. Requested = " + id + " | Max= " + this.buttonList.size());
            return;
        }
        // deactivate old button
        ((GuiButton) this.buttonList.get(activeButton)).enabled = true;
        // update active button
        activeButton = id;
        // activate the button
        ((GuiButton) this.buttonList.get(activeButton)).enabled = false;
    }

    void updateServer(ItemStack stack) {
        TConstruct.packetPipeline.sendToServer(new MoldingTablePacket(logic.xCoord, logic.yCoord, logic.zCoord, stack));
    }

    @Override
    public VisiblityData modifyVisiblity(GuiContainer guiContainer, VisiblityData visiblityData) {
        return visiblityData;
    }

    @Override
    public Iterable<Integer> getItemSpawnSlots(GuiContainer guiContainer, ItemStack itemStack) {
        return null;
    }

    @Override
    public List<TaggedInventoryArea> getInventoryAreas(GuiContainer guiContainer) {
        return Collections.emptyList();
    }

    @Override
    public boolean handleDragNDrop(GuiContainer guiContainer, int i, int i2, ItemStack itemStack, int i3) {
        return false;
    }

    @Override
    public boolean hideItemPanelSlot(GuiContainer guiContainer, int x, int y, int w, int h) {

        // is it in the horizontal column of the right buttons?
        if (x > this.guiLeft + this.xSize && x < this.buttonsRightRect[0]
                && y + h > this.guiTop
                && y < this.buttonsRightRect[1]) {
            return true;
        }

        // is it in the horizontal column of the left buttons?
        if (x + w > this.buttonsLeftRect[0] && x < this.guiLeft && y + h > this.guiTop && y < this.buttonsLeftRect[1]) {
            return true;
        }

        return false;
    }
}
