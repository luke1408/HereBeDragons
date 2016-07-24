package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
	protected Point position;
	
	public abstract Texture getTexture();
	public Point getPosition(){
		return position;
	}
	
}
