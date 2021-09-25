package com.danlju.nifelholt.ecs;

import java.util.ArrayList;
import java.util.List;

/***
 * Baseclass for systems
 *
 * @author danlju
 */
public abstract class SubSystem {

    private GameWorld gameWorld;
    private List<Entity> entities;
    private final List<Entity> entitiesToBeAdded = new ArrayList<>();
    private final List<Entity> addQueue = new ArrayList<>();
    private boolean initialized = false;

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public GameWorld world() {
        return gameWorld;
    }

    /**
     *
     */
    void addAndRemoveObjects() {

        while (!addQueue.isEmpty()) {
            entities.remove(addQueue.remove(0));
        }

        while (!entitiesToBeAdded.isEmpty()) {
            entities.add(entitiesToBeAdded.remove(0));
        }
    }

    public void update(float delta) {
        addAndRemoveObjects();

        entities.forEach(entity -> updateEntity(entity, delta));
    }

    public abstract void updateEntity(Entity entity, float delta);

    public void queueAdd(Entity entity) {
        entitiesToBeAdded.add(entity);
    }

    public void queueRemove(Entity entity) {
        addQueue.add(entity);
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void initialize() {
        initialized = true;
    }

    public abstract void handleEvent(Event event);

    public void entityRemoved(Entity entity) {
        if (getEntities().contains(entity)) {
            queueRemove(entity);
        }
    }

    public void cleanup() {
    }
}
