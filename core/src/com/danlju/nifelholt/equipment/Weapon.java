package com.danlju.nifelholt.equipment;

public class Weapon {

    private final String id;
    private final String name;
    private final int damage;
    private final int range; // TODO: support range disadvantage

    public Weapon(String name, String id, int damage, int range) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.range = range;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}

