package com.mygdx.doudisgame.box2d;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.doudisgame.enums.UserDataType;
import com.mygdx.doudisgame.utils.Constants;

public class CoinUserData extends UserData {
	
	Vector2 linearVelocity;
	
	
public CoinUserData (float width, float height) {
	
	super(width,height);
	
	userDataType = UserDataType.COIN;
	linearVelocity = Constants.COIN_LINEAR_VELOCITY;

}


public Vector2 getLinearVelocity() {
	return linearVelocity;
}


public void setLinearVelocity(Vector2 linearVelocity) {
	this.linearVelocity = linearVelocity;
}




}
