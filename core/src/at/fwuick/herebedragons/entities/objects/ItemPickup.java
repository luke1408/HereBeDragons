package at.fwuick.herebedragons.entities.objects;

import com.badlogic.gdx.graphics.Texture;

import at.fwuick.herebedragons.entities.Player;
import at.fwuick.herebedragons.item.Item;

public class ItemPickup extends Pickup{

	Item item;
	
	public ItemPickup(Item item) {
		super();
		this.item = item;
	}

	@Override
	public void pickup(Player player) {
		player.inventory().add(this.item);
		
	}

	@Override
	public Texture getTexture() {
		return item.getTexture();
	}

}
