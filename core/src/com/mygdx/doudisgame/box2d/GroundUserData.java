package com.mygdx.doudisgame.box2d;

import com.mygdx.doudisgame.enums.UserDataType;

public class GroundUserData extends UserData{

	public GroundUserData(float width, float height){
		super(width, height);
		userDataType = UserDataType.GROUND;
	}
	
}
