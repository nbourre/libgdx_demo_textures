package com.nicolasbourre.demo01images;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DemoImage extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("goods.png");
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

		batch.draw(img, 0, 0, 128, 128 ); // Stretch
		batch.draw(img, 200, 255, 32, 32, 32, 32); // Méthode avec une fenêtre
		batch.draw(img, 200, 300, 16, 16, 32, 32, 0.5f, 0.5f, (float)Math.PI, 32, 32, 32, 32, false, false);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
