package com.mygdx.doudisgame.box2d;

import com.mygdx.doudisgame.enums.UserDataType;

public abstract class UserData {

	protected UserDataType userDataType;
	
	public UserData(){
		
	}
	
	public UserDataType getUserDataType(){
		return userDataType;
	}
	
}
