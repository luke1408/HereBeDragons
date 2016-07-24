package at.fwuick.herebedragons;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Dummy extends Creature{

	public static final Random randomness = new Random();
	private int textureIndex;
	
	public Dummy(){
		textureIndex = randomness.nextInt(5)+1;
	}

	@Override
	public Texture getTexture() {
		return TextureStorage.load(String.format("puppet%s", textureIndex));
	}

	public void setPosition(Point point) {
		this.position = point;
		
	}


}
