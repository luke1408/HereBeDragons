package at.fwuick.herebedragons;

import java.util.Collection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

//This holds the collection of all Entities in a world
//It must be easy to find the entities per chunks
//This is the class to do that
public class EntityManager {

	//Entities HashMultiset to get the entites by the index of a chunk
	private Multimap<Point, Entity> entities;
	
	public  EntityManager(){
		entities = HashMultimap.create();
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
		entities.put(Chunk.indexFromCoord(e.position), e);
	}

}
