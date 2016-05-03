package com.mygdx.doudisgame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.doudisgame.box2d.UserData;
import com.mygdx.doudisgame.enums.GameState;
import com.mygdx.doudisgame.utils.GameManager;

public class Score extends Actor{

	private float score;
	private Rectangle rectangle;
	private BitmapFont font;
	
	
	public Score(Rectangle rectangle) {
		
		this.rectangle = rectangle;
		setWidth(rectangle.width);
		setHeight(rectangle.height);
		font = new BitmapFont();
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		if(GameManager.getInstance().getGameState() == GameState.RUNNING)
			score += delta;
	}

	@Override
	public void draw(Batch batch, float parentAlpha){
		font.draw(batch, "Score : "+ (int)score +  " ! ", rectangle.x, rectangle.y);
		
	}

	public void reset() {
		score = 0;
		// TODO Auto-generated method stub
		
	}

	

	
	
}
