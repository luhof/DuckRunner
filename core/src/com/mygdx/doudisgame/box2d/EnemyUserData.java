package com.mygdx.doudisgame.box2d;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.doudisgame.enums.UserDataType;
import com.mygdx.doudisgame.utils.Constants;

public class EnemyUserData extends UserData{

	private Vector2 linearVelocity;
	private String[] textureRegions;
	
	/**
	 * Create a new user data for enemy
	 * @param width of the enemy
	 * @param height of the enemy
	 * @param textureRegions - sprites to use
	 */
	public EnemyUserData(float width, float height, String[] textureRegions){
		super(width, height);
		userDataType = UserDataType.ENEMY;
		linearVelocity = Constants.ENEMY_LINEAR_VELOCITY;
		this.textureRegions = textureRegions;
	}

	/**
	 * 
	 * @return enemy speed
	 */
	public Vector2 getLinearVelocity() {
		return linearVelocity;
	}
	
	/**
	 * 
	 * @return array of textureRegions used to draw the enemy
	 */
	public String[] getTextureRegions(){
		return textureRegions;
	}

	/**
	 * changes the speed to a new one
	 * @param linearVelocity to apply to the monster
	 */
	public void setLinearVelocity(Vector2 linearVelocity) {
		this.linearVelocity = linearVelocity;
	}
	
}
