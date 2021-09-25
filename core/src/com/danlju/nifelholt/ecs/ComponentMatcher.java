package com.danlju.nifelholt.ecs;

import java.util.ArrayList;
import java.util.List;

public class ComponentMatcher {

    public MatchType getMatchType() {
        return matchType;
    }

    public enum MatchType {
        ALL, ANY
    }

    public static ComponentMatcher create() {
        return new ComponentMatcher();
    }

    public static <T extends Component> ComponentMatcher forType(Class<T> componentClass) {
        return new ComponentMatcher().and(componentClass);
    }

    private List<Class<? extends Component>> components = new ArrayList<>();
    private MatchType matchType = MatchType.ALL;


    public <T extends Component> ComponentMatcher and(Class<T> componentClass) {
        this.components.add(componentClass);
        return this;
    }

    public ComponentMatcher matchAll() {
        this.matchType = MatchType.ALL;
        return this;
    }

    public ComponentMatcher matchAny() {
        this.matchType = MatchType.ANY;
        return this;
    }

    public List<Class<? extends Component>> getComponents() {
        return components;
    }
}
