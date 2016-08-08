package at.fwuick.herebedragons.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import at.fwuick.herebedragons.TextureStorage;
import at.fwuick.herebedragons.world.Point;

public abstract class Creature extends Entity{
	
	//HEALTH OBJECT
	private Health health;
	
	public Creature(Health health){
		super();
		this.health = health;
	}

	public void dealDamage(int i) {
		health.dealDamage(i);
		
	}

	@Override
	public void render(SpriteBatch batch, Point renderPosition) {
		Sprite shadow = new Sprite(TextureStorage.load("shadow"));
		Sprite sprite = new Sprite(this.getTexture());
		sprite.setSize(sprite.getWidth()*2, sprite.getHeight()*2);
		if(health.justHurt())
			sprite.setColor(Color.RED);
		shadow.setSize(sprite.getWidth(), sprite.getHeight());
		sprite.setPosition(renderPosition.x, renderPosition.y);
		shadow.setPosition(renderPosition.x, renderPosition.y-10);
		shadow.draw(batch);
		sprite.draw(batch);
	}
	
	
	
	
}
