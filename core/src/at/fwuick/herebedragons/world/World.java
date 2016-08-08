package at.fwuick.herebedragons.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.powermock.core.ListMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;

import at.fwuick.herebedragons.EntityRender;
import at.fwuick.herebedragons.entities.Entity;
import at.fwuick.herebedragons.entities.EntityManager;
import at.fwuick.herebedragons.entities.Player;
import at.fwuick.herebedragons.world.generator.PlainFieldWorldGenerator;
import at.fwuick.herebedragons.world.generator.WorldGenerator;

public class World {

	public static final Point cameraPos = new Point(Gdx.graphics.getWidth()/2-16, Gdx.graphics.getHeight()/2-16);
	public Map<Point, Chunk> chunks;
	public Player player;
	public WorldGenerator gen;


	//Entities HashMultiset to get the entites by the index of a chunk
	public Multimap<Point, Entity> entities;
	//This class exists for making the entities HashMultiset work
	//This overrides the hashcode function to return the hashcode of the Point of the Chunk they are standing on
	//Makes it easier to get the bunch of entities you really want to render
	private EntityRender entityRender;
	public EntityManager entityManager;
	
	public World(){
		Ground.init();
		entityRender = new EntityRender(this);
		entityManager = new EntityManager(this);
		player = new Player();
		entityManager.spawn(player);
		chunks = new HashMap<>();
		gen = new PlainFieldWorldGenerator();

	}
	
	
	
	//Renders a chunk
	public void renderChunk(SpriteBatch batch, Chunk c){

		Point pointInWindow = new Point(cameraPos);
		pointInWindow = pointInWindow.moveDistance(player.getPosition().getDistanceTo(c.getWorldPosition()));
		c.render(batch, pointInWindow);
	}
	
	//Chunks to  render
	public Collection<Chunk> chunksToRender(Point pointInWorld){
		List<Chunk> torender = new ArrayList<>();
		
		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){

				Chunk c = getChunkOfRealPosition(pointInWorld, i, j);
				torender.add(c);
			}
		}
		
		return torender;
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

	public Chunk getChunk(Point pointInChunks) {
		if(!chunks.containsKey(pointInChunks)){
			chunks.put(pointInChunks, gen.generateChunk(this, pointInChunks));
		}
		return chunks.get(pointInChunks);
	}
	

	



	public void render(SpriteBatch batch) {
		Collection<Chunk> chunksToRender = this.chunksToRender(player.getPosition());
		chunksToRender.forEach(c -> renderChunk(batch, c));
		List<Entity> toRender = new ArrayList<Entity>();
		chunksToRender.stream().map(c -> entityManager.getEntitiesByChunk(c.getIndex())).forEach(es -> toRender.addAll(es));
		entityRender.renderEntities(toRender, batch);
	}
	
	//Transforms an World position to a screen position
	//for rendering
	public Point getRenderPosition(Point realWorldPosition){
		return World.cameraPos.moveDistance(player.getPosition().getDistanceTo(realWorldPosition));
	}
	
	//Gets 3x3 chunks arround the given chunk
	public Collection<Chunk> getSurroundingChunks(Chunk c){
		List<Chunk> cs = new ArrayList<Chunk>();
		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){
				cs.add(this.getChunk(new Point(c.getIndex().x+i, c.getIndex().y+j)));
			}
		}
		return cs;
	}



	public Chunk getChunkOfRealPosition(Point point) {
		return getChunkOfRealPosition(point, 0, 0);
	}
}
