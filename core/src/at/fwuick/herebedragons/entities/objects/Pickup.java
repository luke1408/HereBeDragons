package at.fwuick.herebedragons.entities.objects;

import com.badlogic.gdx.graphics.Texture;

import at.fwuick.herebedragons.entities.Entity;
import at.fwuick.herebedragons.entities.Player;

public abstract class Pickup extends Entity {
	
	public Pickup(){
		this.canWalkthrough = true;
	}
	
	public abstract void pickup(Player player);

	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
