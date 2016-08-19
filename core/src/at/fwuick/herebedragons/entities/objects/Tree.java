package at.fwuick.herebedragons.entities.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import at.fwuick.herebedragons.TextureStorage;
import at.fwuick.herebedragons.entities.Entity;
import at.fwuick.herebedragons.entities.move.Movement;

public class Tree extends Entity{

	@Override
	public Texture getTexture() {
		return TextureStorage.load("tree");
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(0, 0, getBounds().x, 60);
	}
	
	
	
}
