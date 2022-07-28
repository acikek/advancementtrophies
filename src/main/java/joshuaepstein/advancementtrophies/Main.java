package joshuaepstein.advancementtrophies;

import joshuaepstein.advancementtrophies.blocks.entity.TrophyBlockEntity;
import joshuaepstein.advancementtrophies.blocks.renderer.TrophyRenderer;
import joshuaepstein.advancementtrophies.init.ModBlocks;
import joshuaepstein.advancementtrophies.init.ModCommands;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
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
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "advancementtrophies";
    public static final String MOD_VERSION = "1.3";
    public static final String patchNotesURL = "https://patch.joshepstein.co.uk/trophies";
    public static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::onCommandRegister);
    }
    public void onCommandRegister(RegisterCommandsEvent event){
        ModCommands.registerCommands(event.getDispatcher(), event.getEnvironment());
    }

    public static ResourceLocation id(String name){
        return new ResourceLocation(MOD_ID, name);
    }

    @NotNull
    public static ItemStack getTrophyItemStack(String name, String itemDisplay){
        CompoundTag nbt = new CompoundTag();
        ItemStack itemStack = new ItemStack(ModBlocks.TROPHY);
        CompoundTag blockEntityTag = new CompoundTag();
        blockEntityTag.putString("advancementName", name);
        blockEntityTag.putString("advancementDisplayItem", itemDisplay);
        nbt.put("BlockEntityTag", blockEntityTag);
        itemStack.setTag(nbt);
        itemStack.setHoverName(Component.literal(name + " Trophy").withStyle(Style.EMPTY.withColor(ChatFormatting.GOLD).withItalic(false)));
        return itemStack;
    }

}
