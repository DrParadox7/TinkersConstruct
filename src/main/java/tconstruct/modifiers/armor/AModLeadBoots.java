package tconstruct.modifiers.armor;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import tconstruct.library.armor.ArmorPart;

import java.util.EnumSet;
import java.util.UUID;

public class AModLeadBoots extends AModBoolean
{

    public AModLeadBoots(ItemStack[] items)
    {
        super(2, "LeadBoots", EnumSet.of(ArmorPart.Feet), items, "\u00a78", "Lead Boots");
    }

    @Override
    public void modify (ItemStack[] recipe, ItemStack armor)
    {
        NBTTagCompound baseTag = armor.getTagCompound();
        NBTTagCompound armorTag = armor.getTagCompound().getCompoundTag(getTagName(armor));

        armorTag.setBoolean(key, true);

        int modifiers = armorTag.getInteger("Modifiers");
        modifiers -= 1;
        armorTag.setInteger("Modifiers", modifiers);

        int upgrades = armorTag.getInteger("Upgrades");
        upgrades += 1;
        armorTag.setInteger("Upgrades", upgrades);

        armorTag.setInteger("XP_Cost", 17 * 10); //1 lvl = 17 xp (Up until lvl 16)

        addToolTip(armor, color + tooltipName, color + key);

        NBTTagList attributes;
        if (baseTag.hasKey("AttributeModifiers"))
        {
            attributes = baseTag.getTagList("AttributeModifiers", 0);
            for (int iter = 0; iter < attributes.tagCount(); iter++)
            {
                NBTTagCompound tag = (NBTTagCompound) attributes.getCompoundTagAt(iter);
                if (tag.getString("AttributeName").equals("generic.knockbackResistance"))
                    attributes.removeTag(iter);
            }
        }
        else
        {
            attributes = new NBTTagList();
            baseTag.setTag("AttributeModifiers", attributes);
        }
        attributes.appendTag(getAttributeTag("generic.knockbackResistance", key, 0.4, true, shoes));
    }

    private static final UUID shoes = UUID.fromString("4188779d-69d4-487c-b307-c4c182522c47");
}
