package net.slimediamond.soup;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import net.slimediamond.soup.commands.Commands;
import net.slimediamond.soup.database.Database;
import net.slimediamond.soup.inject.modules.SoupModule;
import net.slimediamond.soup.listeners.Listeners;
import org.apache.logging.log4j.Logger;
import org.galliumpowered.annotation.Plugin;
import org.galliumpowered.annotation.PluginLifecycleListener;
import org.galliumpowered.command.PluginCommandManager;
import org.galliumpowered.config.Configuration;
import org.galliumpowered.config.PluginConfiguration;
import org.galliumpowered.plugin.PluginContainer;
import org.galliumpowered.plugin.PluginLifecycleState;

@Plugin(
        name = "Soup",
        id = "soup",
        description = "Plugin for viewing block break and place logs",
        version = "1.0-SNAPSHOT",
        authors = "SlimeDiamond"
)
public class Soup {
    @Inject
    private Logger logger;

    @Inject
    private PluginCommandManager commandManager;

    @Inject
    private PluginContainer container;

    @PluginLifecycleListener(PluginLifecycleState.ENABLED)
    public void onPluginEnable() {
        Configuration config = new PluginConfiguration(container);
        Database database = new Database(config.getValue("db_connection"));
        Injector injector = Guice.createInjector(new SoupModule(database));

        // Call command registration provided in Commands
        // The instance shouldn't need to be used again
        new Commands(commandManager).registerCommands();

        // Listener registering
        new Listeners(injector);
    }
}
