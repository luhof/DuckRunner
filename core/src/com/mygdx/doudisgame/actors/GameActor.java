package com.mygdx.doudisgame.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.doudisgame.box2d.UserData;
import com.mygdx.doudisgame.utils.Constants;

public abstract class GameActor extends Actor{

	protected Body body;
	protected UserData userData;
	protected Rectangle screenRectangle;
	
	public GameActor(Body body){
		this.body = body;
		this.userData = (UserData) body.getUserData();
		this.screenRectangle = new Rectangle();
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		
		if(body.getUserData() != null){
			updateRectangle();
		}
		else{
			//if user data is unll, then body has been destroyed ! so we can remove the actor
			remove();
		}
	}
	
	/**
	 * Adapt actor position to display correctly on different screen resolutions.
	 */
	private void updateRectangle() {
		screenRectangle.x = transformToScreen(body.getPosition().x - userData.getWidth() / 2);
		screenRectangle.y = transformToScreen(body.getPosition().y - userData.getHeight() / 2);
		screenRectangle.width = transformToScreen(userData.getWidth());
		screenRectangle.height = transformToScreen(userData.getHeight());
	}

	/**
	 * 
	 * @return object's user data, depending on its type
	 */
	public abstract UserData getUserData();
	
	/**
	 * 
	 * @param n to convert.
	 * @return n multiplied by a world to screen constant
	 */
	protected float transformToScreen(float n){
		return Constants.WORLD_TO_SCREEN * n;
	}
	
}
