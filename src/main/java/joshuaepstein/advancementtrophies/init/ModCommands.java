package joshuaepstein.advancementtrophies.init;

import com.mojang.brigadier.CommandDispatcher;
import joshuaepstein.advancementtrophies.commands.Command;
import joshuaepstein.advancementtrophies.commands.VersionCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.util.function.Supplier;

public class ModCommands {

    public static VersionCommand VERSION;

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection env) {
        VERSION = registerCommand(VersionCommand::new, dispatcher, env);
    }

    public static <T extends Command> T registerCommand(Supplier<T> supplier, CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection env) {
        Command command = supplier.get();
        if (!command.isDedicatedServerOnly() || env == Commands.CommandSelection.DEDICATED || env == Commands.CommandSelection.ALL) {
            command.registerCommand(dispatcher, "advancementtrophies");
        }
        return (T) command;
    }

}
