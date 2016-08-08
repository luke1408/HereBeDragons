package at.fwuick.herebedragons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.math.Rectangle;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

//This holds the collection of all Entities in a world
//It must be easy to find the entities per chunks
//This is the class to do that
public class EntityManager {

	//Entities HashMultiset to get the entites by the index of a chunk
	private Multimap<Point, Entity> entities;
	private World world;
	
	public  EntityManager(World w){
		entities = HashMultimap.create();
		this.world = w;
	}
	
	public Chunk chunkFromEntity(Entity e){
		return world.getChunk(Chunk.indexFromCoord(e.getPosition()));
	}
	
	public void reportChange(Entity entity, Point old, Point newP) {
		Point oldIndex = Chunk.indexFromCoord(old);
		Point newIndex = Chunk.indexFromCoord(newP);
		if(!oldIndex.equals(newIndex)){
			entities.remove(oldIndex, entity);
			entities.put(newIndex, entity);
		}
	}
	
	public Collection<Entity> getEntitiesByChunk(Point chunkIndex){
		return entities.get(chunkIndex);
	}
	
	public void spawn(Entity e){
		entities.put(Chunk.indexFromCoord(e.position.get()), e);
		e.setEntityManager(this);
	}
	
	public boolean collapse(Entity entity, Point newP){
		List<Entity> entitiesToCheck = new ArrayList<Entity>();
		Rectangle rec = new Rectangle(entity.getBoundingBox());
		rec.setPosition(newP.x, newP.y);
		world.getSurroundingChunks(entity.getChunk()).forEach(c -> entitiesToCheck.addAll(this.getEntitiesByChunk(c.getIndex())));
		for(Entity e: entitiesToCheck){
			if(!e.equals(entity)){
				if(e.getBoundingBox().overlaps(rec)){
					return true;
				}
			}
		}
		return false;
	}
	
	//Gets all entities that are overlapping with the given rectangle
	public Collection<Entity> hitEntities(Rectangle rekt){
		Collection<Chunk> chunks = world.getSurroundingChunks(world.getChunkOfRealPosition(new Point(Math.round(rekt.x), Math.round(rekt.y))));
		List<Entity> entitiesToCheck = new ArrayList<Entity>();
		chunks.stream().forEach(c -> entitiesToCheck.addAll(getEntitiesByChunk(c.getIndex())));
		return entitiesToCheck.stream().filter(e -> rekt.overlaps(e.getHitBox())).collect(Collectors.toList());
	}

}
