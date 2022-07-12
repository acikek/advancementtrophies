package joshuaepstein.advancementtrophies.blocks.entity;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class TrophyBlockEntity extends BlockEntity {

    private String advancementName = "Blank";
    private String advancementDisplayItem = "minecraft:air";

    public TrophyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, String name, String displayItem){
        super(type, pos, state);
        this.advancementName = name;
        this.advancementDisplayItem = displayItem;
    }

    public TrophyBlockEntity(BlockPos pos, BlockState state){
        super(ModBlocks.TROPHY_TILE_ENTITY, pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString("advancementName", advancementName);
        pTag.putString("advancementDisplayItem", advancementDisplayItem);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.advancementName = pTag.getString("advancementName");
        this.advancementDisplayItem = pTag.getString("advancementDisplayItem");
        this.setChanged();
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public ItemStack getPickStack(){
        return Main.getTrophyItemStack(advancementName, advancementDisplayItem);
    }

    public static @NotNull TrophyBlockEntity create(BlockPos pos, BlockState state){
        return new TrophyBlockEntity(pos, state);
    }

    public String getAdvancementName() {
        return advancementName;
    }

    public String getAdvancementDisplayItem() {
        return advancementDisplayItem;
    }

    public Item getDisplayItem() {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(advancementDisplayItem));
    }

}
