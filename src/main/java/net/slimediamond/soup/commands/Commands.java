package net.slimediamond.soup.commands;

import com.google.inject.Injector;
import org.galliumpowered.annotation.Command;
import org.galliumpowered.command.CommandContext;
import org.galliumpowered.command.PluginCommandManager;

/*
 * /soup base command
 * For parenting commands such as /soup help
 */
public class Commands {
    private PluginCommandManager commandManager;
    private Injector injector;

    public Commands(PluginCommandManager commandManager, Injector injector) {
        this.commandManager = commandManager;
        this.injector = injector;
    }


    HelpCommand helpCommand;

    /**
     * Register command using the instances defined in this base command class
     */
    public void registerCommands() {
        helpCommand = injector.getInstance(HelpCommand.class);
        LookupCommand lookupCommand = injector.getInstance(LookupCommand.class);
        commandManager.registerCommand(this);
        commandManager.registerCommand(helpCommand);
        commandManager.registerCommand(lookupCommand);
    }
    @Command(aliases = {"soup"}, description = "Soup base command")
    public void baseCommand(CommandContext ctx) {
        // Just call the help command
        helpCommand.helpCommand(ctx);
    }
}
