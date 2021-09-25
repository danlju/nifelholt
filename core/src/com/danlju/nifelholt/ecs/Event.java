package com.danlju.nifelholt.ecs;

/**
 * @author danlju
 */
public class Event {

    public enum Type {
        ENTITY_ADDED,
        ENTITY_REMOVED
    }

    private final Type type;
    private final Entity entity;

    public Event(Type type, Entity entity) {
        this.type = type;
        this.entity = entity;
    }

    public Type getType() {
        return type;
    }

    public Entity getEntity() {
        return entity;
    }
}
