package com.danlju.nifelholt.ecs;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author danlju
 */
public class GameWorld {

    private final List<SubSystem> subSystems = new ArrayList<>();
    private final List<Entity> entities = new ArrayList<>();

    public GameWorld() {
    }

    /**
     * Runs initialize for all sub-systems
     */
    public void initialize() {
        subSystems.forEach(SubSystem::initialize); // TODO: initialize here?
    }

    /**
     * Adds and initializes a sub-system
     */
    public void addSystem(final SubSystem subSystem) {
        subSystem.setGameWorld(this);
        subSystems.add(subSystem);
    }

    /**
     * Adds a game object and notifies listeners
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        entityAddedEvent(entity);
    }

    /**
     * Removes an entity and notifies listeners
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
        entityRemovedEvent(entity);
    }

    /**
     * List of all entities
     */
    public List<Entity> entities(ComponentMatcher matcher) {
        return this.entities.stream().filter(
                entity -> entity.match(matcher)
        ).collect(Collectors.toList());
    }

    /**
     *
     */
    private void entityAddedEvent(final Entity entity) {
        // TODO: only active
        subSystems.stream().filter(SubSystem::isInitialized)
                .forEach(system -> system.handleEvent(new Event(Event.Type.ENTITY_ADDED, entity)));
    }

    /**
     *
     */
    private void entityRemovedEvent(Entity entity) {

        // TODO: only active
        subSystems.stream().filter(SubSystem::isInitialized)
                .forEach(system -> {
                    system.entityRemoved(entity);
                });
    }

    /**
     *
     */
    public void update(float delta) {

        entities.stream()
                .filter(Entity::isActive)
                .forEach(object -> object.update(delta));

        subSystems.forEach(subSystem -> {
            subSystem.addAndRemoveObjects();
            subSystem.update(delta);
        });
    }

    /**
     * Disposes of entities and runs cleanup on each sub-system
     */
    public void cleanup() {
        entities.forEach(Entity::dispose);
        subSystems.forEach(SubSystem::cleanup);
    }
}
