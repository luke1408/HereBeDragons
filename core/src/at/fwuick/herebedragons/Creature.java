package at.fwuick.herebedragons;

public abstract class Creature extends Entity{
	
	//HEALTH OBJECT
	private Health health;
	
	public Creature(EntityManager manager, Health health){
		super(manager);
		this.health = health;
	}
	
}
