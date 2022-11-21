package tconstruct.smeltery.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import java.util.ArrayList;
import java.util.List;

public class ActiveContainer extends Container
{
    public List<ActiveSlot> activeInventorySlots = new ArrayList<ActiveSlot>();

    @Override
    public boolean canInteractWith (EntityPlayer entityplayer)
    {
        return false;
    }

    protected ActiveSlot addDualSlotToContainer (ActiveSlot slot)
    {
        slot.activeSlotNumber = this.activeInventorySlots.size();
        this.activeInventorySlots.add(slot);
        this.addSlotToContainer(slot);
        return slot;
    }

}