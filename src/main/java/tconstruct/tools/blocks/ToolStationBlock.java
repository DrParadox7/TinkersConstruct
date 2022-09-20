package tconstruct.tools.blocks;

import cpw.mods.fml.relauncher.*;
import java.util.List;
import mantle.blocks.abstracts.InventoryBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.*;
import net.minecraftforge.common.util.ForgeDirection;
import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.tools.ToolProxyCommon;
import tconstruct.tools.logic.ToolStationLogic;
import tconstruct.tools.model.TableRender;
import tconstruct.world.TinkerWorld;

public class ToolStationBlock extends InventoryBlock
{

    public ToolStationBlock(Material material)
    {
        super(material);
        this.setCreativeTab(TConstructRegistry.blockTab);
        this.setHardness(2f);
        this.setStepSound(Block.soundTypeMetal);
    }

    @Override
    public String[] getTextureNames ()
    {
        String[] textureNames = { "toolstationnew_top", "toolstationnew_side", "toolstationnew_bottom" };

        return textureNames;
    }

    @Override
    public String getTextureDomain (int textureNameIndex)
    {
        return "tinker";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        return icons[getTextureIndex(side)];
    }

    public int getTextureIndex (int side)
    {
        if (side == 0)
            return 2;
        if (side == 1)
            return 0;

        return 1;
    }

    @Override
    public boolean renderAsNormalBlock ()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube ()
    {
        return false;
    }

    @Override
    public boolean isSideSolid (IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP;
    }

    @Override
    public int getRenderType ()
    {
        return TableRender.model;
    }

    @Override
    public boolean shouldSideBeRendered (IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity (World world, int metadata)
    {
        return new ToolStationLogic();
    }

    @Override
    public Integer getGui (World world, int x, int y, int z, EntityPlayer entityplayer)
    {   
        return ToolProxyCommon.toolStationID;
    }

    @Override
    public Object getModInstance ()
    {
        return TConstruct.instance;
    }
}
