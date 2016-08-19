package at.fwuick.herebedragons.entities.move;

import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.Point.Distance;

public class VelocityBased extends Movement {
	public Distance velocity;
	public Point last;
	
	public VelocityBased(Point position, Distance velocity) {
		super();
		this.position = position;
		this.last = position;
		this.velocity = velocity;
	}

	@Override
	public Distance velocity() {
		// TODO Auto-generated method stub
		return velocity;
	}
	
	public void setVelocity(Distance velocity){
		this.velocity = velocity;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void move() {
		Point newPoint= this.position.moveDistance(this.velocity());
		if(!this.entity.collapse(newPoint)){
			this.setPosition(newPoint);
			return;
		}
			
		//Wenn du quer läufst soll er der Wand entlang gehen und nicht stoppen 
		//Deswegen das hier
		Point onlyX = newPoint.clone();
		onlyX.y = this.getPosition().y;
		Point onlyY = newPoint.clone();
		onlyY.x = this.getPosition().x;
		if(!this.entity.collapse(onlyX))
			this.setPosition(onlyX);
		else if(!this.entity.collapse(onlyY))
			this.setPosition(onlyY);

	}

	@Override
	public Point getLastPosition() {
		return last;
	}

	@Override
	public void setPosition(Point p) {
		this.last = this.getPosition();
		super.setPosition(p);
	}

	
}
