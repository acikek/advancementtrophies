package joshuaepstein.advancementtrophies.util;

import joshuaepstein.advancementtrophies.blocks.data.TrophyData;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import joshuaepstein.advancementtrophies.init.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TrophyUtils {

    @NotNull
    public static ItemStack getTrophyItemStack(String name, String itemDisplay){
        CompoundTag nbt = new CompoundTag();
        ItemStack itemStack = new ItemStack(ModBlocks.TROPHY);
        CompoundTag blockEntityTag = new CompoundTag();
        blockEntityTag.putString("advancementName", name);
        blockEntityTag.putString("advancementDisplayItem", itemDisplay);
        nbt.put("BlockEntityTag", blockEntityTag);
        itemStack.setTag(nbt);
        itemStack.setHoverName(new TextComponent(name + " Trophy").withStyle(Style.EMPTY.withColor(ChatFormatting.GOLD).withItalic(false)));
        return itemStack;
    }

}
