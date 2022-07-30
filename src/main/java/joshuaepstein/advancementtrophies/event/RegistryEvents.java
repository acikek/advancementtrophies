package joshuaepstein.advancementtrophies.event;

import joshuaepstein.advancementtrophies.blocks.renderer.TrophyRenderer;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onRegisters(RegisterEvent event){
        event.register(ForgeRegistries.Keys.BLOCKS, ModBlocks::registerBlocks);
        event.register(ForgeRegistries.Keys.ITEMS, ModBlocks::registerBlockItems);
        event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, ModBlocks::registerTileEntities);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlocks.TROPHY_TILE_ENTITY, TrophyRenderer::new);
    }
}