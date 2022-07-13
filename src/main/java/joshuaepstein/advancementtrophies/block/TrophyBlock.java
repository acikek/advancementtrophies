package joshuaepstein.advancementtrophies.block;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.block.entity.TrophyBlockEntity;
import joshuaepstein.advancementtrophies.block.renderer.TrophyRenderer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TrophyBlock extends AbstractTrophyBlock {
    public TrophyBlock(FabricBlockSettings properties) {
        super(properties.nonOpaque());
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void prepare(BlockState state, WorldAccess world, BlockPos pos, int flags, int maxUpdateDepth) {
        super.prepare(state, world, pos, flags, maxUpdateDepth);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch(state.get(FACING)){
            case NORTH-> {
                VoxelShape shape = VoxelShapes.empty();
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.125, 0.6875), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.125, 0.4375, 0.5625, 0.3125, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.5, 0.4375, 0.25, 0.625, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.625, 0.4375, 0.1875, 0.75, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.75, 0.4375, 0.25, 0.875, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.8125, 0.625, 0.4375, 0.9375, 0.75, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0.5, 0.4375, 0.9375, 0.625, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0.75, 0.4375, 0.9375, 0.875, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.375, 0.25, 0.75, 0.875, 0.375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.375, 0.625, 0.75, 0.875, 0.75), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.375, 0.375, 0.375, 0.875, 0.625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.375, 0.375, 0.75, 0.875, 0.625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.375, 0.375, 0.625, 0.625, 0.625), BooleanBiFunction.OR);
                return shape;
            }
            case SOUTH-> {
                VoxelShape shape = VoxelShapes.empty();
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.125, 0.6875), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.125, 0.4375, 0.5625, 0.3125, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0.5, 0.4375, 0.9375, 0.625, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.8125, 0.625, 0.4375, 0.9375, 0.75, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0.75, 0.4375, 0.9375, 0.875, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.625, 0.4375, 0.1875, 0.75, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.5, 0.4375, 0.25, 0.625, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.75, 0.4375, 0.25, 0.875, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.375, 0.625, 0.75, 0.875, 0.75), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.375, 0.25, 0.75, 0.875, 0.375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.375, 0.375, 0.75, 0.875, 0.625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.375, 0.375, 0.375, 0.875, 0.625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.375, 0.375, 0.625, 0.625, 0.625), BooleanBiFunction.OR);

                return shape;
            }
            case WEST-> {
                VoxelShape shape = VoxelShapes.empty();
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.125, 0.6875), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.125, 0.4375, 0.5625, 0.3125, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.5, 0.75, 0.5625, 0.625, 0.9375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.625, 0.8125, 0.5625, 0.75, 0.9375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.75, 0.75, 0.5625, 0.875, 0.9375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.625, 0.0625, 0.5625, 0.75, 0.1875), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.5, 0.0625, 0.5625, 0.625, 0.25), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.75, 0.0625, 0.5625, 0.875, 0.25), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.375, 0.25, 0.375, 0.875, 0.75), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.375, 0.25, 0.75, 0.875, 0.75), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.375, 0.625, 0.625, 0.875, 0.75), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.375, 0.25, 0.625, 0.875, 0.375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.375, 0.375, 0.625, 0.625, 0.625), BooleanBiFunction.OR);

                return shape;
            }
            case EAST-> {
                VoxelShape shape = VoxelShapes.empty();
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.125, 0.6875), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.125, 0.4375, 0.5625, 0.3125, 0.5625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.5, 0.0625, 0.5625, 0.625, 0.25), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.625, 0.0625, 0.5625, 0.75, 0.1875), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.75, 0.0625, 0.5625, 0.875, 0.25), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.625, 0.8125, 0.5625, 0.75, 0.9375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.5, 0.75, 0.5625, 0.625, 0.9375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.75, 0.75, 0.5625, 0.875, 0.9375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.375, 0.25, 0.75, 0.875, 0.75), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.375, 0.25, 0.375, 0.875, 0.75), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.375, 0.25, 0.625, 0.875, 0.375), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.375, 0.625, 0.625, 0.875, 0.75), BooleanBiFunction.OR);
                shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.375, 0.375, 0.625, 0.625, 0.625), BooleanBiFunction.OR);

                return shape;
            }
            default -> {
                return VoxelShapes.fullCube();
            }
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if(stack.getOrCreateNbt().contains("BlockEntityTag")){
            NbtCompound tag = stack.getOrCreateNbt().getCompound("BlockEntityTag");
            if(tag.contains("advancementName")){
                tooltip.add(Text.literal(Formatting.DARK_GRAY + "Advancement: " + Formatting.WHITE + tag.getString("advancementName")));
            }
        }
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        ItemStack itemStack = TrophyBlockEntity.getTrophyItemStack(((TrophyBlockEntity) world.getBlockEntity(pos)).getAdvancementName(), ((TrophyBlockEntity) world.getBlockEntity(pos)).getAdvancementDisplayItem());
        ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
        world.spawnEntity(itemEntity);
        super.onBreak(world, pos, state, player);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, Main.TROPHY_BLOCK_ENTITY, (world1, pos, state1, be) -> TrophyBlockEntity.tick(world1, pos, state1, be));
    }
}
