package com.danlju.nifelholt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.danlju.nifelholt.battle.BattleSystem;
import com.danlju.nifelholt.battle.PartyMemberComponent;
import com.danlju.nifelholt.camera.GameCameraComponent;
import com.danlju.nifelholt.camera.GameCameraSystem;
import com.danlju.nifelholt.ecs.Entity;
import com.danlju.nifelholt.ecs.GameWorld;
import com.danlju.nifelholt.entities.EntityFactory;
import com.danlju.nifelholt.input.InputSystem;
import com.danlju.nifelholt.rendering.RenderSystem;
import com.danlju.nifelholt.tilemap.TilemapSystem;

public class PlayScreen implements Screen {

    public static final float SCALE = 1;///2f;

    private final NifelholtGame game;
    private Viewport viewport;
    private GameWorld gameWorld;
    private OrthographicCamera gameCamera;

    public PlayScreen(NifelholtGame nifelholtGame) {
        this.game = nifelholtGame;
    }

    @Override
    public void show() {

        Stage stage = new Stage(new ScreenViewport());

        final float width = Gdx.graphics.getWidth();
        final float height = Gdx.graphics.getHeight();

        gameCamera = (OrthographicCamera) stage.getViewport().getCamera();
        gameCamera.setToOrtho(false, width * SCALE, height * SCALE);
        gameCamera.update();

        Entity gameCameraEntity = new Entity();
        gameCameraEntity.attach(new GameCameraComponent(gameCamera));
        gameCameraEntity.initialize();

        gameWorld = new GameWorld();
        gameWorld.addEntity(gameCameraEntity);
        gameWorld.addSystem(new TilemapSystem(gameCamera, "arena.tmx"));
        gameWorld.addSystem(new RenderSystem(gameCamera)); // TODO: remove parameters and use get component instead?
        gameWorld.addSystem(new InputSystem());
        gameWorld.addSystem(new GameCameraSystem());
        gameWorld.addSystem(new BattleSystem(500));

        gameWorld.initialize();

        EntityFactory entityFactory = new EntityFactory();
        entityFactory.randomParty("Heroes", 4).forEach(gameWorld::addEntity);
        entityFactory.randomParty("Goblins", 4).forEach(gameWorld::addEntity);

        Gdx.app.log("PlayScreen", "Initialization done");
    }

    @Override
    public void render(float delta) {
        gameWorld.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameWorld.cleanup();
    }
}
