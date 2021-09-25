package com.danlju.nifelholt.entities;

import com.danlju.nifelholt.ability.Ability;
import com.danlju.nifelholt.ecs.Component;

import java.util.ArrayList;
import java.util.List;

public class AbilityComponent implements Component {

    public List<Ability> abilities = new ArrayList<>();

    public AbilityComponent(List<Ability> abilities) {
        this.abilities = abilities;
    }
}
