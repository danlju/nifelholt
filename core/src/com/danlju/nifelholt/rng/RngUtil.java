package com.danlju.nifelholt.rng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RngUtil {

    public static final Random random = new Random(System.currentTimeMillis());

    public static int statRoll() {

        int lowest = 6;
        int total = 0;

        for (int i=0; i<4; i++) {

            int roll = Dice.d6();

            total += roll;

            if (roll < lowest)
                lowest = roll;
        }

        total -= lowest;

        return total;
    }

    public static List<Integer> sortedAttributeRolls() {
        return sortedAttributeRolls(0);
    }

    public static List<Integer> sortedAttributeRolls(int penalty) {

        List<Integer> rolls = new ArrayList<>();

        for (int i=0; i<6; i++) {
            rolls.add(RngUtil.statRoll() - penalty);
        }

        rolls.sort(Collections.reverseOrder());

        return rolls;
    }
}