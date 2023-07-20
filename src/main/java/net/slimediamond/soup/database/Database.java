package net.slimediamond.soup.database;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.slimediamond.soup.SoupBlock;
import org.apache.logging.log4j.Logger;
import org.galliumpowered.exceptions.PluginException;
import org.galliumpowered.world.block.WorldBlock;
import org.galliumpowered.world.entity.Player;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class Database {
    private String connectionString;
    private Logger logger;

    public Database(String connectionString, Logger logger) {
        this.connectionString = connectionString;
        this.logger = logger;
    }

    private Connection conn;

    private PreparedStatement insertBlockBreak;
    private PreparedStatement insertBlockPlace;
    private PreparedStatement getBlockHistory;

    public void open() throws SQLException {
        conn = DriverManager.getConnection(connectionString);

        conn.prepareStatement("CREATE TABLE IF NOT EXISTS blocklog (type TINYTEXT, time TIMESTAMP, uuid MEDIUMTEXT, block_id TINYTEXT, world TINYTEXT, x TINYTEXT, y TINYTEXT, z TINYTEXT)").execute();

        insertBlockBreak = conn.prepareStatement("INSERT INTO blocklog (type, time, uuid, block_id, world, x, y, z) VALUES ('break', ?, ?, ?, ?, ?, ?, ?)");
        insertBlockPlace = conn.prepareStatement("INSERT INTO blocklog (type, time, uuid, block_id, world, x, y, z) VALUES ('place', ?, ?, ?, ?, ?, ?, ?)");
        getBlockHistory = conn.prepareStatement("SELECT * FROM blocklog WHERE world = ? AND x = ? AND y = ? AND z = ?");
    }

    public void insertBlockBreak(WorldBlock block, Player player) throws SQLException {
        insertBlockBreak.setTimestamp(1, new Timestamp(Instant.now().getEpochSecond()));
        insertBlockBreak.setString(2, player.getUUID());
        insertBlockBreak.setString(3, block.getId());
        insertBlockBreak.setString(4, "world"); // TODO: Actual world ID. Will need changes in Gallium.
        insertBlockBreak.setString(5, Integer.toString(block.getX()));
        insertBlockBreak.setString(6, Integer.toString(block.getY()));
        insertBlockBreak.setString(7, Integer.toString(block.getZ()));
        insertBlockBreak.execute();
    }

    public void insertBlockPlace(WorldBlock block, Player player) throws SQLException {
        insertBlockPlace.setTimestamp(1, new Timestamp(Instant.now().getEpochSecond()));
        insertBlockPlace.setString(2, player.getUUID());
        insertBlockPlace.setString(3, block.getId());
        insertBlockPlace.setString(4, "world"); // TODO: Actual world ID. Will need changes in Gallium.
        insertBlockPlace.setString(5, Integer.toString(block.getX()));
        insertBlockPlace.setString(6, Integer.toString(block.getY()));
        insertBlockPlace.setString(7, Integer.toString(block.getZ()));
        insertBlockPlace.execute();
    }

    public ArrayList<SoupBlock> getBlockHistory(String world, int x, int y, int z) {
        try {
            ArrayList<SoupBlock> blocks = new ArrayList<>();
            getBlockHistory.setString(1, world);
            getBlockHistory.setString(2, Integer.toString(x));
            getBlockHistory.setString(3, Integer.toString(y));
            getBlockHistory.setString(4, Integer.toString(z));
            ResultSet rs = getBlockHistory.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                String uuid = rs.getString("uuid");
                String blockId = rs.getString("block_id");
                Timestamp time = rs.getTimestamp("time");

                Date date = new Date(time.getTime() * 1000);

                blocks.add(new SoupBlock(type, date, uuid, blockId, Integer.toString(x), Integer.toString(y), Integer.toString(z)));
            }
            return blocks;
        } catch (SQLException e) {
            throw new PluginException(e);
        }
    }
}
