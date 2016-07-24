package at.fwuick.herebedragons;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.powermock.core.ListMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;

public class World {
	public static final Point cameraPos = new Point(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	public Map<Point, Chunk> chunks;
	public Player player;
	public WorldGenerator gen;
	//Entities HashMultiset to get the entites by the index of a chunk
	private Multimap<Point, Entity> entities;
	//This class exists for making the entities HashMultiset work
	//This overrides the hashcode function to return the hashcode of the Point of the Chunk they are standing on
	//Makes it easier to get the bunch of entities you really want to render
	class EntityWrapper extends Entity{
		private Entity entity;

		public EntityWrapper(Entity e){
			this.entity = e;
		}

		public boolean equals(Object obj) {
			return entity.position.equals(((Entity)obj).position);
		}

		public Texture getTexture() {
			return entity.getTexture();
		}

		public Point getPosition() {
			return entity.getPosition();
		}

		public int hashCode() {
			return Chunk.indexFromCoord(this.entity.getPosition()).hashCode();
		}

		public String toString() {
			return entity.toString();
		}
		
		
	}
	
	public World(){
		Ground.init();
		player = new Player();
		chunks = new HashMap<>();
		gen = new PlainFieldWorldGenerator();
		entities = HashMultimap.create();
	}
	
	
	public void renderChunks(SpriteBatch batch, Point pointInWorld){
		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){
				Chunk c = getChunkOfRealPosition(pointInWorld, i, j);
				Point pointInWindow = new Point(cameraPos);
				pointInWindow = pointInWindow.moveDistance(pointInWorld.getDistanceTo(c.getWorldPosition()));
				c.render(batch, pointInWindow);
			}
		}
	}
	
	public void renderEntities(SpriteBatch batch, Point p){
		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){
				Chunk c = getChunkOfRealPosition(p, i, j);
				getEntitiesByChunk(c.getIndex()).stream().forEach(e -> {
					renderEntity(batch, e);
				});
			}
		}
		
	}
	
	public void renderEntity(SpriteBatch batch, Entity e){
		Point drawAt = cameraPos.moveDistance(player.getPoint().getDistanceTo(e.getPosition()));
		Sprite shadow = new Sprite(TextureStorage.load("shadow"));
		Sprite sprite = new Sprite(e.getTexture());
		sprite.setSize(sprite.getWidth()*4, sprite.getHeight()*4);
		shadow.setSize(sprite.getWidth(), sprite.getHeight());
		sprite.setPosition(drawAt.x, drawAt.y);
		shadow.setPosition(drawAt.x, drawAt.y-10);
		shadow.draw(batch);
		sprite.draw(batch);
	}
	
	
	public int worldCoordinateToChunkCoordinate(int coord){
		return coord / Chunk.CHUNK_SIZE;
	}
	
		
	public Chunk getChunkOfRealPosition(Point pointInWorld, int offsetX, int offSetY){
		Point pointInChunks = Chunk.indexFromCoord(pointInWorld);
		pointInChunks.x += offsetX;
		pointInChunks.y += offSetY;
		return getChunk(pointInChunks);
	}

	private Chunk getChunk(Point pointInChunks) {
		if(!chunks.containsKey(pointInChunks)){
			chunks.put(pointInChunks, gen.generateChunk(this, pointInChunks));
		}
		return chunks.get(pointInChunks);
	}
	
	private Collection<Entity> getEntitiesByChunk(Point chunkIndex){
		return entities.get(chunkIndex);
	}
	
	public void spawn(Entity e){
		entities.put(Chunk.indexFromCoord(e.position), new EntityWrapper(e));
	}
}
