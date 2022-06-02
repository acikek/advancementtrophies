package joshuaepstein.advancementtrophies.event;

import joshuaepstein.advancementtrophies.Main;
import joshuaepstein.advancementtrophies.init.ModAttributes;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvents {

    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event){
        Main.LOGGER.info("setupClient()");
        ModBlocks.registerTileEntityRenderers();
    }

    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event){
        Main.LOGGER.info("setupCommon()");
    }

    @SubscribeEvent
    public static void setupDedicatedServer(final FMLDedicatedServerSetupEvent event) {
        Main.LOGGER.info("setupDedicatedServer()");
    }

    @SubscribeEvent
    public static void onAttributeRegister(RegistryEvent.Register<Attribute> event){
        ModAttributes.register(event);
    }

}
