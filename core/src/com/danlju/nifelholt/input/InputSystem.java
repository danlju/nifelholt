package com.danlju.nifelholt.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.danlju.nifelholt.ecs.Entity;
import com.danlju.nifelholt.ecs.Event;
import com.danlju.nifelholt.ecs.SubSystem;

/***
 * Handle keyboard and controller input.
 * Modifies {@link com.danlju.nifelholt.movement.MovementComponent}
 * TODO: which class?
 */
public class InputSystem extends SubSystem implements InputProcessor {

    private boolean upButtonPressed = false;
    private boolean leftButtonPressed = false;
    private boolean rightButtonPressed = false;
    private boolean downButtonPressed = false;

    public InputSystem() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void updateEntity(Entity entity, float delta) {
        // noop
    }

    @Override
    public void handleEvent(Event event) {

    }

    /* KEYBOARD */

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.SPACE) {
            upButtonPressed = true;
        }
        if (keycode == Input.Keys.DOWN) {
            downButtonPressed = true;
        }
        if (keycode == Input.Keys.LEFT) {
            leftButtonPressed = true;
        }
        if (keycode == Input.Keys.RIGHT) {
            rightButtonPressed = true;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.SPACE) {
            Gdx.app.log("keyUp", "space");
            upButtonPressed = false;
        }
        if (keycode == Input.Keys.LEFT) {
            leftButtonPressed = false;
        }
        if (keycode == Input.Keys.RIGHT) {
            rightButtonPressed = false;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
