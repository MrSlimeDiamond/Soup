package net.slimediamond.soup.commands;

import net.kyori.adventure.text.Component;
import org.galliumpowered.annotation.Command;
import org.galliumpowered.chat.Colors;
import org.galliumpowered.command.CommandContext;

public class HelpCommand {
    @Command(parent = "soup", aliases = {"help", "h"}, description = "Plugin help")
    public void helpCommand(CommandContext ctx) {
        ctx.getCaller().sendMessage(Component.text(Colors.LIGHT_GREEN + "Soup/Help"));
        ctx.getCaller().sendMessage(Component.text(Colors.LIGHT_GREEN + "/soup help" + Colors.WHITE + " - Shows this"));
        ctx.getCaller().sendMessage(Component.text(Colors.LIGHT_GREEN + "/soup lookup <x> <y> <z>" + Colors.WHITE + " - Look up actions at specific coordinates"));
    }
}
