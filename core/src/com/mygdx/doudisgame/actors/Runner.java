package com.mygdx.doudisgame.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.doudisgame.box2d.RunnerUserData;

public class Runner extends GameActor{

	private boolean isJumping;
	private boolean isDodging;
	
	public Runner(Body body) {
		super(body);
	}

	@Override
	public RunnerUserData getUserData() {
		return (RunnerUserData) userData;
	}
	
	public void jump(){
		if(!(isJumping || isDodging)){
			body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(),body.getWorldCenter(), true);
			isJumping = true;
		}
	}
	
	public void landed(){
		isJumping = false;
	}
	
	public void dodge(){
		if(!(isJumping)){
			body.setTransform(getUserData().getDodgePosition(),getUserData().getDodgeAngle());
			isDodging = true;
		}
	}
	
	public void stopDodge(){
		isDodging = false;
		body.setTransform(getUserData().getRunningPosition(), 0f);
		
	}
	
	public boolean isDodging(){
		return isDodging;
	}

}
