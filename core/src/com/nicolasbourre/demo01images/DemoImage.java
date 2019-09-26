package com.nicolasbourre.demo01images;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DemoImage extends ApplicationAdapter {
	SpriteBatch batch;

	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;

	Animation walkAnimation;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	TextureRegion currentFrame;

	float stateTime;

	float walkSpeed = 5f;
	boolean pause = true;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		walkSheet = new Texture("animation_sheet.png");

		TextureRegion [][] temp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);

		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = temp[i][j];
			}
		}

		walkAnimation = new Animation(0.025f, walkFrames);
		stateTime = 0.0f;
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		draw();
	}

	int xPos = 50;
	boolean flippedX = false;

	void update (float deltaTime) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			pause = !pause;
		}

		stateTime += deltaTime;

		if (!pause)
			xPos += walkSpeed;

		currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);

		if (xPos + currentFrame.getRegionWidth() >= Gdx.graphics.getWidth() ||
			xPos < 0) {
			walkSpeed = -walkSpeed;
			flippedX = !flippedX;
		}
	}

	void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.draw(currentFrame,
				(flippedX ? currentFrame.getRegionWidth() : 0) + xPos, 50,
				(flippedX ? -1 : 1) * currentFrame.getRegionWidth(), currentFrame.getRegionHeight());

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
