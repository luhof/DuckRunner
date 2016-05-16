package com.mygdx.doudisgame.actors;

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
	
	
	public Score(Rectangle rectangle) {
		prefs = Gdx.app.getPreferences("Game");
		this.rectangle = rectangle;
		setWidth(rectangle.width);
		setHeight(rectangle.height);
		font = new BitmapFont();
		
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
	
		font.draw(batch, "Score : "+ (int)score  +" ! ", rectangle.x-300, rectangle.y);
		font.draw(batch, "Best : "+ prefs.getInteger("highScore") +  " ! ", rectangle.x-100, rectangle.y+50);
		
	}
	
	public int getScore(){
		return (int)score;
	}
	
	public void addScore(int add){
		score += add;
	}
	
	public int getHighScore(){
		return Gdx.app.getPreferences("Game").getInteger("highScore");
	}
	
	public void setHighScore(){
		if(getScore() > getHighScore()){
			prefs.putInteger("highScore", (int)score);
			prefs.flush();
		}
			
	}

	public void reset() {
		score = 0f;
		// TODO Auto-generated method stub
		
	}

	

	
	
}
