package joshuaepstein.advancementtrophies.init;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.blocks.TrophyBlock;
import joshuaepstein.advancementtrophies.blocks.entity.TrophyBlockEntity;
import joshuaepstein.advancementtrophies.blocks.renderer.TrophyRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;

public class ModBlocks {

    public static final Block TROPHY = new TrophyBlock(); // TODO: Create Trophy block class, with correct voxelbox stuff
    public static final BlockEntityType<TrophyBlockEntity> TROPHY_TILE_ENTITY = BlockEntityType.Builder.of(TrophyBlockEntity::create, TROPHY).build(null);

    public static void registerBlocks(RegisterEvent.RegisterHelper<Block> event){
        registerBlock(event, TROPHY, Main.id("trophy"));
    }

    public static void registerBlockItems(RegisterEvent.RegisterHelper<Item> event){
        registerBlockItem(event, TROPHY, CreativeModeTab.TAB_SEARCH, Main.id("trophy"));
    }

    public static void registerTileEntities(RegisterEvent.RegisterHelper<BlockEntityType<?>> event){
        registerTileEntity(event, TROPHY_TILE_ENTITY, Main.id("trophy_block_entity"));
    }

    public static void registerTileEntityRenderers(){
//        EntityRenderers.register().registerEntityShader(TROPHY_TILE_ENTITY, TrophyRenderer::new);
    }

    private static void registerBlock(RegisterEvent.RegisterHelper<Block> event, Block block, ResourceLocation id){
        event.register(id, block);
    }

    private static <T extends BlockEntity> void registerTileEntity(RegisterEvent.RegisterHelper<BlockEntityType<?>> event, BlockEntityType<?> type, ResourceLocation id){
        event.register(id, type);
    }

    private static void registerBlockItem(RegisterEvent.RegisterHelper<Item> event, Block block, CreativeModeTab group, ResourceLocation id){
        BlockItem blockItem = new BlockItem(block, (new Item.Properties().tab(group).stacksTo(64)));
        event.register(id, blockItem);
    }

    private static void registerBlockItem(RegisterEvent.RegisterHelper<Item> event, Block block, CreativeModeTab group, int maxStackSize, final boolean showGlint, ResourceLocation id){
        BlockItem blockItem = new BlockItem(block, (new Item.Properties().tab(group).stacksTo(maxStackSize))) {
            public boolean isFoil(ItemStack stack){ return showGlint; }
        };
        event.register(id, blockItem);
    }

    private static void registerBlockItem(RegisterEvent.RegisterHelper<Item> event, Block block, int maxStackSize, ResourceLocation id) {
        BlockItem blockItem = new BlockItem(block, (new Item.Properties()).tab(CreativeModeTab.TAB_SEARCH).stacksTo(maxStackSize));

        event.register(id, blockItem);
    }

    private static void registerBlockItem(RegisterEvent.RegisterHelper<Item> event, Block block, BlockItem blockItem, ResourceLocation id) {

        event.register(id, blockItem);
    }

    private static void registerTallBlockItem(RegisterEvent.RegisterHelper<Item> event, Block block, ResourceLocation id) {
        DoubleHighBlockItem tallBlockItem = new DoubleHighBlockItem(block, (new Item.Properties()).tab(CreativeModeTab.TAB_SEARCH).stacksTo(64));
        event.register(id, tallBlockItem);
    }

    private static boolean never(BlockState blockState, BlockGetter iBlockReader, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static boolean never(BlockState state, BlockGetter blockReader, BlockPos pos) {
        return false;
    }


}
