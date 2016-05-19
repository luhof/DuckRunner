package com.mygdx.doudisgame.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.doudisgame.box2d.CoinUserData;
import com.mygdx.doudisgame.box2d.EnemyUserData;
import com.mygdx.doudisgame.box2d.GroundUserData;
import com.mygdx.doudisgame.box2d.RunnerUserData;
import com.mygdx.doudisgame.enums.EnemyType;

public class WorldUtils {

		/**
		 * 
		 * @return a new world with custom gravity.
		 */
		public static World createWorld(){
			return new World(Constants.WORLD_GRAVITY, true);
		}
		
		/**
		 * Creates a box2D-ready actor with constant width, height and position.
		 * @param the world to add the actor in.
		 * @return its body
		 */
		public static Body createGround(World world){
			//Initialize ground body
			BodyDef bodyDef = new BodyDef();
			bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
			Body body = world.createBody(bodyDef);
			
			//Creating the shape and attaching it to the body
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(Constants.GROUND_WIDTH/2, Constants.GROUND_HEIGHT/2);
			body.createFixture(shape, Constants.GROUND_DENSITY);
			body.setUserData(new GroundUserData(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));
			shape.dispose();
			
			return body;
		}
		
		/**
		 * Creates a box2D-ready actor with constant width, height and position.
		 * @param the world to add the actor in.
		 * @return its body
		 */
		public static Body createRunner(World world){
			//Initialize ground body
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.DynamicBody;
			bodyDef.position.set(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y));
			Body body = world.createBody(bodyDef);
			
			//Creating the shape and attaching it to the body
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(Constants.RUNNER_WIDTH/2, Constants.RUNNER_HEIGHT/2);
			body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
			body.createFixture(shape, Constants.RUNNER_DENSITY);
			body.resetMassData();
			body.setUserData(new RunnerUserData(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT));
			shape.dispose();
			
			return body;
		}
		
		/**
		 * Creates a box2D-ready actor with constant width, height and position.
		 * @param the world to add the actor in.
		 * @return its body
		 */
		public static Body createEnemy(World world){
			EnemyType enemyType = RandomUtils.getRandomEnemyType();
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.KinematicBody;
			bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(enemyType.getWidth() /2 , enemyType.getHeight() / 2);
			Body body = world.createBody(bodyDef);
			body.createFixture(shape, enemyType.getDensity());
			body.resetMassData();
			EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight(), enemyType.getRegions());
			body.setUserData(userData);
			shape.dispose();
			return body;
		}
	
		/**
		 * Creates a box2D-ready actor with constant width, height and position.
		 * @param the world to add the actor in.
		 * @return its body
		 */
		public static Body createCoin(World world){
			//Initialize ground body
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.KinematicBody;
			if(MathUtils.randomBoolean()){
				bodyDef.position.set(new Vector2(Constants.COIN_X, Constants.COIN_Y));
			}
			else{
				bodyDef.position.set(new Vector2(Constants.COIN_X, Constants.COIN_Y+2f));
			}
			
			
			Body body = world.createBody(bodyDef);
			
			//Creating the shape and attaching it to the body
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(Constants.COIN_WIDTH/2, Constants.COIN_HEIGHT/2);
			FixtureDef fd1 = new FixtureDef();
			fd1.isSensor = true;
			fd1.shape = shape;
			body.createFixture(fd1);
			
			
			
			body.setUserData(new CoinUserData(Constants.COIN_WIDTH, Constants.COIN_HEIGHT));
			shape.dispose();
			
			return body;
		}
		
}
