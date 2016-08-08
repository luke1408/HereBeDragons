package at.fwuick.herebedragons.entities.objects;

import com.badlogic.gdx.graphics.Texture;

import at.fwuick.herebedragons.TextureStorage;
import at.fwuick.herebedragons.entities.Entity;

public class Tree extends Entity{

	@Override
	public Texture getTexture() {
		return TextureStorage.load("tree");
	}
	
}
