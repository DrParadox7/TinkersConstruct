package tconstruct.modifiers.armor;

import static tconstruct.library.util.XpUtils.ModCost;

import java.util.EnumSet;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tconstruct.library.armor.ArmorMod;
import tconstruct.library.armor.ArmorPart;

/* Adds an integer NBTTag */

public class AModInteger extends ArmorMod {
    String color;
    String tooltipName;
    int initialIncrease;
    int secondaryIncrease;

    public AModInteger(
            int effect,
            String dataKey,
            EnumSet<ArmorPart> armorTypes,
            ItemStack[] items,
            int increase,
            String c,
            String tip) {
        super(effect, dataKey, armorTypes, items);
        initialIncrease = secondaryIncrease = increase;
        color = c;
        tooltipName = tip;
    }

    public AModInteger(
            int effect,
            String dataKey,
            EnumSet<ArmorPart> armorTypes,
            ItemStack[] items,
            int increase1,
            int increase2,
            String c,
            String tip) {
        super(effect, dataKey, armorTypes, items);
        initialIncrease = increase1;
        secondaryIncrease = increase2;
        color = c;
        tooltipName = tip;
    }

    @Override
    public void modify(ItemStack[] recipe, ItemStack input) {
        NBTTagCompound tags = getModifierTag(input);
        if (tags.hasKey(key)) {
            int increase = tags.getInteger(key);
            increase += secondaryIncrease;
            tags.setInteger(key, increase);
        } else {
            tags.setInteger(key, initialIncrease);
        }

        int modifiers = tags.getInteger("Modifiers");
        modifiers -= 1;
        tags.setInteger("Modifiers", modifiers);

        int upgrades = tags.getInteger("Upgrades");
        upgrades += 1;
        tags.setInteger("Upgrades", upgrades);

        tags.setInteger("XP_Cost", ModCost());

        addToolTip(input, color + tooltipName, color + key);
    }
}
