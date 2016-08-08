package at.fwuick.herebedragons.entities;

public class Health{
	//IMMORTAL Health object for easy immortality
	public static final Health IMMORTAL = new Health(0){

		@Override
		public void dealDamage(int damage) {
		}

		@Override
		public void heal(int value) {
		}

		@Override
		public boolean dead() {
			return false;
		}
		
		
	};
	
	private int maxHealth;
	private int currentHealth;
	private boolean hurt;
	
	public Health(int health){
		maxHealth = health;
		currentHealth = maxHealth;
	}
	
	public void dealDamage(int damage){
		currentHealth -= damage>currentHealth?currentHealth:damage;
		hurt = true;
	}
	
	public boolean justHurt(){
		if(hurt){
			hurt = false;
			return true;
		}
		return hurt;
	}
	
	public void heal(int value){
		currentHealth += value;
		if(currentHealth>maxHealth){
			currentHealth = maxHealth;
		}
	}
	
	public boolean dead(){
		return currentHealth == 0;
	}
}
