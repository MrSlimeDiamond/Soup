package net.slimediamond.soup.inject.modules;

import com.google.inject.AbstractModule;
import net.slimediamond.soup.database.Database;
import net.slimediamond.soup.inject.providers.DatabaseProvider;

public class SoupModule extends AbstractModule {
    private Database database;
    public SoupModule(Database database) {
        this.database = database;
    }
    @Override
    protected void configure() {
        bind(Database.class).toProvider(new DatabaseProvider(database));
    }
}
