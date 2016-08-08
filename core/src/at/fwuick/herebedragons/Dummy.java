package at.fwuick.herebedragons;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Dummy extends Creature{

	public static final Random randomness = new Random();
	private int textureIndex;
	
	public Dummy(){
		super(new Health(20));
		textureIndex = randomness.nextInt(5)+1;
	}

	@Override
	public Texture getTexture() {
		return TextureStorage.load(String.format("puppet%s", textureIndex));
	}

	public void setPosition(Point point) {
		this.position.set(point);
	}

}
