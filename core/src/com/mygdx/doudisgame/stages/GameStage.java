package com.mygdx.doudisgame.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.doudisgame.actors.Background;
import com.mygdx.doudisgame.actors.Coin;
import com.mygdx.doudisgame.actors.Enemy;
import com.mygdx.doudisgame.actors.Ground;
import com.mygdx.doudisgame.actors.Runner;
import com.mygdx.doudisgame.actors.Score;
import com.mygdx.doudisgame.actors.StartButton;
import com.mygdx.doudisgame.box2d.CoinUserData;
import com.mygdx.doudisgame.box2d.UserData;
import com.mygdx.doudisgame.enums.Difficulty;
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
	private float totalTimePassed;
	
	private OrthographicCamera camera;
	private Score score;
	private static Preferences prefs;
	private StartButton startButton;

	
	
	
	private Rectangle screenRightSide;
	private Rectangle screenLeftSide;
	private Vector3 touchPoint;
	
	/**
	 * Creates a new gamestage and initialize its world.
	 */
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
	
	/**
	 * Creates stage's actors, such as Jean-Marie, monsters or coins.
	 * Also creates HUD like score.
	 */
	private void setupWorld(){
		world = WorldUtils.createWorld();
		world.setContactListener(this);
		
		
		
		setupBackground();
		setupRunner();
		setupGround();
		createEnemy();
		setupScore();
		createCoin();
		
		
        prefs = Gdx.app.getPreferences("Game");
        if(!prefs.contains("highScore")){
        	prefs.putInteger("highScore", 0);
            prefs.flush();
        }
        
        
	}
	
	/**
	 * Add a background actor to the stage.
	 */
	private void setupBackground(){
		background = new Background();
		addActor(background);
	}
	
	/**
	 * Add an enemy actor to the stage and set his speed.
	 */
	private void createEnemy() {
		Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
		enemy.getUserData().setLinearVelocity(GameManager.getInstance().getDifficulty().getEnemyLinearVelocity());
		addActor(enemy);
		
	}
	
	/**
	 * Add a coin actor to the stage and set its speed.
	 */
	private void createCoin() {
		Coin onecoin = new Coin(WorldUtils.createCoin(world));
		onecoin.getUserData().setLinearVelocity(GameManager.getInstance().getDifficulty().getEnemyLinearVelocity());
		addActor(onecoin);
	}
	
	/**
	 * Add a ground actor to the stage.
	 */
	private void setupGround(){
		ground = new Ground(WorldUtils.createGround(world));
		addActor(ground);
	}
	
	/**
	 * Add a Jean-Marie actor to the stage.
	 */
	private void setupRunner(){
		runner = new Runner(WorldUtils.createRunner(world));
		addActor(runner);
	}
	
	/**
	 * setup the stage's orthographic camera
	 */
	private void setupCamera(){
		
		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
		camera.update();
		
	}
	
	/**
	 * Add a Score actor to the stage.
	 */
	private void setupScore(){
		Rectangle boundaries = new Rectangle(125, getCamera().viewportHeight-100, 100, 100);
		
		score = new Score(boundaries);
		
		addActor(score);
	}
	
	/**
	 * Add a Start Button actor to the stage.
	 */
	private void setupStartButton(){
		Rectangle boundaries = new Rectangle(100, 100, 70, 70);
		startButton = new StartButton(boundaries, this);
		addActor(startButton);
	}

	/**
	 * Determines screen's right side and left side coordinates
	 */
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
	
	/**
	 * 
	 * @param x touched point
	 * @param y touched point
	 * @return true if the right side contains the point at x, y
	 */
	private boolean rightSideTouched(float x, float y){
		return screenRightSide.contains(x, y);
	}

	/**
	 * 
	 * @param x touched point
	 * @param y touched point
	 * @return true if the left side contains the point at x, y
	 */
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
		else if((BodyUtils.bodyIsCoin(a) && BodyUtils.bodyIsRunner(b)) ||
				(BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsCoin(b))){
			
				if(BodyUtils.bodyIsCoin(a)) killBody(a);
				else killBody(b);
				
				// Since we got a coin, we earn 5 pts.
				score.addScore(5);
		}
			
	}


	
	/*
	 * 
	 * UPDATES
	 * 
	 */
	

	@Override
	public void act(float delta){
		
		super.act(delta);
		if (GameManager.getInstance().getGameState() == GameState.RUNNING) {
            totalTimePassed += delta;
            updateDifficulty();
        }
		
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
	
	/**
	 * Checks if bodies needs to be destroyed and destroys them if needed.
	 * Also creates new actors.
	 * 
	 * @param body
	 */
	private void update(Body body) {
		
		//check if bodies needs to be destroyed
		if(!BodyUtils.bodyInBounds(body)){
			if(BodyUtils.bodyIsEnemy(body) && (GameManager.getInstance().getGameState() == GameState.RUNNING)){
				createEnemy();		
			}
			if(BodyUtils.bodyIsCoin(body) && (GameManager.getInstance().getGameState() == GameState.RUNNING)){
				createCoin();			
			}
			if(!BodyUtils.bodyIsRunner(body))world.destroyBody(body);
		}
		
		
		UserData bodyData = (UserData) body.getUserData();
		if(bodyData != null && bodyData.isFlaggedForDeletion() == true){
			if(BodyUtils.bodyIsCoin(body)) createCoin();
			world.destroyBody(body);
			
		}
		
	}
	
	/**
	 * Makes the game harder the longer you played it.
	 */
    private void updateDifficulty() {

        if (GameManager.getInstance().isMaxDifficulty()) {
            return;
        }

        Difficulty currentDifficulty = GameManager.getInstance().getDifficulty();

        if (totalTimePassed > GameManager.getInstance().getDifficulty().getLevel() * 5) {

            int nextDifficulty = currentDifficulty.getLevel() + 1;
            String difficultyName = "DIFFICULTY_" + nextDifficulty;
            GameManager.getInstance().setDifficulty(Difficulty.valueOf(difficultyName));

            runner.onDifficultyChange(GameManager.getInstance().getDifficulty());
        }

    }
	
    /**
     * Helper class for an upcoming feature !
     */
	private void removeAllMonsters(){
		Array<Body> bodies = new Array<Body>(world.getBodyCount());
		world.getBodies(bodies);
		for(Body body:bodies){
			if (BodyUtils.bodyIsEnemy(body)) world.destroyBody(body);
			
		}
	}
	
	/**
	 * Add an actor to the "to-be-destroyed" list !
	 * @param body of the actor
	 */
	private void killBody(Body body){
		UserData bodyData = (UserData) body.getUserData();
		bodyData.setFlaggedForDelete(true);
	}
	
	
	
	/**
	 * Actions to happen when the player loses
	 */
	public void onGameOver(){
		GameManager.getInstance().setGameState(GameState.OVER);
		GameManager.getInstance().resetDifficulty();
		this.totalTimePassed = 0.0f;
		score.setHighScore();
		setupStartButton();
	}

	/**
	 * Restart the game
	 */
	public void reset(){
		
		clear();
		setupWorld();
		setupCamera();
		GameManager.getInstance().setGameState(GameState.RUNNING);
		
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
	


	
}
