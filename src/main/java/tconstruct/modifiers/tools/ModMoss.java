package tconstruct.modifiers.tools;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import tconstruct.library.crafting.ModifyBuilder;
import tconstruct.library.tools.ToolCore;
import tconstruct.tools.TinkerTools;

public class ModMoss extends ModBoolean {

    public ModMoss(ItemStack[] items, int effect, String tag, String c, String tip){
        super(items, effect, "Moss", "\u00a72", StatCollector.translateToLocal("modifier.tool.mossnew"));
    }
    //ModifyBuilder.registerModifier(new ModInteger(new ItemStack[] { new ItemStack(TinkerTools.materials, 1, 6) }, 4, "Moss", 3, "\u00a72", StatCollector.translateToLocal("modifier.tool.mossnew")));

    @Override
    protected boolean canModify (ItemStack tool, ItemStack[] input)
    {
        NBTTagCompound tags = tool.getTagCompound().getCompoundTag("InfiTool");
        return tags.getInteger("Modifiers") > 0 && !tags.getBoolean(key);
    }
    public static float mossChance(Entity entity) {
        World world = entity.worldObj;

        if (!world.provider.hasNoSky && world.isDaytime()) {

            int light = world.getSavedLightValue(EnumSkyBlock.Sky, (int) entity.posX, (int) entity.posY, (int) entity.posZ) - world.skylightSubtracted;

            if(light < 1)
                return 0;

            float angle = world.getCelestialAngleRadians(1.0F);

            if (angle < (float) Math.PI)
                angle += (0.0F - angle) * 0.2F;
            else
                angle += ((float) Math.PI * 2F - angle) * 0.2F;

            light = Math.round(light * MathHelper.cos(angle));

            float chance = light * 0.05f;

            return chance;
        }
        return 0;
    }
}
