package com.mygdx.doudisgame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.doudisgame.box2d.GroundUserData;
import com.mygdx.doudisgame.enums.GameState;
import com.mygdx.doudisgame.utils.Constants;
import com.mygdx.doudisgame.utils.GameManager;

public class Ground extends GameActor{
	
	private final TextureRegion textureRegion;
	private Rectangle textureRegionBounds1;
	private Rectangle textureRegionBounds2;
	private int speed = 10;
	
	/**
	 * Creates a ground object with two region bounds so it can scroll endlessly
	 * @param body
	 */
	public Ground(Body body){
		super(body);
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_IMAGE_PATH)));
		textureRegionBounds1 = new Rectangle(0-getUserData().getWidth() / 2, 0, getUserData().getWidth(), getUserData().getHeight());
		textureRegionBounds2 = new Rectangle(getUserData().getWidth() / 2, 0, getUserData().getWidth(), getUserData().getHeight());
	}
	
	@Override
	public GroundUserData getUserData(){
		return (GroundUserData) userData;
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		if(leftBoundsReached(delta)){
			resetBounds();
		}
		else if(GameManager.getInstance().getGameState() == GameState.RUNNING){
			updateXBounds(-delta);
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, textureRegionBounds1.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
		batch.draw(textureRegion, textureRegionBounds2.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
	}
	
	/**
	 * 
	 * @param delta
	 * @return true if the ground has reached the left of the screen
	 */
	private boolean leftBoundsReached(float delta){
		return (textureRegionBounds2.x - transformToScreen(delta*speed)) <=0;
	}
	
	/**
	 * update textures' x position based on speed
	 * @param delta
	 */
	private void updateXBounds(float delta){
		textureRegionBounds1.x += transformToScreen(delta * speed);
		textureRegionBounds2.x += transformToScreen(delta * speed);
	}
	
	/**
	 * Set ground bounds to their base position.
	 */
	private void resetBounds(){
		textureRegionBounds1 = textureRegionBounds2;
		textureRegionBounds2 = new Rectangle(textureRegionBounds1.x + screenRectangle.width, 0, screenRectangle.width, screenRectangle.height);
	}

}
