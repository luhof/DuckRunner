package com.mygdx.doudisgame.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.doudisgame.box2d.UserData;
import com.mygdx.doudisgame.enums.UserDataType;

public class BodyUtils {

	/**
	 * 
	 * @param body
	 * @return true if body is of type Runner
	 */
	public static boolean bodyIsRunner(Body body){
		UserData userData = (UserData) body.getUserData();
		return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
	}
	
	/**
	 * 
	 * @param body
	 * @return true if body is of type Ground
	 */
	public static boolean bodyIsGround(Body body){
		UserData userData = (UserData) body.getUserData();
		return userData != null && userData.getUserDataType() == UserDataType.GROUND;
	}
	
	/**
	 * 
	 * @param body
	 * @return true if body is of type Enemy
	 */
	public static boolean bodyIsEnemy(Body body){
		UserData userData = (UserData) body.getUserData();
		return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
	}
	
	/**
	 * 
	 * @param body
	 * @return true if body is of type Coin
	 */
	public static boolean bodyIsCoin(Body body){
		UserData userData = (UserData) body.getUserData();
		return userData != null && userData.getUserDataType() == UserDataType.COIN;
	}
	
	/**
	 * 
	 * @param body
	 * @return true if body in the screen's bounds
	 */
	public static boolean bodyInBounds(Body body){
		UserData userData = (UserData) body.getUserData();
		
		switch(userData.getUserDataType()){
			case RUNNER:
			case ENEMY:
			case COIN:
				return body.getPosition().x + userData.getWidth() / 2 > 0;
			default:
				return true;
		}
	}
	
}
