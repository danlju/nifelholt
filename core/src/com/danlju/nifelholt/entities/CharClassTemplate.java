package com.danlju.nifelholt.entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharClassTemplate {
    
    public List<String> attributePriority;


    public static Map<CharClass, CharClassTemplate> archetypes;

    public CharClassTemplate(List<String> attributePriority) {
        this.attributePriority = attributePriority;
    }

    static {

        archetypes = new HashMap<>();

        // TODO: omission means random priority?

        List<String> fighterPrio = Arrays.asList("str", "con", "dex", "wis", "int", "cha");
        archetypes.put(CharClass.FIGHTER, new CharClassTemplate(fighterPrio));

        List<String> rangerPrio = Arrays.asList("dex", "con", "wis", "int", "str", "cha");
        archetypes.put(CharClass.RANGER, new CharClassTemplate(rangerPrio));

        List<String> roguePrio = Arrays.asList("dex", "str", "con", "wis", "int", "cha");
        archetypes.put(CharClass.ROGUE, new CharClassTemplate(roguePrio));

        List<String> sorcPrio = Arrays.asList("cha", "con", "dex", "wis", "str", "int");
        archetypes.put(CharClass.SORCERER, new CharClassTemplate(sorcPrio));

    }
}
