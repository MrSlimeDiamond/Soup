package net.slimediamond.soup.commands;

import com.google.inject.Injector;
import org.galliumpowered.Gallium;
import org.galliumpowered.annotation.Command;
import org.galliumpowered.command.CommandContext;
import org.galliumpowered.command.CommandManager;
import org.galliumpowered.plugin.PluginContainer;

/*
 * /soup base command
 * For parenting commands such as /soup help
 */
public class Commands {
    private PluginContainer pluginContainer;
    private Injector injector;

    public Commands(PluginContainer pluginContainer, Injector injector) {
        this.pluginContainer = pluginContainer;
        this.injector = injector;
    }


    HelpCommand helpCommand;

    /**
     * Register command using the instances defined in this base command class
     */
    public void registerCommands() {
        helpCommand = injector.getInstance(HelpCommand.class);
        LookupCommand lookupCommand = injector.getInstance(LookupCommand.class);
        CommandManager commandManager = Gallium.getCommandManager();
        commandManager.registerCommand(this, pluginContainer);
        commandManager.registerCommand(helpCommand, pluginContainer);
        commandManager.registerCommand(lookupCommand, pluginContainer);
    }
    @Command(aliases = {"soup"}, description = "Soup base command")
    public void baseCommand(CommandContext ctx) {
        // Just call the help command
        helpCommand.helpCommand(ctx);
    }
}
