package com.danlju.nifelholt.equipment;

import com.danlju.nifelholt.ecs.Component;

public class EquipmentComponent implements Component {
    // TODO: equipment slots
    private Armor armor;
    private Weapon weapon;

    public EquipmentComponent(Armor armor, Weapon weapon) {
        this.armor = armor;
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
