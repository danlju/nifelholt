package com.danlju.nifelholt.ecs;

import java.util.*;

/**
 * @author danlju
 *
 * A Entity that is nothing but a collection of Components
 */
public class Entity {

    private final String id;

    private final Map<Class<? extends Component>, Component> components = new HashMap<>();

    private final List<Component> componentsToAdd = new ArrayList<>();

    private final List<Class<? extends Component>> componentsToRemove = new ArrayList<>();

    private boolean active;

    public Entity() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);
        active = true; // TODO
    }

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void initialize() {

        while (!componentsToRemove.isEmpty()) {
            remove(componentsToRemove.remove(0));
        }

        while (!componentsToAdd.isEmpty()) {
            add(componentsToAdd.remove(0));
        }
    }

    /**
     * Gets a component of a specific type or throws an exception
     */
    @SuppressWarnings("unchecked")
    public <T extends Component> T get(Class<T> componentClass) {

        if (!hasComponent(componentClass)) {
            throw new IllegalArgumentException("Component of type " + componentClass.getName() + " could not be found.");
        }
        return (T)components.get(componentClass);
    }

    /**
     * Attaches a new Component to the Game Object.
     * Throws an exception if duplicate Component.
     */
    public void attach(Component component) {

        if (hasComponent(component.getClass())) {
            throw new IllegalArgumentException("Component already exists");
        }
        componentsToAdd.add(component);
    }

    /**
     * Removes a Component of the specified type or throws an Exception
     */
    public <T extends Component> void detach(Class<T> componentClass) {

        if (!hasComponent(componentClass)) {
            throw new IllegalArgumentException("Tried removing non-existing component");
        }
        componentsToRemove.add(componentClass);
    }

    public void update(float delta) {

        while (!componentsToRemove.isEmpty()) {
            remove(componentsToRemove.remove(0));
        }

        while (!componentsToAdd.isEmpty()) {
            add(componentsToAdd.remove(0));
        }
    }

    private void add(Component component) {
        // TODO: initialize
        components.put(component.getClass(), component);
    }

    private void remove(Class<? extends Component> cClass) {
        // TODO: cleanup
        components.remove(cClass);
    }

    public <T extends Component> boolean hasComponent(Class<T> componentClass) {
        return components.containsKey(componentClass);
    }

    public final <T extends Component> boolean hasComponents(List<Class<? extends Component>> componentClasses) {
        return componentClasses.stream().allMatch(components::containsKey);
    }

    public final <T extends Component> boolean match(ComponentMatcher filter) {
        if (filter.getMatchType().equals(ComponentMatcher.MatchType.ANY)) {
            return filter.getComponents().stream().anyMatch(components::containsKey);
        }
        return filter.getComponents().stream().allMatch(components::containsKey);
    }

    public void dispose() {
        components.values().forEach(Component::dispose);
    }
}
