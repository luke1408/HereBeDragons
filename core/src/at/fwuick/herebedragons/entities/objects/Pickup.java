package at.fwuick.herebedragons.entities.objects;

import at.fwuick.herebedragons.entities.Entity;
import at.fwuick.herebedragons.entities.Player;

public abstract class Pickup extends Entity {
	public abstract void pickup(Player player);
}
