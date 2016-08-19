package at.fwuick.herebedragons.entities.move;

import at.fwuick.herebedragons.entities.Entity;
import at.fwuick.herebedragons.world.Chunk;
import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.Point.Distance;

public abstract class Movement {
	Point position;
	Entity entity;
	public abstract Distance velocity();
	public abstract Point getPosition();
	public abstract void move();
	public abstract Point getLastPosition();
	
	public void tick(){
		this.move();
		if(!Chunk.indexFromCoord(this.getPosition()).equals(Chunk.indexFromCoord(this.getLastPosition()))){
			this.entity.notifyManager();
		}
	}
	
	public void setPosition(Point p){
		this.position = p;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
		
	}
}
