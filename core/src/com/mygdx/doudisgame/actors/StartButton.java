package com.mygdx.doudisgame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.doudisgame.stages.GameStage;
import com.mygdx.doudisgame.utils.Constants;

public class StartButton extends Actor{

	private Texture texture;
	private Rectangle bounds;
	
	
	/**
	 * Creates a new button that reload the game when pressed.
	 * @param rect with values to draw on
	 * @param gam to reset on press
	 */
	public StartButton(Rectangle rect, final GameStage gam){
		
		this.bounds = rect;
		setWidth(bounds.getWidth());
		setHeight(bounds.getHeight());
		setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
		this.texture = new Texture(Gdx.files.internal(Constants.RESTART_IMAGE_PATH));
		
		 this.addListener(new InputListener() {
	            @Override
	            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	            	
	                gam.reset();
	               
	                return true;
	            }
	        });
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
			
			super.draw(batch, parentAlpha);
			batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
			
			
	}
	

	
	
}
