package tconstruct.plugins.gears;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.PatternBuilder;
import tconstruct.library.util.IPattern;
import tconstruct.util.config.PHConstruct;

public class GearCast extends Item implements IPattern {
    public GearCast() {
        if (!PHConstruct.steelPatterns) {
            setTextureName("tinker:materials/gear_cast");
        } else {
            setTextureName("tinker:materials/gear_cast_steel");
        }
        setUnlocalizedName("tconstruct.GearPattern");
    }

    @Override
    public int getPatternCost(ItemStack pattern) {
        return 8;
    }

    @Override
    public ItemStack getPatternOutput(ItemStack pattern, ItemStack input, PatternBuilder.MaterialSet set) {
        return TConstructRegistry.getPartMapping(this, pattern.getItemDamage(), set.materialID);
    }
}
