package com.danlju.nifelholt.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.danlju.nifelholt.animation.AnimationComponent;
import com.danlju.nifelholt.ecs.ComponentMatcher;
import com.danlju.nifelholt.ecs.Entity;
import com.danlju.nifelholt.ecs.Event;
import com.danlju.nifelholt.ecs.SubSystem;
import com.danlju.nifelholt.transform.TransformComponent;

/***
 *
 */
public class RenderSystem extends SubSystem {

    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final ComponentMatcher matcher = ComponentMatcher.create()
            .and(AnimationComponent.class)
            .and(TransformComponent.class)
            .matchAll();

    public RenderSystem(OrthographicCamera camera) {

        this.spriteBatch = new SpriteBatch();
        this.camera = camera;
        this.spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void initialize() {

        super.initialize();
        setEntities(world().entities(matcher));
    }

    @Override
    public void handleEvent(Event event) {

        if (event.getType() == Event.Type.ENTITY_ADDED &&
                event.getEntity().hasComponents(matcher.getComponents())) {
           queueAdd(event.getEntity());
        }
    }

    @Override
    public void update(float delta) {
        this.spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        getEntities().forEach(entity -> spriteBatch.draw(entity.get(AnimationComponent.class).currentFrame(),
                entity.get(TransformComponent.class).getPosition().x,
                entity.get(TransformComponent.class).getPosition().y));
        spriteBatch.end();
    }

    @Override
    public void updateEntity(Entity entity, float delta) {
        // noop
    }
}
