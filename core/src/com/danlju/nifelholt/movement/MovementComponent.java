package com.danlju.nifelholt.movement;

import com.danlju.nifelholt.ecs.Component;

public class MovementComponent implements Component {

    public static final float MAX_SPEED = 100f; // TODO

    public boolean moveUp = false;
    public boolean moveLeft = false;
    public boolean moveRight = false;
    public boolean moveDown = false;
}
