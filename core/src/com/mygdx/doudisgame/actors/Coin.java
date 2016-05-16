package com.mygdx.doudisgame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.doudisgame.box2d.CoinUserData;
import com.mygdx.doudisgame.box2d.UserData;
import com.mygdx.doudisgame.utils.Constants;

public class Coin extends GameActor{
	
	private Texture texture;

	public Coin(Body body) {
		super(body);
		// TODO Auto-generated constructor stub
		texture = new Texture(Gdx.files.internal(Constants.COIN_IMAGE_PATH));
	}

	@Override
	public CoinUserData getUserData() {
		// TODO Auto-generated method stub
		return (CoinUserData)userData;
	}

	@Override
	public void draw (Batch batch, float parentAlpha) {
		
		super.draw(batch,parentAlpha);
		batch.draw(texture, screenRectangle.x, screenRectangle.y, screenRectangle.width, screenRectangle.height);
		
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		body.setLinearVelocity(getUserData().getLinearVelocity());
	}
}
