package com.mygdx.doudisgame.utils;

import com.mygdx.doudisgame.enums.GameState;

public class GameManager{
	
	private static GameManager myInstance = new GameManager();
	
	private GameState gameState;
	
	private GameManager(){
		gameState = GameState.RUNNING;
	}
	
	public GameState getGameState(){
		return this.gameState;
	}
	
	public static GameManager getInstance(){
		return myInstance;
	}
	
	public void setGameState(GameState gameState){
		this.gameState = gameState;
	}

}
