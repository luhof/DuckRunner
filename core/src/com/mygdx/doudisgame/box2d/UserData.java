package com.mygdx.doudisgame.box2d;

import com.mygdx.doudisgame.enums.UserDataType;

public abstract class UserData {

	protected UserDataType userDataType;
	protected float width;
	protected float height;
	protected boolean isFlaggedForDelete;
	
	public UserData(){
		
	}
	
	/**
	 * abstract : contains basic information (width and height) about a user
	 * @param width
	 * @param height
	 */
	public UserData(float width, float height){
		this.width = width;
		this.height = height;
		this.isFlaggedForDelete = false;
	}
	
	/**
	* return the type of the object.
	 */
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

	/**
	 * return true if the object will be deleted in the next update step.
	 */
	public boolean isFlaggedForDeletion() {
		return isFlaggedForDelete;
	}

	/**
	 * set the object to be deleted in the next update step.
	 */
	public void setFlaggedForDelete(boolean isFlaggedForDelete) {
		this.isFlaggedForDelete = isFlaggedForDelete;
	}
	
}
