package at.fwuick.herebedragons.entities.move;

import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.Point.Distance;

//Just stands on a position
public class Vegetable extends Movement{

	
	public Vegetable() {
		position = new Point();
	}
	
	@Override
	public Distance velocity() {
		return this.getPosition().new Distance(0, 0);
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void move() {
		
	}

	@Override
	public Point getLastPosition() {
		return getPosition();
	}
	
	
	
}
