package joshuaepstein.advancementtrophies.blocks;

import joshuaepstein.advancementtrophies.blocks.entity.TrophyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class AbstractTrophyBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public AbstractTrophyBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.above()).isAir();
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player){
        BlockEntity blockEntity = level.getBlockEntity(pos);
        return blockEntity instanceof TrophyBlockEntity entity ? entity.getPickStack() : super.getCloneItemStack(state, target, level, pos, player);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        CompoundTag tag = pStack.getOrCreateTag();
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof TrophyBlockEntity entity) {

            if(tag.contains("BlockEntityTag")){
                CompoundTag compoundTag = tag.getCompound("BlockEntityTag");
                if(compoundTag.contains("advancementName") && compoundTag.contains("advancementDisplayItem")){
                    entity.load(compoundTag);
                }
            }
            entity.setChanged();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public void destroy(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        super.destroy(pLevel, pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pLevel.isClientSide) return InteractionResult.SUCCESS;
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof TrophyBlockEntity entity) {
            return entity.onUse(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TrophyBlockEntity(pPos, pState);
    }


}
