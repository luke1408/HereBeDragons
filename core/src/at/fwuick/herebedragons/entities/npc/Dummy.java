package at.fwuick.herebedragons.entities.npc;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import at.fwuick.herebedragons.TextureStorage;
import at.fwuick.herebedragons.entities.Creature;
import at.fwuick.herebedragons.entities.Health;
import at.fwuick.herebedragons.entities.objects.CoinPickup;
import at.fwuick.herebedragons.entities.objects.Pickup;
import at.fwuick.herebedragons.world.Point;

public class Dummy extends Creature{

	public static final Random randomness = new Random();
	private int textureIndex;
	
	public Dummy(){
		super(new Health(20));
		textureIndex = randomness.nextInt(5)+1;
	}

	@Override
	public Texture getTexture() {
		if(this.showCorpse())
			return TextureStorage.load("puppet_corpse");
		return TextureStorage.load(String.format("puppet%s", textureIndex));
	}

	public void setPosition(Point point) {
		this.position.set(point);
	}

	@Override
	public void drop() {
		// TODO Auto-generated method stub
		super.drop();
		Pickup[] pickup = new Pickup[random().nextInt(4)+1];
		for(int i=0; i!=pickup.length; i++)
			pickup[i] = new CoinPickup();
		dropPickups(pickup);
	}
	
	
	
	
	

}
