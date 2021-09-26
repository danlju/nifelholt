package com.danlju.nifelholt.tilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.danlju.nifelholt.ecs.Entity;
import com.danlju.nifelholt.ecs.Event;
import com.danlju.nifelholt.ecs.SubSystem;

public class TilemapSystem extends SubSystem {

    private final TiledMapRenderer mapRenderer;
    private final OrthographicCamera camera;
    private TiledMap tiledMap;

    public TilemapSystem(OrthographicCamera camera, String filepath) {

        this.camera = camera;
        this.tiledMap = new TmxMapLoader().load(filepath);

        this.mapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        this.mapRenderer.setView(camera);
    }

    public void update(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    @Override
    public void updateEntity(Entity entity, float delta) {
        // TODO: ?
    }

    @Override
    public void handleEvent(Event event) {
    }

    @Override
    public void cleanup() {
        super.cleanup();
        tiledMap.dispose();
    }
}