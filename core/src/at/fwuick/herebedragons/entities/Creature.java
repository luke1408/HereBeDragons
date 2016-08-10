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
	
	private int damageAnimation;
	public final static int DEFAULT_DAMAGE_ANIMATION = 5;
	public final static int DEFAULT_CORPSE_TIMER = 100;
	
	//-1 = not dead
	//0 = despawn
	// >0 = corpses
	private int deathvariable = -1;
	
	public Creature(Health health){
		super();
		this.health = health;
		this.needTick = true;
	}

	public void dealDamage(int i) {
		damageAnimation = DEFAULT_DAMAGE_ANIMATION;
		health.dealDamage(i);
		if(health.dead())
			die();
		
	}
	

	@Override
	public void tick() {
		if(deathvariable == 0){
			this.despawn();
			return;
		}
		if(damageAnimation > 0)
			damageAnimation--;
		if(deathvariable > 0)
			deathvariable--;
	}
	
	public void die(){
		deathvariable = DEFAULT_CORPSE_TIMER;
	}
	
	public boolean showCorpse(){
		return deathvariable>=0;
	}
	
	public boolean blinkCorpse(){
		return showCorpse() && deathvariable<=30 && deathvariable/4%2 == 1;
	}
	
	

	@Override
	public Rectangle getBoundingBox() {
		if(showCorpse())
			return new Rectangle();
		return super.getBoundingBox();
	}

	@Override
	public Rectangle getHitBox() {
		if(showCorpse())
			return new Rectangle();
		return super.getHitBox();
	}

	@Override
	public void render(SpriteBatch batch, Point renderPosition) {
		Sprite shadow = new Sprite(TextureStorage.load("shadow"));
		Sprite sprite = new Sprite(this.getTexture());
		sprite.setSize(sprite.getWidth()*2, sprite.getHeight()*2);
		if(damageAnimation > 0)
			sprite.setColor(Color.RED);
		shadow.setSize(sprite.getWidth(), sprite.getHeight());
		sprite.setPosition(renderPosition.x, renderPosition.y);
		shadow.setPosition(renderPosition.x, renderPosition.y-10);
		shadow.draw(batch);
		if(!blinkCorpse())
			sprite.draw(batch);
	}
	
	
}
