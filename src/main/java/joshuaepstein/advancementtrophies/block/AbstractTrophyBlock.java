package joshuaepstein.advancementtrophies.block;

import joshuaepstein.advancementtrophies.block.entity.TrophyBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class AbstractTrophyBlock extends BlockWithEntity  {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public AbstractTrophyBlock(FabricBlockSettings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof TrophyBlockEntity entity ? entity.getPickStack() : ItemStack.EMPTY;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        NbtCompound nbt = itemStack.getOrCreateNbt();
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof TrophyBlockEntity entity){
            if(nbt.contains("BlockEntityTag")){
                NbtCompound ctag = nbt.getCompound("BlockEntityTag");
                if(ctag.contains("advancementName") && ctag.contains("advancementDisplayItem")){
                    entity.readNbt(ctag);
                }
            }
            entity.markDirty();
        }

    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.isAir(pos.up());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TrophyBlockEntity(pos, state);
    }
}
