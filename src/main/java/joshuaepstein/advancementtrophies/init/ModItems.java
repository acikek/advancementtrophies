package joshuaepstein.advancementtrophies.init;

import joshuaepstein.advancementtrophies.Main;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;


public class ModItems {

//    public static CreativeModeTab GROUP = new CreativeModeTab(Main.MOD_ID) {
//        @Override
//        public ItemStack makeIcon() {
//            return new ItemStack(ModBlocks.TROPHY);
//        }
//
//        @Override
//        public boolean hasSearchBar() {
//            return false;
//        }
//
//        @Override
//        public Component getDisplayName() {
//            return new TextComponent("Advancement Trophies");
//        }
//    };

//    public static BasicItem item = new BasicItem(Main.id("this_is_an_example"), new Item.Properties().tab(GROUP).stacksTo(20));
//    public static TrophyItem TROPHY = new TrophyItem();

    public static void registerItems(RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry = event.getRegistry();

//        registry.register(TROPHY);

//        Main.LOGGER.info("Advancement Trophies Items Registered!");
    }

}
