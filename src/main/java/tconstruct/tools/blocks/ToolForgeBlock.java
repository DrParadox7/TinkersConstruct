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

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mantle.blocks.abstracts.InventoryBlock;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.tools.TinkerTools;
import tconstruct.tools.ToolProxyCommon;
import tconstruct.tools.logic.ToolForgeLogic;
import tconstruct.tools.model.TableRender;
import tconstruct.world.TinkerWorld;

public class ToolForgeBlock extends InventoryBlock {

    public ToolForgeBlock(Material material) {
        super(material);
        this.setCreativeTab(TConstructRegistry.blockTab);
        this.setHardness(2f);
        this.setStepSound(Block.soundTypeMetal);
    }

    String[] textureNames = { "toolforge_manyullyn_nether" };

    /* Rendering */
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
        textureTop = iconRegister.registerIcon("tinker:toolforge_top_nether");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return textureTop;
        } else {
            if (!TinkerTools.metallurgyAvailable) {
                return TinkerWorld.metalBlock.getIcon(side, 2);
            } else {
                Block netherMetal = GameRegistry.findBlock("Metallurgy", "nether.block");
                Block fantasyMetal = GameRegistry.findBlock("Metallurgy", "fantasy.block");
                switch (meta) {
                    case 1:
                        return netherMetal.getIcon(side, 8); // Vulcanite
                    case 2:
                        return netherMetal.getIcon(side, 9); // Sanguinite
                    case 3:
                        return fantasyMetal.getIcon(side, 11); // Orichalcum
                    case 4:
                        return fantasyMetal.getIcon(side, 12); // Celenegil
                    case 5:
                        return fantasyMetal.getIcon(side, 13); // Adamantine
                    case 6:
                        return fantasyMetal.getIcon(side, 14); // Atlarus
                    case 7:
                        return fantasyMetal.getIcon(side, 15); // Tartarite

                    default:
                        return TinkerWorld.metalBlock.getIcon(side, 2);
                }
            }
        }
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
        return new ToolForgeLogic();
    }

    @Override
    public Integer getGui(World world, int x, int y, int z, EntityPlayer entityplayer) {
        return ToolProxyCommon.toolForgeID;
    }

    @Override
    public Object getModInstance() {
        return TConstruct.instance;
    }

    @Override
    public void getSubBlocks(Item b, CreativeTabs tab, List list) {
        if (TinkerTools.metallurgyAvailable) {
            for (int iter = 0; iter < 8; iter++) {
                list.add(new ItemStack(b, 1, iter));
            }
        } else {
            super.getSubBlocks(b, tab, list);
        }
    }
}
