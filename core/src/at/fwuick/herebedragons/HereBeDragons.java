package at.fwuick.herebedragons;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.javafx.geom.Point2D;

public class HereBeDragons extends ApplicationAdapter {
	
	public final static int CHUNK_SIZE = 10;
	
	SpriteBatch batch;
	Texture[] grass;
	Map<String, int[][]> chunks;
	Point2D p;
	
			
	@Override
	public void create () {
		batch = new SpriteBatch();
		grass = new Texture[]{new Texture("assets/Grass1.png"), new Texture("assets/Grass2.png")};
		chunks = new HashMap<>();
		p = new Point2D();
	}
	
	public int[][] getChunk(Point2D p){
		Point p1 = new Point(p.x<0?-1:1 * Math.round(Math.abs(p.x) / CHUNK_SIZE), p.y<0?-1:1* Math.round(Math.abs(p.y) / CHUNK_SIZE)) ;
		String key = new com.badlogic.gdx.utils.StringBuilder().append(p1.x).append(',').append(p1.y).toString();
		if(!chunks.containsKey(key)){
			chunks.put(key, generateChunk());
		}
		return chunks.get(key);
		
	}
	
	public int[][] generateChunk(){
		Random r = new Random();
		int[][] grassMap = new int[CHUNK_SIZE][CHUNK_SIZE];
		for(int i=0; i<grassMap.length; i++){
			for(int j=0; j<grassMap[0].length; j++){
				grassMap[i][j] = r.nextInt(2);
			}
		}
		return grassMap;
	}
	

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyPressed(Keys.W)){
			p.y ++;
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			p.x --;
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			p.y --;
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			p.x ++;
		}
		batch.begin();
		
	
		for(int ci=-1; ci<5; ci++)
			for(int cj=-1; cj<5; cj++){
				Point2D p = new Point2D(this.p.x + ci*CHUNK_SIZE, this.p.y + cj*CHUNK_SIZE);
				int[][] grassMap = getChunk(p);
				for(int i=0; i<grassMap.length; i++)
					for(int j=0; j<grassMap[0].length; j++){
						batch.draw(grass[grassMap[i][j]], (i - (p.x%CHUNK_SIZE))*30, (j - (p.y%CHUNK_SIZE))*30);
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
