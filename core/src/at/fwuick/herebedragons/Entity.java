package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
	protected Point position;
	//Hitbox/bounds of an entity
	protected Rectangle bounds = new Rectangle();
	
	public abstract Texture getTexture();
	public Point getPosition(){
		return position;
	}
	
}
