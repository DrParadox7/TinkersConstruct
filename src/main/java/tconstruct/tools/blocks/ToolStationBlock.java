package tconstruct.tools.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mantle.blocks.abstracts.InventoryBlock;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.tools.ToolProxyCommon;
import tconstruct.tools.logic.ToolStationLogic;
import tconstruct.tools.model.TableRender;
import tconstruct.world.TinkerWorld;

public class ToolStationBlock extends InventoryBlock {

    public ToolStationBlock(Material material) {
        super(material);
        this.setCreativeTab(TConstructRegistry.blockTab);
        this.setHardness(2f);
        this.setStepSound(Block.soundTypeMetal);
    }

    // We keep it "toolforge" for the sake of texture pack support.
    String[] textureNames = { "toolforge_bronze", "toolforge_steel", "toolforge_alumite" };

    @Override
    public String[] getTextureNames() {
        return textureNames;
    }

    @Override
    public String getTextureDomain(int textureNameIndex) {
        return "tinker";
    }

    IIcon textureTop;

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        textureTop = iconRegister.registerIcon("tinker:toolforge_top");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return textureTop;
        }
        if (side == 0) {
            switch (meta) {
                case 1:
                    return TinkerWorld.metalBlock.getIcon(side, 9);
                case 2:
                    return TinkerWorld.metalBlock.getIcon(side, 8);
                default:
                    return TinkerWorld.metalBlock.getIcon(side, 4);
            }
        }
        if (meta <= textureNames.length - 1) return this.icons[meta];

        return this.icons[0];
    }

    public int getTextureIndex(int side) {
        if (side == 0) return 2;
        if (side == 1) return 0;

        return 1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.UP;
    }

    @Override
    public int getRenderType() {
        return TableRender.model;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new ToolStationLogic();
    }

    @Override
    public Integer getGui(World world, int x, int y, int z, EntityPlayer entityplayer) {
        return ToolProxyCommon.toolStationID;
    }

    @Override
    public Object getModInstance() {
        return TConstruct.instance;
    }

    @Override
    public void getSubBlocks(Item b, CreativeTabs tab, List list) {
        for (int iter = 0; iter < textureNames.length; iter++) {
            list.add(new ItemStack(b, 1, iter));
        }
    }
}
