package com.danlju.nifelholt.rng;

import java.util.Random;

import static com.danlju.nifelholt.rng.RngUtil.random;

public class Dice {

    public static int d20() {
        return 1 + random.nextInt(20);
    }

    public static int d6() {
        return 1 + random.nextInt(6);
    }

    public static int d(int max) {
        return 1 + random.nextInt(max);
    }
}
