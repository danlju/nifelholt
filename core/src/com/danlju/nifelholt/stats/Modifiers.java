package com.danlju.nifelholt.stats;

import static java.lang.Math.floor;

public class Modifiers {

    public static int get(int attribute) {

        if (attribute == 1) {
            return -5;
        } else if (attribute <= 3) {
            return -4;
        } else if (attribute <= 5) {
            return -3;
        } else if (attribute <= 7) {
            return -2;
        } else if (attribute <= 9) {
            return -1;
        } else if (attribute <= 11) {
            return 0;
        } else if (attribute <= 13) {
            return 1;
        } else if (attribute <= 15) {
            return 2;
        } else if (attribute <= 17) {
            return 3;
        } else if (attribute <= 19) {
            return 4;
        } else if (attribute <= 21) {
            return 5;
        } else if (attribute <= 23) {
            return 6;
        } else if (attribute <= 25) {
            return 7;
        } else if (attribute <= 27) {
            return 8;
        } else if (attribute <= 29) {
            return 9;
        }

        return 10;
    }

    // TODO: fix
    public static int get_simplified(int attribute) {
        int input_start = 1;
        int input_end = 30;
        int output_start = -5;
        int output_end = 10;

        if (attribute > input_end)
            return output_end;

        double slope = 1.0 * (output_end - output_start) / (input_end - input_start);

        return ((int)(output_start + slope * (attribute - input_start)));
    }

    double round(double d) {
        return floor(d + 0.5);
    }
}
