package tconstruct.library.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class MoldBuilder
{
    public static MoldBuilder instance = new MoldBuilder();

    public List<ItemStack> blankMolds = new LinkedList<ItemStack>(); // i wish ItemStack would support equals so i could use a Set here...
    public Map<Integer, ItemStack> molds = new TreeMap<Integer, ItemStack>();

    /**
     * Returns whether the given ItemStack is a blank mold and therefore usable for mold crafting.
     */
    public static boolean isBlank (ItemStack stack)
    {
        for (ItemStack blank : instance.blankMolds)
            if (OreDictionary.itemMatches(stack, blank, false)) // this has nothing to do with the oredictionary.
                return true;

        return false;
    }

    public static void registerBlankMold (ItemStack itemStack)
    {
        instance.blankMolds.add(itemStack);
    }

    public static void registerMold (int id, Item item, int meta)
    {
        registerMold(id, new ItemStack(item, 1, meta));
    }

    public static void registerMold (int id, ItemStack mold)
    {
        if(instance.molds.containsKey(id))
            throw new IllegalArgumentException("[TCon API] Mold ID " + id + " is already occupied by " + instance.molds.get(id).getDisplayName());

        instance.molds.put(id, mold);
    }

    public static Collection<ItemStack> getStencils ()
    {
        return instance.molds.values();
    }

    /**
     * Returns the index of the given mold. If no mold is found, returns -1.
     */
    public static int getId(ItemStack mold)
    {
        for(Map.Entry<Integer, ItemStack> entry : instance.molds.entrySet())
            if (OreDictionary.itemMatches(mold, entry.getValue(), false))
                return entry.getKey();

        return -1;
    }

    // returns the mold with the given index
    public static ItemStack getMold (int num)
    {
        if (!instance.molds.containsKey(num))
            return null;

        return instance.molds.get(num).copy();
    }
}
