package net.slimediamond.soup.database;

import org.galliumpowered.world.block.WorldBlock;
import org.galliumpowered.world.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private String connectionString;

    public Database(String connectionString) {
        this.connectionString = connectionString;
    }

    private Connection conn;

    private PreparedStatement insertBlockBreak;
    private PreparedStatement insertBlockPlace;

    public void open() throws SQLException {
        conn = DriverManager.getConnection(connectionString);

        // TODO: auto create tables

        insertBlockBreak = conn.prepareStatement("INSERT INTO blocklog (type, uuid, block_id, world, x, y, z) VALUES ('break', ?, ?, ?, ?, ?, ?)");
        insertBlockPlace = conn.prepareStatement("INSERT INTO blocklog (type, uuid, block_id, world, x, y, z) VALUES ('place', ?, ?, ?, ?, ?, ?)");
    }

    public void insertBlockBreak(WorldBlock block, Player player) throws SQLException {
        insertBlockBreak.setString(1, player.getUUID());
        insertBlockBreak.setString(2, block.getId());
        insertBlockBreak.setString(3, "world"); // TODO: Actual world ID. Will need changes in Gallium.
        insertBlockBreak.setString(3, Integer.toString(block.getX()));
        insertBlockBreak.setString(4, Integer.toString(block.getY()));
        insertBlockBreak.setString(5, Integer.toString(block.getZ()));
    }
}
