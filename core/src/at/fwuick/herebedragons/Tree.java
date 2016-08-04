package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;

public class Tree extends Entity{

	@Override
	public Texture getTexture() {
		return TextureStorage.load("tree");
	}
	
}
