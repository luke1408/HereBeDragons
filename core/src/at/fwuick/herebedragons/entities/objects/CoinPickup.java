package at.fwuick.herebedragons.entities.objects;

import com.badlogic.gdx.graphics.Texture;

import at.fwuick.herebedragons.TextureStorage;
import at.fwuick.herebedragons.entities.Player;

public class CoinPickup extends Pickup{

	@Override
	public void pickup(Player player) {
		player.inventory().coins++;
		this.despawn();
		
	}

	@Override
	public Texture getTexture() {
		return TextureStorage.load("coin");
	}

}
