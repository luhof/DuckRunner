package com.mygdx.doudisgame.actors;

import java.awt.Color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.doudisgame.enums.GameState;
import com.mygdx.doudisgame.utils.GameManager;

public class Score extends Actor{

	private static Preferences prefs;
	private float score;
	private Rectangle rectangle;
	private BitmapFont font;
	
	/**
	 * Creates a score object that contains players score and display position.
	 */
	public Score(Rectangle rectangle) {
		prefs = Gdx.app.getPreferences("Game");
		this.rectangle = rectangle;
		setWidth(rectangle.width);
		setHeight(rectangle.height);
		font = new BitmapFont(Gdx.files.internal("fonts/myFont.fnt"));
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		if(GameManager.getInstance().getGameState() == GameState.RUNNING){
			score += delta;
			setHighScore();
		}
			
	}

	@Override
	public void draw(Batch batch, float parentAlpha){
		font.setColor(1.0f, 1.0f,1.0f, 0.5f);
		
		font.draw(batch, "Score : "+ getScore()  +" ! ", rectangle.x, rectangle.y);
		font.setColor(0.0f, 0.0f, 0.0f, 0.2f);
		font.draw(batch, "Best : "+ prefs.getInteger("highScore") +  " ! ", rectangle.x, rectangle.y-100);
		
	}
	
	public int getScore(){
		return (int)score;
	}
	
	/**
	 * adds a certain int to the players score.
	 * @param add
	 */
	public void addScore(int add){
		score += add;
	}
	
	/**
	 * Get device's saved high score.
	 * @return
	 */
	public int getHighScore(){
		return Gdx.app.getPreferences("Game").getInteger("highScore");
	}
	
	/**
	 * Uses players score to write it on the actual device's highest score
	 */
	public void setHighScore(){
		if(getScore() > getHighScore()){
			prefs.putInteger("highScore", (int)score);
			prefs.flush();
		}
			
	}

	/**
	 * Set the score to zero.
	 */
	public void reset() {
		score = 0f;
		// TODO Auto-generated method stub
		
	}

	

	
	
}
