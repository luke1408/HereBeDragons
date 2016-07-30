package at.fwuick.herebedragons;

public abstract class Creature extends Entity{
	
	//HEALTH OBJECT
	private Health health;
	
	public Creature(Health health){
		super();
		this.health = health;
	}
	
}
