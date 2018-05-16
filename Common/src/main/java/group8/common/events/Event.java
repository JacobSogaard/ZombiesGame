package group8.common.events;

import group8.common.data.Entity;
import java.io.Serializable;

/**
 *
 * @author group8
 */
public class Event implements Serializable{
    private final Entity source;

    public Event(Entity source) {
        this.source = source;
    }

    public Entity getSource() {
        return source;
    }
}
