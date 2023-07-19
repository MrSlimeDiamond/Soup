package net.slimediamond.soup.listeners;

import com.google.inject.Inject;
import net.slimediamond.soup.database.Database;
import org.galliumpowered.annotation.EventListener;
import org.galliumpowered.event.player.PlayerBreakBlockEvent;

import java.sql.SQLException;

public class BlockBreakListener {
    @Inject
    private Database database;
    @EventListener
    public void onBlockBreak(PlayerBreakBlockEvent event) throws SQLException {
        if (!event.isCancelled()) {
            database.insertBlockBreak(event.getBlock(), event.getPlayer());
        }
    }
}
