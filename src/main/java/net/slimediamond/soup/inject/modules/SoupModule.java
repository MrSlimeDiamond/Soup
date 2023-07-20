package net.slimediamond.soup.inject.modules;

import com.google.inject.AbstractModule;
import net.slimediamond.soup.database.Database;
import net.slimediamond.soup.inject.providers.DatabaseProvider;
import net.slimediamond.soup.inject.providers.LoggerProvider;
import org.apache.logging.log4j.Logger;

public class SoupModule extends AbstractModule {
    private Database database;
    private Logger logger;
    public SoupModule(Database database, Logger logger) {
        this.database = database;
        this.logger = logger;
    }
    @Override
    protected void configure() {
        bind(Database.class).toProvider(new DatabaseProvider(database));
        bind(Logger.class).toProvider(new LoggerProvider(logger));
    }
}
