package com.mygdx.doudisgame.box2d;

import com.mygdx.doudisgame.enums.UserDataType;

public class GroundUserData extends UserData{

	/**
	 * creates a ground
	 * @param width
	 * @param height
	 */
	public GroundUserData(float width, float height){
		super(width, height);
		userDataType = UserDataType.GROUND;
	}
	
}
