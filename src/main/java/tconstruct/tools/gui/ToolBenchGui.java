package tconstruct.tools.gui;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tconstruct.library.client.*;
import tconstruct.tools.logic.ToolBenchLogic;

@SideOnly(Side.CLIENT)
public class ToolBenchGui extends ToolStationGui
{
    int selectedButton;

    public ToolBenchGui(InventoryPlayer inventoryplayer, ToolBenchLogic stationlogic, World world, int x, int y, int z)
    {
        super(inventoryplayer, stationlogic, world, x, y, z);
    }

    @Override
    public void initGui ()
    {
        super.initGui();

        this.buttonList.clear();
        ToolGuiElement repair = TConstructClientRegistry.toolButtons.get(0);
        GuiButtonTool repairButton = new GuiButtonTool(0, this.guiLeft, this.guiTop, repair.buttonIconX, repair.buttonIconY, repair.domain, repair.texture, repair); // Repair
        repairButton.enabled = false;
        this.buttonList.add(repairButton);

        for (int iter = 1; iter < 6; iter++)
        {
            ToolGuiElement element = TConstructClientRegistry.toolButtons.get(iter);
            GuiButtonTool button = new GuiButtonTool(iter, this.guiLeft + 22 * (iter % 5), this.guiTop + 22 * (iter / 5), element.buttonIconX, element.buttonIconY, element.domain, element.texture, element);
            this.buttonList.add(button);
        }
    }

    @Override
    protected void actionPerformed (GuiButton button)
    {
        GuiButtonTool b = (GuiButtonTool) button;
        ((GuiButton) this.buttonList.get(selectedButton)).enabled = true;
        selectedButton = button.id;
        button.enabled = false;

        setSlotType(b.element.slotType);
        iconX = b.element.iconsX;
        iconY = b.element.iconsY;
        title = "\u00A7n" + b.element.title;
        body = StatCollector.translateToLocal(b.element.body);
        if(body != null) {
            int i;
            // for some really weird reason replaceAll doesn't find "\\n", but indexOf does. We have to replace manually.
            while((i = body.indexOf("\\n")) >= 0)
            {
                body = body.substring(0, i) + '\n' + body.substring(i+2);
            }
        }
    }

    @Override
    void resetGui ()
    {
        this.text.setText("");
        selectedButton = 0;
        setSlotType(0);
        iconX = new int[] { 0, 1, 2, 13 };
        iconY = new int[] { 13, 13, 13, 13 };
        title = "\u00A7n" + StatCollector.translateToLocal("gui.toolforge1");
        body = StatCollector.translateToLocal("gui.toolforge2");
    }

    @Override
    void setSlotType (int type)
    {
        switch (type)
        {
        case 0:
            slotX = new int[] { 56, 38, 38 }; // Repair
            slotY = new int[] { 37, 28, 46 };
            break;
        case 1:
            slotX = new int[] { 56, 56, 56 }; // Three parts
            slotY = new int[] { 19, 55, 37 };
            break;
        case 2:
            slotX = new int[] { 56, 56, 14 }; // Two parts
            slotY = new int[] { 28, 46, 37 };
            break;
        case 3:
            slotX = new int[] { 38, 47, 56 }; // Double head
            slotY = new int[] { 28, 46, 28 };
            break;
        case 4:
            slotX = new int[] { 47, 38, 56 }; // Four parts
            slotY = new int[] { 19, 37, 37 };
            break;
        case 5:
            slotX = new int[] { 38, 47, 56, 47 }; // Four parts, double head
            slotY = new int[] { 19, 55, 19, 37 };
            break;
        }
        toolSlots.resetSlots(slotX, slotY);
    }
}
