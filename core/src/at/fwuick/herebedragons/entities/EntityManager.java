package at.fwuick.herebedragons.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.badlogic.gdx.math.Rectangle;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import at.fwuick.herebedragons.entities.objects.Pickup;
import at.fwuick.herebedragons.world.Chunk;
import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.World;

//This holds the collection of all Entities in a world
//It must be easy to find the entities per chunks
//This is the class to do that
public class EntityManager {

	//Entities HashMultiset to get the entites by the index of a chunk
	private Multimap<Point, Entity> entities;
	public World world;
	private Random random;
	
	public  EntityManager(World w){
		entities = HashMultimap.create();
		this.world = w;
		entityFrame = new ArrayList<>();
		random = new Random();
	}
	
	//Entities that should get tick() and render()
	private List<Entity> entityFrame;
	
	public void tick(){
		Collection<Chunk> chunks = world.chunkFrame;
		//Generate new entityFrame
		entityFrame.clear();
		chunks.forEach(c -> entityFrame.addAll(getEntitiesByChunk(c.getIndex())));
		//Tick entityFrame
		entityFrame.stream().filter(e -> e.needTick).forEach(e -> e.tick());
		//Checks for pickups
		pickups();
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
		List<Entity> entitiesToCheck = entityFrame;
		Rectangle rec = new Rectangle(entity.getBoundingBox());
		rec.setPosition(newP.x, newP.y);
		for(Entity e: entitiesToCheck){
			if(!e.equals(entity)&&!e.canWalkthrough){
				if(e.getBoundingBox().overlaps(rec)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void pickups(){
		entityFrame.stream().filter(e -> e instanceof Pickup).map(e -> (Pickup)e).filter(p -> p.getBoundingBox().overlaps(this.world.player.getBoundingBox())).forEach(p -> p.pickup(this.world.player));
	}
	
	//Gets all entities that are overlapping with the given rectangle
	public Collection<Entity> hitEntities(Rectangle rekt){
		return entityFrame.stream().filter(e -> e.canHit).filter(e -> rekt.overlaps(e.getHitBox())).collect(Collectors.toList());
	}

	public void despawn(Entity entity) {
		this.entities.remove(entity.getChunk().getIndex(), entity);
		
	}

	public Random random() {
		return random;
	}

}
