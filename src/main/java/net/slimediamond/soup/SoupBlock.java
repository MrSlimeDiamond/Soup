package net.slimediamond.soup;

import org.galliumpowered.Gallium;
import org.galliumpowered.world.entity.Player;

import java.util.Date;

public class SoupBlock {
    private SoupActionType actionType;
    private Date date;
    private Player player;
    private String blockId;
    private int x;
    private int y;
    private int z;

    enum SoupActionType {
        BREAK("break"),
        PLACE("place");

        private String type;

        SoupActionType(String type) {
            this.type = type;
        }

        // Optionals would be better but null makes more sense because
        // only two values will probably ever be checked
        public static SoupActionType fromStringType(String type) {
            for (SoupActionType soupActionType : values()) {
                if (soupActionType.type.equals(type)) {
                    return soupActionType;
                }
            }
            return null;
        }
    }

    public SoupBlock(String type, Date date, String uuid, String blockId, String x, String y, String z) {
        this.actionType = SoupActionType.fromStringType(type);
        this.date = date;
        this.player = Gallium.getServer().getOnlinePlayers().stream().filter(p -> p.getUUID().equals(uuid)).findFirst().orElseThrow();
        this.blockId = blockId;
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.z = Integer.parseInt(z);
    }

    public SoupActionType getActionType() {
        return actionType;
    }

    public Date getDate() {
        return date;
    }

    public Player getPlayer() {
        return player;
    }

    public String getBlockId() {
        return blockId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
