package com.mygdx.doudisgame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen{

	private SpriteBatch batch;
	private BitmapFont fnt;
	private Color colorRed;
	private Color colorWhite;
	final Game gam;
	
	public MenuScreen(Game gam){
		this.batch = new SpriteBatch();
		this.fnt = new BitmapFont();
		this.colorRed = new Color(1, 0, 0, 1);
		this.colorWhite = new Color(1, 1, 1, 1);
		this.gam = gam;
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    batch.begin();
	    fnt.setColor(colorWhite);
		fnt.draw(batch, "WELCOME TO THE JAVA GAME !!!!", 200, 200);
		fnt.setColor(colorRed);
		fnt.draw(batch, "Touch the screen to begin", 200, 100);
		batch.end();
		
		if (Gdx.input.isTouched()) {
			dispose();
            gam.setScreen(new GameScreen());
        }
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
}
