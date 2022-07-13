package joshuaepstein.advancementtrophies;

import joshuaepstein.advancementtrophies.block.TrophyBlock;
import joshuaepstein.advancementtrophies.block.entity.TrophyBlockEntity;
import joshuaepstein.advancementtrophies.block.renderer.TrophyRenderer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

    public static final String MOD_ID = "advancementtrophies";
    public static final String MOD_VERSION = "1.2";
    public static final String patchNotesURL = "https://patch.joshepstein.co.uk/trophies";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final Block TROPHY = new TrophyBlock(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK).strength(0.2f));
    public static BlockEntityType<TrophyBlockEntity> TROPHY_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(TrophyBlockEntity::new, TROPHY).build();

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal(MOD_ID).then(CommandManager.literal("version").executes((context -> {
                ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                player.sendMessage(Text.literal(Formatting.GRAY + "You are running" + Formatting.GOLD + " AdvancementTrophies " + Formatting.WHITE + "v" + Main.MOD_VERSION));
                player.sendMessage(Text.literal(Formatting.WHITE + "You can view the" + Formatting.GOLD + " AdvancementTrophies " + Formatting.WHITE + "Patch Notes here:"));
                player.sendMessage(Text.literal(Formatting.GRAY + Main.patchNotesURL).setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://patch.joshepstein.co.uk/trophies")).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("https://patch.joshepstein.co.uk/trophies")))));
                return 1;
            }))));
        }));

        Registry.register(Registry.BLOCK, Main.id("trophy"), TROPHY);
        Registry.register(Registry.ITEM, Main.id("trophy"), new BlockItem(TROPHY, new FabricItemSettings()));
        BlockEntityRendererRegistry.register(TROPHY_BLOCK_ENTITY, TrophyRenderer::new);
        TROPHY_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Main.id("trophy_tile_entity"), TROPHY_BLOCK_ENTITY);
    }

    public static String sId(String name){
        return MOD_ID + ":" + name;
    }

    public static Identifier id(String name){
        return new Identifier(MOD_ID, name);
    }
}
