package com.mygdx.doudisgame;


import com.badlogic.gdx.Game;
import com.mygdx.doudisgame.screens.GameScreen;



public class Doudisgame extends Game{
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	
}
