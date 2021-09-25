package com.danlju.nifelholt.equipment;

public class Armor {

    public enum Type {
        LIGHT,
        MEDIUM,
        HEAVY
    }

    String name;
    String id;
    Type type;
    int armorClass;

    public Armor(String name, String id, Type type, int armorClass) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.armorClass = armorClass;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getArmorClass() {
        return armorClass;
    }
}
