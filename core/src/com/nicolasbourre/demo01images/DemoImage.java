package com.nicolasbourre.demo01images;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DemoImage extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion regions[];
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("goods.png");

		regions = new TextureRegion[4];

		regions[0] = new TextureRegion(img, 0, 0, 32, 32);
		// Méthode uniformisée
		regions[1] = new TextureRegion(img, 0.5f, 0f, 1, 0.5f);

		regions[2] = new TextureRegion(img, 0, 32, 32, 32);
		regions[3] = new TextureRegion(img, 0.5f, 0.5f, 1f, 1f);
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		draw();
	}

	void update (float deltaTime) {

	}

	void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		for (int i = 0; i < regions.length; i++) {
			batch.draw(regions[i], 75 * (i + 2), 100);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
