package com.danlju.nifelholt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NifelholtGame extends Game {

	@Override
	public void create () {
		this.setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		screen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose () {
		screen.dispose();
	}
}
