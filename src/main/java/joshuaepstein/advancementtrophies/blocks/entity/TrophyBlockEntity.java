package joshuaepstein.advancementtrophies.blocks.entity;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.blocks.TrophyBlock;
import joshuaepstein.advancementtrophies.blocks.data.TrophyData;
import joshuaepstein.advancementtrophies.blocks.renderer.TrophyRenderer;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import joshuaepstein.advancementtrophies.util.TrophyUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class TrophyBlockEntity extends BlockEntity {

    private String advancementName = "Blank";
    private String advancementDisplayItem = "minecraft:air";
    private boolean showName = true;

    public TrophyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, String name, String displayItem){
        super(type, pos, state);
        this.advancementName = name;
        this.advancementDisplayItem = displayItem;
    }

    public TrophyBlockEntity(BlockPos pos, BlockState state){
        super(ModBlocks.TROPHY_TILE_ENTITY, pos, state);
    }

    public void toggleRenderName(){
        showName = true;
        this.setChanged();
    }

    public boolean isShowName(){
        return true;
    }

    public InteractionResult onUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit){
//            player.displayClientMessage(new TextComponent("Advancement: " + name), true);
        if(world.getBlockEntity(pos) instanceof TrophyBlockEntity){
            TrophyRenderer.toggleRenderName(pos);
//            player.displayClientMessage(new TextComponent("Toggled Render Name"), true);
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString("advancementName", advancementName);
        pTag.putString("advancementDisplayItem", advancementDisplayItem);
        pTag.putBoolean("showName", showName);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.advancementName = pTag.getString("advancementName");
        this.advancementDisplayItem = pTag.getString("advancementDisplayItem");
        this.showName = pTag.getBoolean("showName");
        this.setChanged();
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public ItemStack getPickStack(){
        return TrophyUtils.getTrophyItemStack(advancementName, advancementDisplayItem);
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
