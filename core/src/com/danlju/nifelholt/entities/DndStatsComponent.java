package com.danlju.nifelholt.entities;

import com.danlju.nifelholt.ecs.Component;

import java.util.HashMap;
import java.util.Map;

public class DndStatsComponent implements Component {

    private final Map<String, Integer> attributes = new HashMap<>();

    private int hitPoints;

    private int armorClass;

    private int proficiencyBonus;

    private int walkingSpeed;

    public DndStatsComponent() {
        this(0, 0, 0, 0);
        setAttributes(0, 0, 0, 0, 0, 0);
    }

    public DndStatsComponent(int hitPoints, int armorClass, int proficiencyBonus, int walkingSpeed) {
        this.hitPoints = hitPoints;
        this.armorClass = armorClass;
        this.proficiencyBonus = proficiencyBonus;
        this.walkingSpeed = walkingSpeed;
    }

    public void setAttributes(int str, int dex, int con, int intel, int wis, int charis) {
        attributes.put("str", str);
        attributes.put("dex", dex);
        attributes.put("con", con);
        attributes.put("int", intel);
        attributes.put("wis", wis);
        attributes.put("char", charis);
    }

    public Integer get(final String attr) {
        return attributes.get(attr);
    }

    public void set(final String attr, final Integer value) {
        attributes.put(attr, value);
    }


    public int getHitPoints() {
        return hitPoints;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public int getWalkingSpeed() {
        return walkingSpeed;
    }
}
