package at.fwuick.herebedragons;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World {
	public static final Point cameraPos = new Point(Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2);
	public Map<Point, Chunk> chunks;
	public Player player;
	public WorldGenerator gen;
	
	public World(){
		Ground.init();
		player = new Player();
		chunks = new HashMap<>();
		gen = new PlainFieldWorldGenerator();
	}
	
	
	public void renderChunks(SpriteBatch batch, Point pointInWorld){
		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){
				Chunk c = getChunkOfRealPosition(pointInWorld, i, j);
				Point pointInWindow = new Point(cameraPos);
				pointInWindow.move(pointInWorld.x - player.getPoint().x + i * Chunk.CHUNK_DIMENSION, pointInWorld.y - player.getPoint().x + j * Chunk.CHUNK_DIMENSION);
				c.render(batch, pointInWindow);
			}
		}
	}
	
	
	public int worldCoordinateToChunkCoordinate(int coord){
		return coord / Chunk.CHUNK_SIZE;
	}
	
	public Point pointInWorldTopointInChunks(Point p){
		return new Point(worldCoordinateToChunkCoordinate(p.x), worldCoordinateToChunkCoordinate(p.y));
	}
	
	public Chunk getChunkOfRealPosition(Point pointInWorld, int offsetX, int offSetY){
		Point pointInChunks = pointInWorldTopointInChunks(pointInWorld);
		pointInChunks.x += offsetX;
		pointInChunks.y += offSetY;
		return getChunk(pointInChunks);
	}

	private Chunk getChunk(Point pointInChunks) {
		if(!chunks.containsKey(pointInChunks)){
			chunks.put(pointInChunks, gen.generateChunk(pointInChunks));
		}
		return chunks.get(pointInChunks);
	}
}
