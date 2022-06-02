package joshuaepstein.advancementtrophies.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import joshuaepstein.advancementtrophies.Main;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import org.w3c.dom.Text;

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
            context.getSource().sendSuccess(new TextComponent(ChatFormatting.GRAY + "You are running AdvancementTrophies" + ChatFormatting.WHITE + " v" + Main.MOD_VERSION), false);
//            Send pactch notes url: https://patch.joshepstein.co.uk/trophies/
            context.getSource().getPlayerOrException().sendMessage(new TextComponent(ChatFormatting.WHITE + "You can view the" + ChatFormatting.GOLD + " AdvancementTrophies " + ChatFormatting.WHITE + "Patch Notes here:"), context.getSource().getPlayerOrException().getUUID());
            context.getSource().getPlayerOrException().sendMessage(new TextComponent(ChatFormatting.GRAY + "https://patch.joshepstein.co.uk/trophies").withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://patch.joshepstein.co.uk/trophies")).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent("https://patch.joshepstein.co.uk/trophies")))), context.getSource().getPlayerOrException().getUUID());
            return 1;
        });
    }

    @Override
    public boolean isDedicatedServerOnly() {
        return false;
    }
}
