package com.mygdx.doudisgame.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.doudisgame.box2d.RunnerUserData;

public class Runner extends GameActor{

	private boolean isJumping;
	private boolean isDodging;
	private boolean isHit;
	
	public Runner(Body body) {
		super(body);
	}

	@Override
	public RunnerUserData getUserData() {
		return (RunnerUserData) userData;
	}
	
	public void jump(){
		if(!(isJumping || isHit)){
			body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(),body.getWorldCenter(), true);
			isJumping = true;
		}
	}
	
	public void landed(){
		isJumping = false;
	}
	
	public void dodge(){
		if(!(isJumping || isHit)){
			body.setTransform(getUserData().getDodgePosition(),getUserData().getDodgeAngle());
			isDodging = true;
		}
	}
	
	public void stopDodge(){
		isDodging = false;
		if(!isHit){
			body.setTransform(getUserData().getRunningPosition(), 0f);
		}
	}
	
	public boolean isDodging(){
		return isDodging;
	}
	
	public void hit(){
		body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
		isHit = true;
	}
	
	public boolean isHit(){
		return this.isHit;
	}

}
