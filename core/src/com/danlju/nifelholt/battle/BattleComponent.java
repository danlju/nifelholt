package com.danlju.nifelholt.battle;

import com.danlju.nifelholt.ecs.Component;

public class BattleComponent implements Component {

    public static final int NOT_ROLLED = -100;

    public int initiative = NOT_ROLLED;
}
