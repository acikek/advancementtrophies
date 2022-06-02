package joshuaepstein.advancementtrophies.blocks.data;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.List;
import java.util.Objects;

public class TrophyData implements INBTSerializable<CompoundTag> {

    public static final TrophyData EMPTY = new TrophyData.EmptyData();
    private CompoundTag delegate = new CompoundTag();
    protected String advancementName;
    protected Item advancementDisplayItem;

    public TrophyData(){
        this.advancementName = "Blank";
        this.advancementDisplayItem = Items.DIRT;
    }

    public TrophyData(ItemStack delegate){
        this.advancementName = "Blank";
        this.advancementDisplayItem = Items.DIRT;
        if(delegate != null){
            this.delegate = delegate.getOrCreateTag();
            this.deserializeNBT(this.delegate.getCompound("data"));
        }
    }

    public CompoundTag getDelegate(){
        return this.delegate;
    }

    public void updateDelegate(){
        if(this.delegate != null)
            this.delegate.put("data", this.serializeNBT());
    }

    public String getAdvancementName(){
        return this.advancementName;
    }

    public void setAdvancementName(String name){
        if(!Objects.equals(advancementName, name)){
            this.advancementName = name;
            this.updateDelegate();
        }
    }

    public Item getAdvancementDisplayItem(){
        return this.advancementDisplayItem;
    }

    public void setAdvancementDisplayItem(Item stack){
        if(this.advancementDisplayItem != stack){
            this.advancementDisplayItem = stack;
            this.updateDelegate();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(List<Component> tooltip, TooltipFlag flag){
        tooltip.add(new TextComponent(ChatFormatting.DARK_GRAY + "Advancement: " + ChatFormatting.WHITE + advancementName));
        tooltip.add(new TextComponent(ChatFormatting.DARK_GRAY + "Item: " + ChatFormatting.WHITE + advancementDisplayItem.getRegistryName().toString()));
    }

    public CompoundTag serializeNBT(){
        CompoundTag tag = new CompoundTag();
        tag.putString("advancementName", this.advancementName);
        tag.putString("advancementDisplayItem", this.advancementDisplayItem.getRegistryName().toString());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.advancementName = nbt.getString("advancementName");
        if(nbt.getString("advancementDisplayItem").contains(":")){
            String[] resourceLocationSplit = nbt.getString("advancementDisplayItem").split(":");
            String namespaceKey = resourceLocationSplit[0];
            String valueKey = resourceLocationSplit[1];
            this.advancementDisplayItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(namespaceKey, valueKey));
        } else {
            this.advancementDisplayItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getString("advancementDisplayItem")));
        }
    }

    public TrophyData deserialize(CompoundTag nbt){
        this.deserializeNBT(nbt);
        return this;
    }

    private static class EmptyData extends TrophyData {
        private EmptyData(){}

        public void setAdvancementName() {}
        public void setAdvancementDisplayItem() {}
    }
}
