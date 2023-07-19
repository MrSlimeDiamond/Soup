package net.slimediamond.soup.inject.providers;

import com.google.inject.Provider;
import net.slimediamond.soup.database.Database;

public class DatabaseProvider implements Provider<Database> {
    private Database database;
    public DatabaseProvider(Database database) {
        this.database = database;
    }
    @Override
    public Database get() {
        return database;
    }
}
