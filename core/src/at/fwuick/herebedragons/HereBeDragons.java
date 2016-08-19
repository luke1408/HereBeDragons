package at.fwuick.herebedragons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.fwuick.herebedragons.world.World;

public class HereBeDragons extends ApplicationAdapter {
	
	public final static int CHUNK_SIZE = 10;
	
	SpriteBatch batch;
	World world;
	
			
	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World();
	}
	

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		world.tick();
		world.render(batch);
		batch.end();
	}
	
	
	
	@Override
	public void dispose () {
		batch.dispose();;
	}
}
