package at.fwuick.herebedragons;

import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HereBeDragons extends ApplicationAdapter {
	SpriteBatch batch;
	Texture[] grass;
	int[][] grassMap;
	
			
	@Override
	public void create () {
		batch = new SpriteBatch();
		grass = new Texture[]{new Texture("assets/Grass1.png"), new Texture("assets/Grass2.png")};
		grassMap = new int[800/30][600/30];
		Random r = new Random();
		for(int i=0; i<grassMap.length; i++){
			for(int j=0; j<grassMap[0].length; j++){
				grassMap[i][j] = r.nextInt(2);
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(int i=0; i<grassMap.length; i++){
			for(int j=0; j<grassMap[0].length; j++){
				batch.draw(grass[grassMap[i][j]], i*30, j*30);
			}
		}
		batch.end();
	}
	
	
	
	@Override
	public void dispose () {
		batch.dispose();
		Arrays.stream(grass).forEach(t -> t.dispose());
	}
}
