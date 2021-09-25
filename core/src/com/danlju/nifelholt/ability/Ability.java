package com.danlju.nifelholt.ability;

public class Ability {

    public enum DamageType {
        PHYSICAL,
        FIRE,
        FROST
    }

    public String name;
    public String id;
    public DamageType damageType;
    public int damageRoll;
    public int rangeFt;
    public int actionCost;
    public int cooldown;

    public Ability(String name, String id, DamageType damageType, int damageRoll, int rangeFt, int actionCost, int cooldown) {
        this.name = name;
        this.id = id;
        this.damageType = damageType;
        this.damageRoll = damageRoll;
        this.rangeFt = rangeFt;
        this.actionCost = actionCost;
        this.cooldown = cooldown;
    }


    // TODO: move these
    // TODO: check cooldowns
    public static Ability fireball() {
        return new Ability("Fireball", "fireball", DamageType.FIRE, 6, 150, 1, 2);
    }

    public static Ability rayOfFrost() {
        return new Ability("Ray of frost", "ray_of_frost", DamageType.FROST, 8, 60, 1, 2);
    }

}