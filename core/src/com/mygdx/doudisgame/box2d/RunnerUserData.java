package com.mygdx.doudisgame.box2d;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.doudisgame.enums.UserDataType;
import com.mygdx.doudisgame.utils.Constants;

public class RunnerUserData extends UserData{

	
	private final Vector2 runningPosition = new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
	private final Vector2 dodgePosition = new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);
	private Vector2 jumpingLinearImpulse;
	
	/**
	 * Creates a new userdata of type Runner
	 * @param width
	 * @param height
	 */
	public RunnerUserData(float width, float height){
		super(width, height);
		jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
		userDataType = UserDataType.RUNNER;
	}
	
	/**
	 * 
	 * @return Vector2 runner jump strength
	 */
	public Vector2 getJumpingLinearImpulse(){
		return jumpingLinearImpulse;
	}
	
	/**
	 * changes runner jump strength to a new one
	 * @param jumpingLinearImpulse to use
	 */
	public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse){
		this.jumpingLinearImpulse = jumpingLinearImpulse;
	}
	
	/**
	 * 
	 * @return player angular impulse he has when he collides with a monster
	 */
	public float getHitAngularImpulse(){
		return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
	}
	
	/**
	 * 
	 * @return angle to apply when he's dodging
	 */
	public float getDodgeAngle(){
		return (float) (-90f * (Math.PI / 180f));
	}
	
	/**
	 * 
	 * @return position to have when he's standing
	 */
	public Vector2 getRunningPosition(){
		return runningPosition;
	}
	
	
	/**
	 * 
	 * @return position to have when he's dodging
	 */
	public Vector2 getDodgePosition(){
		return dodgePosition;
	}
	
}
