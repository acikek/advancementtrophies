package joshuaepstein.advancementtrophies.blocks;

import joshuaepstein.advancementtrophies.blocks.entity.TrophyBlockEntity;
import joshuaepstein.advancementtrophies.blocks.renderer.TrophyRenderer;
import joshuaepstein.advancementtrophies.util.TrophyUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TrophyBlock extends AbstractTrophyBlock{

    public TrophyBlock() {
        super(Properties.copy(Blocks.GOLD_BLOCK).strength(0.2f));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        switch(state.getValue(FACING)){
            case NORTH-> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.125, 0.6875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.125, 0.4375, 0.5625, 0.3125, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.5, 0.4375, 0.25, 0.625, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.625, 0.4375, 0.1875, 0.75, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.75, 0.4375, 0.25, 0.875, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0.625, 0.4375, 0.9375, 0.75, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.75, 0.5, 0.4375, 0.9375, 0.625, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.75, 0.75, 0.4375, 0.9375, 0.875, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.25, 0.75, 0.875, 0.375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.625, 0.75, 0.875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.375, 0.375, 0.875, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.625, 0.375, 0.375, 0.75, 0.875, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.375, 0.625, 0.625, 0.625), BooleanOp.OR);
                return shape;
            }
            case SOUTH-> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.125, 0.6875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.125, 0.4375, 0.5625, 0.3125, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.75, 0.5, 0.4375, 0.9375, 0.625, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0.625, 0.4375, 0.9375, 0.75, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.75, 0.75, 0.4375, 0.9375, 0.875, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.625, 0.4375, 0.1875, 0.75, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.5, 0.4375, 0.25, 0.625, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.75, 0.4375, 0.25, 0.875, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.625, 0.75, 0.875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.25, 0.75, 0.875, 0.375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.625, 0.375, 0.375, 0.75, 0.875, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.375, 0.375, 0.875, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.375, 0.625, 0.625, 0.625), BooleanOp.OR);

                return shape;
            }
            case WEST-> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.125, 0.6875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.125, 0.4375, 0.5625, 0.3125, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.5, 0.75, 0.5625, 0.625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.625, 0.8125, 0.5625, 0.75, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.75, 0.75, 0.5625, 0.875, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.625, 0.0625, 0.5625, 0.75, 0.1875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.5, 0.0625, 0.5625, 0.625, 0.25), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.75, 0.0625, 0.5625, 0.875, 0.25), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.25, 0.375, 0.875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.625, 0.375, 0.25, 0.75, 0.875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.625, 0.625, 0.875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.25, 0.625, 0.875, 0.375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.375, 0.625, 0.625, 0.625), BooleanOp.OR);

                return shape;
            }
            case EAST-> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.125, 0.6875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.125, 0.4375, 0.5625, 0.3125, 0.5625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.375, 0.625, 0.375, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.5, 0.0625, 0.5625, 0.625, 0.25), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.625, 0.0625, 0.5625, 0.75, 0.1875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.75, 0.0625, 0.5625, 0.875, 0.25), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.625, 0.8125, 0.5625, 0.75, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.5, 0.75, 0.5625, 0.625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.4375, 0.75, 0.75, 0.5625, 0.875, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.625, 0.375, 0.25, 0.75, 0.875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.25, 0.375, 0.875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.25, 0.625, 0.875, 0.375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.625, 0.625, 0.875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.375, 0.375, 0.625, 0.625, 0.625), BooleanOp.OR);

                return shape;
            }
            default -> {
                return Shapes.block();
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        return pDirection == Direction.DOWN && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltip, TooltipFlag flag) {
//        Get BlockEntityTag data
        if(stack.getOrCreateTag().contains("BlockEntityTag")) {
            CompoundTag tag = stack.getOrCreateTag().getCompound("BlockEntityTag");
            if(tag.contains("advancementName")) {
                tooltip.add(new TextComponent(ChatFormatting.DARK_GRAY + "Advancement: " + ChatFormatting.WHITE + tag.getString("advancementName")));
            }
        }
        super.appendHoverText(stack, getter, tooltip, flag);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(TrophyRenderer.positionsWithoutName.contains(pPos)) {
//            TrophyRenderer.positionsWithoutName.remove(pPos);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    TrophyRenderer.positionsWithoutName.remove(pPos);
                }
            }, 100);
        }
        ItemStack itemStack = TrophyUtils.getTrophyItemStack(((TrophyBlockEntity) pLevel.getBlockEntity(pPos)).getAdvancementName(), ((TrophyBlockEntity) pLevel.getBlockEntity(pPos)).getAdvancementDisplayItem());
        ItemEntity itemEntity = new ItemEntity(pLevel, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, itemStack);
        pLevel.addFreshEntity(itemEntity);
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.above()).isAir() && !pLevel.getBlockState(pPos.east()).getMaterial().isLiquid() && !pLevel.getBlockState(pPos.west()).getMaterial().isLiquid() && !pLevel.getBlockState(pPos.north()).getMaterial().isLiquid() && !pLevel.getBlockState(pPos.south()).getMaterial().isLiquid();
    }
}