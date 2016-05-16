package com.mygdx.doudisgame.box2d;

import com.mygdx.doudisgame.enums.UserDataType;

public abstract class UserData {

	protected UserDataType userDataType;
	protected float width;
	protected float height;
	protected boolean isFlaggedForDelete;
	
	public UserData(){
		
	}
	
	public UserData(float width, float height){
		this.width = width;
		this.height = height;
		this.isFlaggedForDelete = false;
	}
	
	public UserDataType getUserDataType(){
		return userDataType;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isFlaggedForDeletion() {
		return isFlaggedForDelete;
	}

	public void setFlaggedForDelete(boolean isFlaggedForDelete) {
		this.isFlaggedForDelete = isFlaggedForDelete;
	}
	
}
