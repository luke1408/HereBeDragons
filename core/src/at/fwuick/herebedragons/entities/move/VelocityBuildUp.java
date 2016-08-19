package at.fwuick.herebedragons.entities.move;

import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.Point.Distance;

public class VelocityBuildUp extends VelocityBased {
	//The controlled is a movement type that can be cobntrolled by external logic
	// You just tell the Controlled where to Go this tick and Controlled uses velocity based movement
	
	
	//Top Speed pixel/sek
	private int maxSpeed;
	//how many ticks the entity needs to reach top speed
	private int zeroToTop;
	private int buildup;
	
	public VelocityBuildUp(Point position) {
		super(position, position.new Distance(0, 0));
		maxSpeed = 3;
		zeroToTop = 10;
		buildup = 0;
	}
	
	@Override
	public void move() {
		if(this.stopped(this.velocity)){
			buildup = 0;
		}else
		if(buildup < zeroToTop)
			buildup++;
		super.move();
		
	}
	
	@Override
	public void setVelocity(Distance velocity) {
		if(stopped(velocity) || 
				!(velocity.x != 0 && Math.signum(this.velocity.x) == Math.signum(velocity.x) ||
				(velocity.y != 0 && Math.signum(this.velocity.y) == Math.signum(velocity.y))))
			buildup = 0;
		super.setVelocity(velocity);
	}

	@Override
	public Distance velocity() {

		float currentSpeed = (maxSpeed * this.buildup) / (float)zeroToTop;
		if(velocity.x == 0){
			if(velocity.y == 0)
				return velocity;
			return this.position.new Distance(0, currentSpeed* Math.signum(velocity.y));
		}else if (velocity.y == 0) {
			return this.position.new Distance(currentSpeed * Math.signum(this.velocity.x), 0);
		}
		
		double alpha = Math.atan(Math.abs(this.velocity.y)/Math.abs(this.velocity.x));	
		double newX = Math.cos(alpha) *  currentSpeed * Math.signum(this.velocity.x);
		double newY = Math.sin(alpha) * currentSpeed * Math.signum(this.velocity.y);
		return position.new Distance((float)newX, (float)newY);
		
	}
	public double currentSpeed(){
		return Math.sqrt(Math.pow(velocity.x, 2) + Math.pow(velocity.y,2));
	}
	
	public boolean stopped(Distance velocity){
		return (velocity.x == 0 && velocity.y == 0);
	}
	
	
	
	

}
