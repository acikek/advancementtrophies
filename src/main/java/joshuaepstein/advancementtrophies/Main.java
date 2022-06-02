package joshuaepstein.advancementtrophies;

import joshuaepstein.advancementtrophies.blocks.entity.TrophyBlockEntity;
import joshuaepstein.advancementtrophies.blocks.renderer.TrophyRenderer;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import joshuaepstein.advancementtrophies.init.ModCommands;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "advancementtrophies";
    public static final String MOD_VERSION = "1.1";
    public static final String patchNotesURL = "https://patch.joshepstein.co.uk/trophies";
    public static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        if(FMLEnvironment.dist == Dist.CLIENT){
            IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
            IEventBus forgeBus = MinecraftForge.EVENT_BUS;

            modBus.addListener(this::onRegisterRenderers);
        }
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::onCommandRegister);
    }

    public void onCommandRegister(RegisterCommandsEvent event){
        ModCommands.registerCommands(event.getDispatcher(), event.getEnvironment());
    }

    public void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlocks.TROPHY_TILE_ENTITY, TrophyRenderer::new);
    }

    public static String sId(String name){
        return MOD_ID + ":" + name;
    }

    public static ResourceLocation id(String name){
        return new ResourceLocation(MOD_ID, name);
    }


}
