package net.slimediamond.soup.commands;

import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import net.slimediamond.soup.database.Database;
import org.apache.logging.log4j.Logger;
import org.galliumpowered.annotation.Args;
import org.galliumpowered.annotation.Command;
import org.galliumpowered.chat.Colors;
import org.galliumpowered.command.CommandCaller;
import org.galliumpowered.command.CommandContext;
import org.galliumpowered.command.args.ArgumentType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class LookupCommand {

    @Inject
    private Database database;

    @Inject
    private Logger logger;

    @Command(
            parent = "soup",
            aliases = {"l", "lookup"},
            description = "Look up specific coordinates"
    )
    public void lookupCommand(CommandContext ctx) {
        if (ctx.getCommandArgs().length <  5) {
            sendIncorrectUsage(ctx.getCaller());
            return;
        }
        int x = Integer.parseInt(ctx.getCommandArgs()[2]);
        int y = Integer.parseInt(ctx.getCommandArgs()[3]);
        int z = Integer.parseInt(ctx.getCommandArgs()[4]);

        ctx.getCaller().sendMessage(Component.text(Colors.LIGHT_GREEN + "Soup/Lookup"));
        logger.info("Looking up block at {} {} {}", x, y, z);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        database.getBlockHistory("world", x, y, z).forEach(block -> {
            ctx.getCaller().sendMessage(Component.text(
                    Colors.LIGHT_GRAY + dateFormat.format(block.getDate()) + Colors.WHITE + " " +
                            block.getPlayer().getName() + " "  +
                            block.getActionType() + " " +
                            block.getBlockId()
            ));
        });
    }

    private void sendIncorrectUsage(CommandCaller caller) {
        caller.sendMessage(Component.text(Colors.LIGHT_RED + "Usage: /soup lookup <x> <y> <z>"));
    }
}
