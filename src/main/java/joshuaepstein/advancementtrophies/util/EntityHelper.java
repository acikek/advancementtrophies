package joshuaepstein.advancementtrophies.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class EntityHelper {

    public static void giveItem(ServerPlayer player, ItemStack stack) {
        stack = stack.copy();
        if (player.getInventory().add(stack) && stack.isEmpty()) {
            stack.setCount(1);
            ItemEntity dropped = player.drop(stack, false);
            if (dropped != null)
                dropped.makeFakeItem();
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, ((player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.inventoryMenu.broadcastChanges();
        } else {
            ItemEntity dropped = player.drop(stack, false);
            if (dropped != null) {
                dropped.setNoPickUpDelay();
                dropped.setOwner(player.getUUID());
            }
        }
    }

}
