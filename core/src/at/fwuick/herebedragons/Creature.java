package at.fwuick.herebedragons;

import com.badlogic.gdx.math.Rectangle;

public abstract class Creature extends Entity{
	
	//HEALTH OBJECT
	private Health health;
	
	public Creature(Health health){
		super();
		this.health = health;
	}
	
}
