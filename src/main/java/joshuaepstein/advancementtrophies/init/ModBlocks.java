package joshuaepstein.advancementtrophies.init;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.blocks.TrophyBlock;
import joshuaepstein.advancementtrophies.blocks.entity.TrophyBlockEntity;
import joshuaepstein.advancementtrophies.blocks.renderer.TrophyRenderer;
import joshuaepstein.advancementtrophies.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks {

    public static final Block TROPHY = new TrophyBlock(); // TODO: Create Trophy block class, with correct voxelbox stuff
    public static final BlockEntityType<TrophyBlockEntity> TROPHY_TILE_ENTITY = BlockEntityType.Builder.of(TrophyBlockEntity::create, TROPHY).build(null);

    public static void registerBlocks(RegistryEvent.Register<Block> event){
        registerBlock(event, TROPHY, Main.id("trophy"));
    }

    public static void registerBlockItems(RegistryEvent.Register<Item> event){
        registerBlockItem(event, TROPHY, CreativeModeTab.TAB_SEARCH);
    }

    public static void registerTileEntities(RegistryEvent.Register<BlockEntityType<?>> event){
        registerTileEntity(event, TROPHY_TILE_ENTITY, Main.id("trophy_block_entity"));
    }

    public static void registerTileEntityRenderers(){
//        ClientRegistry.registerEntityShader(TROPHY_TILE_ENTITY, TrophyRenderer::new);
    }

    private static void registerBlock(RegistryEvent.Register<Block> event, Block block, ResourceLocation id){
        block.setRegistryName(id);
        event.getRegistry().register(block);
    }

    private static <T extends BlockEntity> void registerTileEntity(RegistryEvent.Register<BlockEntityType<?>> event, BlockEntityType<?> type, ResourceLocation id){
        type.setRegistryName(id);
        event.getRegistry().register(type);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block, boolean hide){
        BlockItem blockItem;
        if(hide){
            blockItem = new BlockItem(block, (new Item.Properties().stacksTo(64)));
        } else {
            blockItem = new BlockItem(block, (new Item.Properties().tab(CreativeModeTab.TAB_SEARCH).stacksTo(64)));
        }
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block){
//        registerBlockItem(event, block, CreativeModeTab.TAB_SEARCH);
        registerBlockItem(event, block, CreativeModeTab.TAB_SEARCH);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block, CreativeModeTab group){
        BlockItem blockItem = new BlockItem(block, (new Item.Properties().tab(group).stacksTo(64)));
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block, CreativeModeTab group, int maxStackSize, final boolean showGlint){
        BlockItem blockItem = new BlockItem(block, (new Item.Properties().tab(group).stacksTo(maxStackSize))) {
            public boolean isFoil(ItemStack stack){ return showGlint; }
        };
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block, int maxStackSize) {
        BlockItem blockItem = new BlockItem(block, (new Item.Properties()).tab(CreativeModeTab.TAB_SEARCH).stacksTo(maxStackSize));
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block, BlockItem blockItem) {
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
    }

    private static void registerTallBlockItem(RegistryEvent.Register<Item> event, Block block) {
        DoubleHighBlockItem tallBlockItem = new DoubleHighBlockItem(block, (new Item.Properties()).tab(CreativeModeTab.TAB_SEARCH).stacksTo(64));
        tallBlockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(tallBlockItem);
    }

    private static boolean never(BlockState blockState, BlockGetter iBlockReader, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static boolean never(BlockState state, BlockGetter blockReader, BlockPos pos) {
        return false;
    }


}
