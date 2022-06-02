package joshuaepstein.advancementtrophies.event;

import joshuaepstein.advancementtrophies.init.ModBlocks;
import joshuaepstein.advancementtrophies.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event){
        ModBlocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event){
        ModItems.registerItems(event);
        ModBlocks.registerBlockItems(event);
    }

    @SubscribeEvent
    public static void onTileEntityRegister(RegistryEvent.Register<BlockEntityType<?>> event) {
        ModBlocks.registerTileEntities(event);
    }

}