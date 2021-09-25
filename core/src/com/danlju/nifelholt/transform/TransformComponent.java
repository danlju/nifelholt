package com.danlju.nifelholt.transform;

import com.badlogic.gdx.math.Vector2;
import com.danlju.nifelholt.ecs.Component;

public class TransformComponent implements Component {

    private Vector2 position = new Vector2();
    private int width;
    private int height;

    public TransformComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
