package joshuaepstein.advancementtrophies.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import joshuaepstein.advancementtrophies.Main;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;

public class VersionCommand extends Command {
    @Override
    public String getName() {
        return "version";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.executes(context -> {
            Player player = context.getSource().getPlayerOrException();
            player.sendSystemMessage(Component.literal(ChatFormatting.GRAY + "You are running" + ChatFormatting.GOLD + " AdvancementTrophies" + ChatFormatting.WHITE + " v" + Main.MOD_VERSION));
            player.sendSystemMessage(Component.literal(ChatFormatting.WHITE + "You can view the" + ChatFormatting.GOLD + " AdvancementTrophies " + ChatFormatting.WHITE + "Patch Notes here:"));
            player.sendSystemMessage(Component.literal(ChatFormatting.GRAY + Main.patchNotesURL).withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://patch.joshepstein.co.uk/trophies")).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("https://patch.joshepstein.co.uk/trophies")))));
            return 1;
        });
    }

    @Override
    public boolean isDedicatedServerOnly() {
        return false;
    }
}
