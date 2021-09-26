package com.danlju.nifelholt.entities;

import com.badlogic.gdx.Gdx;
import com.danlju.nifelholt.ability.Ability;
import com.danlju.nifelholt.ecs.Entity;
import com.danlju.nifelholt.equipment.Armor;
import com.danlju.nifelholt.equipment.EquipmentComponent;
import com.danlju.nifelholt.equipment.Weapon;
import com.danlju.nifelholt.rng.RngUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class EntityFactory {

    public Entity randomCharacter(String name) {

        Entity entity = new Entity();

        Race race = Race.HUMAN; // TODO: random race
        CharClass charClass = CharClass.cachedValues.get(RngUtil.random.nextInt(CharClass.valuesSize));

        DndCharComponent charComponent = new DndCharComponent(name, race, charClass);
        entity.attach(charComponent);

        AbilityComponent abilityComponent = new AbilityComponent(abilitiesForClass(charClass));
        entity.attach(abilityComponent);

        EquipmentComponent equipmentComponent = new EquipmentComponent(armorForClass(charClass), weaponForClass(charClass));
        entity.attach(equipmentComponent);

        List<Integer> rolls = RngUtil.sortedAttributeRolls();

        DndStatsComponent statsComponent = new DndStatsComponent();
        int index=0;
        for (String attr : CharClassTemplate.archetypes.get(charClass).attributePriority) {
            statsComponent.set(attr, rolls.get(index++));
        }
        entity.attach(statsComponent);

        entity.initialize();

        return entity;
    }

    private Armor armorForClass(CharClass charClass) {

        if (charClass == CharClass.FIGHTER) {
            return new Armor("Ring mail", "ring_mail", Armor.Type.HEAVY, 14);
        } else if (charClass == CharClass.ROGUE) {
            return new Armor("Leather armor", "leather", Armor.Type.LIGHT, 11);
        } else if (charClass == CharClass.RANGER) {
            return new Armor("Studded leather armor", "studded_leather", Armor.Type.LIGHT, 12);
        } else if (charClass == CharClass.SORCERER) {
            return new Armor("Padded armor", "padded_armor", Armor.Type.LIGHT, 11);
        }

        return new Armor("Padded armor", "padded_armor", Armor.Type.LIGHT, 11);
    }

    private Weapon weaponForClass(CharClass charClass) {

        if (charClass == CharClass.FIGHTER) {
            return new Weapon("Battleaxe", "battleaxe", 8, 5);
        } else if (charClass == CharClass.ROGUE) {
            return new Weapon("Dagger", "dagger", 6, 5);
        } else if (charClass == CharClass.RANGER) {
            return new Weapon("Shortbow", "shortbow", 6, 80);
        } else if (charClass == CharClass.SORCERER) {
            return new Weapon("Quarterstaff", "quarterstaff", 6, 5);
        }

        Gdx.app.log("WARNING", "No weapon for class " + charClass.name());

        return new Weapon("Dagger", "dagger", 6, 5);
    }


    private static List<Ability> abilitiesForClass(CharClass charClass) {

        if (charClass == CharClass.SORCERER) {
            return Arrays.asList(Ability.fireball(), Ability.rayOfFrost());
        }
        return Collections.emptyList();
    }

    public static String randomName() {

        List<String> namePart1 = Arrays.asList("Gor", "Zog", "Yon", "Zea", "Zol", "Ana", "Bil", "Kia", "Ken", "Mia");
        List<String> namePart2 = Arrays.asList("Ta", "Ma", "Ano", "Gur", "Inda", "Ema", "Ani", "Koi", "Mi", "Zea", "Zeo");
        List<String> namePart3 = Arrays.asList("Zi", "Zo", "Ke", "Kib", "Pod", "Kod", "Mod", "Loa", "Cie", "Kimi", "Aia");
        List<List<String>> partLists = Arrays.asList(namePart1, namePart2, namePart3);

        int nParts = 1 + RngUtil.random.nextInt(partLists.size());

        StringBuilder name = new StringBuilder();
        for (int i=0; i<nParts; i++) {

            List<String> partList = partLists.get(RngUtil.random.nextInt(partLists.size()));
            String part = partList.get(RngUtil.random.nextInt(partList.size()));

            if (i > 0) {
                part = part.toLowerCase(Locale.ROOT);
            }

            name.append(part);
        }

        return name.toString();
    }
}
