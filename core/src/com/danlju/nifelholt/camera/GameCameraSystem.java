package com.danlju.nifelholt.camera;

import com.danlju.nifelholt.ecs.SubSystem;
import com.danlju.nifelholt.ecs.ComponentMatcher;
import com.danlju.nifelholt.ecs.Entity;
import com.danlju.nifelholt.ecs.Event;

public class GameCameraSystem extends SubSystem {

    @Override
    public void initialize() {
        super.initialize();
        this.setEntities(world().entities(ComponentMatcher.forType(GameCameraComponent.class)));
    }

    @Override
    public void updateEntity(Entity entity, float delta) {

        // TODO: decide how to get the components
        var cc = world().entities(ComponentMatcher.forType(GameCameraComponent.class)).get(0).get(GameCameraComponent.class);
        cc.getCamera().update();

    }

    @Override
    public void handleEvent(Event event) {

    }
}
