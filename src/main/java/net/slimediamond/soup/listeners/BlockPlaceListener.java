package net.slimediamond.soup.listeners;

import com.google.inject.Inject;
import net.slimediamond.soup.database.Database;
import org.galliumpowered.annotation.EventListener;
import org.galliumpowered.event.player.PlayerPlaceBlockEvent;

import java.sql.SQLException;

public class BlockPlaceListener {
    @Inject
    private Database database;
    @EventListener
    public void onBlockPlace(PlayerPlaceBlockEvent event) throws SQLException {
        if (!event.isCancelled()) {
            database.insertBlockPlace(event.getBlock(), event.getPlayer());
        }
    }
}
