package com.danlju.nifelholt.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.danlju.nifelholt.ecs.ComponentMatcher;
import com.danlju.nifelholt.ecs.Entity;
import com.danlju.nifelholt.ecs.Event;
import com.danlju.nifelholt.ecs.SubSystem;

public class InputSystem extends SubSystem implements InputProcessor {

    public InputSystem() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void initialize() {
        super.initialize();
        setEntities(world().entities(ComponentMatcher.forType(InputComponent.class)));
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
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        InputComponent ic = world().entities(ComponentMatcher.forType(InputComponent.class)).get(0).get(InputComponent.class);
        ic.mouseJustPressed = true;
        ic.mouseJustPressedHandled = false;

        return true;
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
