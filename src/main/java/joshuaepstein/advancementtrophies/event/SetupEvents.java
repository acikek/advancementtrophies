package joshuaepstein.advancementtrophies.event;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.blocks.renderer.TrophyRenderer;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvents {

    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event){
        Main.LOGGER.info("setupClient()");
        ModBlocks.registerTileEntityRenderers();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(SetupEvents::onRegisterRenderers);
    }

    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlocks.TROPHY_TILE_ENTITY, TrophyRenderer::new);
    }

    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event){
        Main.LOGGER.info("setupCommon()");
    }

    @SubscribeEvent
    public static void setupDedicatedServer(final FMLDedicatedServerSetupEvent event) {
        Main.LOGGER.info("setupDedicatedServer()");
    }

}
