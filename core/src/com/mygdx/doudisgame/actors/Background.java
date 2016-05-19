package com.mygdx.doudisgame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.doudisgame.enums.GameState;
import com.mygdx.doudisgame.utils.Constants;
import com.mygdx.doudisgame.utils.GameManager;

public class Background extends Actor{
	
	private final TextureRegion textureRegion;
	private Rectangle textureRegionBounds1;
	private Rectangle textureRegionBounds2;
	private int speed = 100;
	
	/**
	 * Creates a new background object, with 2 textureregions, allowing it to scroll endlessly.
	 */
	public Background(){
		textureRegion = new TextureRegion(new Texture(Constants.BACKGROUND_IMAGE_PATH));
		textureRegionBounds1 = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT );
		textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT );
	}
	
	
	@Override
	public void act(float delta){
		if(leftBoundsReached(delta)){
			resetBounds();
		}
		else if(GameManager.getInstance().getGameState() == GameState.RUNNING){
			updateXBounds(-delta);
		}
	}
	
	/**
	 * Draws the moving background
	 * 
	 */
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Constants.APP_WIDTH, Constants.APP_HEIGHT);
	}
	
	/**
	 * 
	 * @param delta
	 * @return true if the background image has reached the left of the screen
	 */
	private boolean leftBoundsReached(float delta){
		return (textureRegionBounds2.x - (delta*speed)) <= 0;
	}
	
	private void updateXBounds(float delta){
		textureRegionBounds1.x += delta * speed;
		textureRegionBounds2.x += delta * speed;
	}
	
	private void resetBounds(){
		textureRegionBounds1 = textureRegionBounds2;
		textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
	}

}
