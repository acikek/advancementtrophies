package joshuaepstein.advancementtrophies.event;

import com.google.common.collect.Iterables;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import net.minecraft.advancements.FrameType;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AdvancementEvents {

    @SubscribeEvent
    public static void onGain(AdvancementEvent event){
        if(Iterables.size(event.getAdvancement().getChildren()) == 0){
            if(event.getAdvancement().getId().toString().contains("recipe")) return;
            if(!event.getAdvancement().getDisplay().getFrame().equals(FrameType.TASK)){
                if(!event.getEntity().getLevel().isClientSide){
                    ItemStack stack = new ItemStack(ModBlocks.TROPHY);
                    CompoundTag tag = stack.getOrCreateTag();
                    CompoundTag blockTag = new CompoundTag();
                    blockTag.putString("advancementName", event.getAdvancement().getDisplay().getTitle().getString());
                    if(event.getAdvancement().getDisplay() == null){
                        blockTag.putString("advancementDisplayItem", "minecraft:air");
                    } else {
                        blockTag.putString("advancementDisplayItem", Registry.ITEM.getKey(event.getAdvancement().getDisplay().getIcon().getItem()).toString());
                    }
                    tag.put("BlockEntityTag", blockTag);
                    stack.setTag(tag);
                    stack.setHoverName(Component.literal(event.getAdvancement().getDisplay().getTitle().getString() +  " Trophy").withStyle(Style.EMPTY.withItalic(false).withColor(event.getAdvancement().getChatComponent().getStyle().getColor())));
                    giveItem(((ServerPlayer) event.getEntity()), stack);
                }
            }
        }
    }

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
