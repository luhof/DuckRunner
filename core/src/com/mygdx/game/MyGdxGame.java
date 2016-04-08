package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private BitmapFont font;
	private TextureAtlas textureAtlas;
	private Animation animation;
	private float elapsedTime;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture(Gdx.files.internal("spritesheet.png"));
		textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet.png"));
		animation = new Animation(1/15f, textureAtlas.getRegions());
		font = new BitmapFont();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		textureAtlas.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		elapsedTime += Gdx.graphics.getDeltaTime();
		//batch.draw(img, 0, 0);
		batch.draw(animation.getKeyFrame(elapsedTime, true), 0, 0);
		font.draw(batch, "CECI EST UN JEU VIDEAL", 200, 200);
		batch.end();
	}
}
