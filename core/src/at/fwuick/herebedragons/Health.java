package at.fwuick.herebedragons;

public class Health{
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
	
	public Health(int health){
		maxHealth = health;
		currentHealth = maxHealth;
	}
	
	public void dealDamage(int damage){
		currentHealth -= damage>currentHealth?currentHealth:damage;
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
