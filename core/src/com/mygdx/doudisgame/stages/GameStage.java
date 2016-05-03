package com.mygdx.doudisgame.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.doudisgame.actors.Background;
import com.mygdx.doudisgame.actors.Enemy;
import com.mygdx.doudisgame.actors.Ground;
import com.mygdx.doudisgame.actors.Runner;
import com.mygdx.doudisgame.actors.Score;
import com.mygdx.doudisgame.actors.StartButton;
import com.mygdx.doudisgame.enums.GameState;
import com.mygdx.doudisgame.utils.BodyUtils;
import com.mygdx.doudisgame.utils.Constants;
import com.mygdx.doudisgame.utils.GameManager;
import com.mygdx.doudisgame.utils.WorldUtils;



public class GameStage extends Stage implements ContactListener {

	private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
	private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
	
	private World world;
	private Background background;
	private Ground ground;
	private Runner runner;
	
	private final float TIME_STEP = 1/600f;
	private float accumulator = 0f;
	
	private OrthographicCamera camera;
	private Score score;
	private StartButton startButton;


	
	
	private Rectangle screenRightSide;
	private Rectangle screenLeftSide;
	private Vector3 touchPoint;
	
	
	public GameStage(){
		 super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
		//BODIES
		setupWorld();
		setupCamera();
		setupTouchControlAreas();
		
		
	}
	
	/*
	 * 
	 * SETUP
	 * 
	 */
	
	private void setupWorld(){
		world = WorldUtils.createWorld();
		world.setContactListener(this);
		setupBackground();
		setupRunner();
		setupGround();
		createEnemy();
		setupScore();
	}
	
	private void setupBackground(){
		background = new Background();
		addActor(background);
	}
	
	private void createEnemy() {
		Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
		addActor(enemy);
		
	}

	private void setupGround(){
		ground = new Ground(WorldUtils.createGround(world));
		addActor(ground);
	}
	
	private void setupRunner(){
		runner = new Runner(WorldUtils.createRunner(world));
		addActor(runner);
	}
	
	private void setupCamera(){
		
		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
		camera.update();
		
	}
	
	private void setupScore(){
		Rectangle boundaries = new Rectangle(getCamera().viewportWidth-100, getCamera().viewportHeight-100, 100, 100);
		
		score = new Score(boundaries);
		addActor(score);
	}
	
	private void setupStartButton(){
		Rectangle boundaries = new Rectangle(100, 100, 70, 70);
		startButton = new StartButton(boundaries, this);
		addActor(startButton);
	}

	
    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        screenLeftSide = new Rectangle(0,0, getCamera().viewportWidth /2, getCamera().viewportHeight);
        
        Gdx.input.setInputProcessor(this);
    }
	
	
	/*
	 * 
	 * INPUTS & ACTIONS
	 * 
	 */
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button){

		
		//translate to world coordinates
		translateScreenToWorldCoordinates(x, y);
			
			if(rightSideTouched(touchPoint.x, touchPoint.y)){
				runner.jump();
			}
			else if(leftSideTouched(touchPoint.x, touchPoint.y)){
				runner.dodge();
			}
		
		return super.touchDown(x, y, pointer, button);
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button){
		if(runner.isDodging()){
			runner.stopDodge();
		}
		return super.touchUp(x, y, pointer, button);
	}
	
	private boolean rightSideTouched(float x, float y){
		return screenRightSide.contains(x, y);
	}
	
	private boolean leftSideTouched(float x, float y){
		return screenLeftSide.contains(x, y);
	}
	
	
	
	//helper class
	private void translateScreenToWorldCoordinates(int x, int y){
		getCamera().unproject(touchPoint.set(x, y, 0));
	}
	
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		Body a = contact.getFixtureA().getBody();
		Body b = contact.getFixtureB().getBody();
		
		if((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
				(BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))){
			runner.hit();
			onGameOver();
		}
		else if((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
				(BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))){
			runner.landed();
		}
		
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	

	
	/*
	 * 
	 * UPDATES
	 * 
	 */
	

	@Override
	public void act(float delta){
		
		super.act(delta);
		
		
		//Updating all bodies
		Array<Body> bodies = new Array<Body>(world.getBodyCount());
		world.getBodies(bodies);
		for(Body body:bodies){
			update(body);
		}
		
		
			accumulator += delta;
			
			while (accumulator >= delta){
				world.step(TIME_STEP, 6, 2);
				accumulator -= TIME_STEP;
			}
			
		
	}
	
	private void update(Body body) {
		
		//check if bodies needs to be destroyed
		if(!BodyUtils.bodyInBounds(body)){
			if(BodyUtils.bodyIsEnemy(body) && (GameManager.getInstance().getGameState() == GameState.RUNNING)){
				createEnemy();
			}
			if(!BodyUtils.bodyIsRunner(body))world.destroyBody(body);
		}
		
	}
	
	private void removeAllMonsters(){
		Array<Body> bodies = new Array<Body>(world.getBodyCount());
		world.getBodies(bodies);
		for(Body body:bodies){
			if (BodyUtils.bodyIsEnemy(body)) world.destroyBody(body);
			
		}
	}
	
	public void onGameOver(){
		GameManager.getInstance().setGameState(GameState.OVER);
		setupStartButton();
	}

	public void reset(){
		
		clear();
		setupWorld();
		setupCamera();
		GameManager.getInstance().setGameState(GameState.RUNNING);
	}
	


	
}
