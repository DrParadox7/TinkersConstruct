package tconstruct.util.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import mantle.common.network.AbstractPacket;
import tconstruct.TConstruct;
import tconstruct.library.crafting.MoldBuilder;
import tconstruct.tools.inventory.MoldingTableContainer;
import tconstruct.tools.logic.MoldingTableLogic;

public class MoldingTablePacket extends AbstractPacket {

    int x, y, z;
    ItemStack contents;

    public MoldingTablePacket() {}

    public MoldingTablePacket(int x, int y, int z, ItemStack contents) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.contents = contents;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        ByteBufUtils.writeItemStack(buffer, contents);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        contents = ByteBufUtils.readItemStack(buffer);
    }

    @Override
    public void handleClientSide(EntityPlayer player) {}

    @Override
    public void handleServerSide(EntityPlayer player) {
        if (player.openContainer instanceof MoldingTableContainer) {
            MoldingTableContainer container = (MoldingTableContainer) player.openContainer;
            MoldingTableLogic logic = container.logic;
            if (logic != null && logic.xCoord == this.x && logic.yCoord == this.y && logic.zCoord == this.z)
                if (this.contents == null) logic.setSelectedMold(null);
                else {
                    ItemStack stackBlank = logic.getStackInSlot(0);
                    if (stackBlank != null && stackBlank.stackSize > 0 && MoldBuilder.isBlank(stackBlank)) {
                        boolean warning = true;
                        for (ItemStack stack : MoldBuilder.instance.molds.values()) {
                            if (stack != null && this.contents.isItemEqual(stack)) {
                                this.contents = stack.copy();
                                warning = false;
                                break;
                            }
                        }
                        if (warning) TConstruct.logger.warn(
                                "Possible packet-cheating with MoldingTable for player "
                                        + player.getCommandSenderName());
                        else logic.setSelectedMold(this.contents);
                    }
                }
        }
    }
}
