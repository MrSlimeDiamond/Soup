package net.slimediamond.soup.listeners;

import com.google.inject.Injector;
import net.slimediamond.soup.database.Database;
import org.galliumpowered.Gallium;

public class Listeners {
    private Injector injector;

    public Listeners(Injector injector) {
        this.injector = injector;
    }

    /**
     * Register listeners
     */
    public void registerListeners() {
        BlockBreakListener blockBreakListener = injector.getInstance(BlockBreakListener.class);
        BlockPlaceListener blockPlaceListener = injector.getInstance(BlockPlaceListener.class);

        Gallium.getEventManager().registerListener(blockBreakListener);
        Gallium.getEventManager().registerListener(blockPlaceListener);
    }
}
