package joshuaepstein.advancementtrophies.event;

import com.google.common.collect.Iterables;
import joshuaepstein.advancementtrophies.blocks.TrophyBlock;
import joshuaepstein.advancementtrophies.blocks.data.TrophyData;
import joshuaepstein.advancementtrophies.init.ModAttributes;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import joshuaepstein.advancementtrophies.util.EntityHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.FrameType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderNameplateEvent;
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
                if(!event.getPlayer().getLevel().isClientSide){
//                    ItemStack stack = new ItemStack(ModBlocks.TROPHY);
//                    CompoundTag tag = stack.getOrCreateTag();
//                    tag.putString("advancement_name", event.getAdvancement().getDisplay().getTitle().getString());
//                    tag.putString("advancement_item", event.getAdvancement().getDisplay().getIcon().getItem().getRegistryName().toString());
//                    stack.setHoverName(new TextComponent(event.getAdvancement().getDisplay().getTitle().getString() +  " trophy.").withStyle(Style.EMPTY.withItalic(false).withColor(event.getAdvancement().getChatComponent().getStyle().getColor())));
//                    stack.setTag(tag);
//                    EntityHelper.giveItem(((ServerPlayer) event.getPlayer()), stack);

                    ItemStack stack = new ItemStack(ModBlocks.TROPHY);
                    CompoundTag tag = stack.getOrCreateTag();
                    CompoundTag blockTag = new CompoundTag();
                    blockTag.putString("advancementName", event.getAdvancement().getDisplay().getTitle().getString());
                    blockTag.putString("advancementDisplayItem", event.getAdvancement().getDisplay().getIcon().getItem().getRegistryName().toString());
                    tag.put("BlockEntityTag", blockTag);
                    stack.setTag(tag);
                    stack.setHoverName(new TextComponent(event.getAdvancement().getDisplay().getTitle().getString() +  " Trophy").withStyle(Style.EMPTY.withItalic(false).withColor(event.getAdvancement().getChatComponent().getStyle().getColor())));

//                    ItemStack stack = new ItemStack(ModBlocks.TROPHY);
//                    ModAttributes.ADVANCEMENT_NAME.create(stack, event.getAdvancement().getDisplay().getTitle().getString());
//                    ModAttributes.ADVANCEMENT_ITEM.create(stack, event.getAdvancement().getDisplay().getIcon().getItem().getRegistryName().toString());
//                    stack.setHoverName(new TextComponent(event.getAdvancement().getDisplay().getTitle().getString() +  " Trophy").withStyle(Style.EMPTY.withItalic(false).withColor(event.getAdvancement().getChatComponent().getStyle().getColor())));


                    EntityHelper.giveItem(((ServerPlayer) event.getPlayer()), stack);

                }
            }
        }
    }

}
