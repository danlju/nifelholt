package com.danlju.nifelholt.entities;

import com.danlju.nifelholt.ecs.Component;

public class DndCharComponent implements Component {

    private String name;
    private Race race;
    private CharClass charClass;

    public DndCharComponent(String name, Race race, CharClass charClass) {
        this.name = name;
        this.race = race;
        this.charClass = charClass;
    }
}
