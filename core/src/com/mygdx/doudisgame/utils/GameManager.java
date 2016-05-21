package com.mygdx.doudisgame.utils;

import com.mygdx.doudisgame.enums.Difficulty;
import com.mygdx.doudisgame.enums.GameState;

public class GameManager{
	
	private static GameManager myInstance = new GameManager();
	
	private GameState gameState;
	private Difficulty difficulty;
	/**
	 * Private constructor since it's a singleton.
	 */
	private GameManager(){
		gameState = GameState.RUNNING;
		difficulty = Difficulty.DIFFICULTY_1;
	}
	
	/**
	 * 
	 * @return the current difficulty enum
	 */
	public Difficulty getDifficulty(){
		return this.difficulty;
	}
	
	/**
	 * changes current difficulty to a new one
	 * @param difficulty
	 */
	public void setDifficulty(Difficulty difficulty){
		this.difficulty = difficulty;
	}
	
	/**
	 * 
	 * @return true if the game manager has reached max difficulty
	 */
    public boolean isMaxDifficulty() {
        return difficulty == Difficulty.values()[Difficulty.values().length - 1];
    }
    
    /**
     * set the current difficulty to the first one from the enum
     */
    public void resetDifficulty() {
        setDifficulty(Difficulty.values()[0]);
    }
	
    /**
     * 
     * @return the current game state from the enum (running, over....)
     */
	public GameState getGameState(){
		return this.gameState;
	}
	
	/**
	 * @return the instance. It's also how to create an instance since it's a singleton.
	 */
	public static GameManager getInstance(){
		return myInstance;
	}
	
	/**
	 * changes the current gamestate to another.
	 * @param gameState
	 */
	public void setGameState(GameState gameState){
		this.gameState = gameState;
	}

}
