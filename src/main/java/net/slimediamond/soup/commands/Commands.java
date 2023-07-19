package net.slimediamond.soup.commands;

import org.galliumpowered.annotation.Command;
import org.galliumpowered.command.CommandContext;
import org.galliumpowered.command.PluginCommandManager;

/*
 * /soup base command
 * For parenting commands such as /soup help
 */
public class Commands {
    private PluginCommandManager commandManager;

    public Commands(PluginCommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /*
     * Command instances
     */
    HelpCommand helpCommand = new HelpCommand();

    /**
     * Register command using the instances defined in this base command class
     */
    public void registerCommands() {
        commandManager.registerCommand(this);
        commandManager.registerCommand(helpCommand);
    }
    @Command(aliases = {"soup"}, description = "Soup base command")
    public void baseCommand(CommandContext ctx) {
        // Just call the help command
        helpCommand.helpCommand(ctx);
    }
}
