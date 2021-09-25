package com.danlju.nifelholt.entities;

import java.util.List;

public enum CharClass {

    FIGHTER,
    RANGER,
    ROGUE,
    SORCERER;

    public static final List<CharClass> cachedValues = List.of(values());

    public static final int valuesSize = cachedValues.size();
}

