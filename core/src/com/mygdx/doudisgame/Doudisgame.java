package com.mygdx.doudisgame;


import com.badlogic.gdx.Game;
import com.mygdx.doudisgame.screens.MenuScreen;



public class Doudisgame extends Game{
	
	
	@Override
	public void create () {
		
		this.setScreen(new MenuScreen(this));
	}

	
}
