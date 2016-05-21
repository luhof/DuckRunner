package com.mygdx.doudisgame.enums;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.doudisgame.utils.Constants;

public enum Difficulty {

    DIFFICULTY_1(1, Constants.ENEMY_LINEAR_VELOCITY, Constants.RUNNER_GRAVITY_SCALE, Constants.RUNNER_JUMPING_LINEAR_IMPULSE),
    DIFFICULTY_2(2, new Vector2(-12f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.1f, new Vector2(0, 13f)),
    DIFFICULTY_3(3, new Vector2(-14f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.1f, new Vector2(0, 13f)),
    DIFFICULTY_4(4, new Vector2(-16f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.1f, new Vector2(0, 13f)),
    DIFFICULTY_5(5, new Vector2(-18f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.1f, new Vector2(0, 13f)),
    DIFFICULTY_6(6, new Vector2(-20f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.3f, new Vector2(0, 14f)),
    DIFFICULTY_7(7, new Vector2(-22f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.3f, new Vector2(0, 14f)),
    DIFFICULTY_8(8, new Vector2(-24f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.3f, new Vector2(0, 14f)),
    DIFFICULTY_9(9, new Vector2(-26f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.5f, new Vector2(0, 15f)),
    DIFFICULTY_10(10, new Vector2(-28f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.5f, new Vector2(0, 15f)),
    DIFFICULTY_11(11, new Vector2(-30f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.6f, new Vector2(0, 15f)),
    DIFFICULTY_12(12, new Vector2(-32f, 0f), Constants.RUNNER_GRAVITY_SCALE * 1.7f, new Vector2(0, 16f)),
    DIFFICULTY_13(13, new Vector2(-34f, 0f), Constants.RUNNER_GRAVITY_SCALE * 2.1f, new Vector2(0, 18f));
	


private int level;
private Vector2 enemyLinearVelocity;
private float runnerGravityScale;
private Vector2 runnerJumpingLinearImpulse;


/**
 * 
 * @param level default level
 * @param obstacleLinearVelocity enemy speed
 * @param runnerGravityScale gravity of the player
 * @param runnerJumpingLinearImpulse jump force
 */
Difficulty(int level, Vector2 obstacleLinearVelocity, float runnerGravityScale, Vector2 runnerJumpingLinearImpulse) {
    this.level = level;
    this.enemyLinearVelocity = obstacleLinearVelocity;
    this.runnerGravityScale = runnerGravityScale;
    this.runnerJumpingLinearImpulse = runnerJumpingLinearImpulse;
}

/**
 * 
 * @return current level
 */
public int getLevel() {
	return level;
}

/**
 * changes current level
 * @param level to use
 */
public void setLevel(int level) {
	this.level = level;
}

/**
 * 
 * @return enemy speed multiplicator based on level
 */
public Vector2 getEnemyLinearVelocity() {
	return enemyLinearVelocity;
}

/**
 * changes enemy speed multiplicator based on level
 * @param enemyLinearVelocity to use
 */
public void setEnemyLinearVelocity(Vector2 enemyLinearVelocity) {
	this.enemyLinearVelocity = enemyLinearVelocity;
}

/**
 * 
 * @return jump multiplicator based on level
 */
public float getRunnerGravityScale() {
	return runnerGravityScale;
}

/**
 * changes runner gravity based on level
 * @param runnerGravityScale to use
 */
public void setRunnerGravityScale(float runnerGravityScale) {
	this.runnerGravityScale = runnerGravityScale;
}

/**
 * returns runner jump force
 * @return vector2
 */
public Vector2 getRunnerJumpingLinearImpulse() {
	return runnerJumpingLinearImpulse;
}

/**
 * changes runner jump force
 * @param runnerJumpingLinearImpulse to use
 */
public void setRunnerJumpingLinearImpulse(Vector2 runnerJumpingLinearImpulse) {
	this.runnerJumpingLinearImpulse = runnerJumpingLinearImpulse;
}


}
