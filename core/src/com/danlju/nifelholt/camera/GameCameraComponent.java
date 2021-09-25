package com.danlju.nifelholt.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.danlju.nifelholt.ecs.Component;

/**
 * @author danlju
 */
public class GameCameraComponent implements Component {

    private final OrthographicCamera camera;

    public GameCameraComponent(OrthographicCamera camera) {
        this.camera = camera;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
