package at.fwuick.herebedragons.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import at.fwuick.herebedragons.TextureStorage;
import at.fwuick.herebedragons.entities.move.Movement;
import at.fwuick.herebedragons.entities.move.Vegetable;
import at.fwuick.herebedragons.world.Chunk;
import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.Point.Distance;

public abstract class Entity {
	protected EntityManager manager;
	//Hitbox/bounds of an entity
	protected Vector2 bounds;
	
	public boolean needTick;
	public boolean canHit;
	public boolean canWalkthrough;
	
	public Movement move;
	
	
	
	public Entity(Movement move) {
		super();
		this.setMovement(move);
	}
	
	public Entity(){
		this(new Vegetable());
	}
	
	public void setMovement(Movement move){
		move.setEntity(this);
		this.move = move;
	}
	
	public void setEntityManager(EntityManager manager){
		this.manager = manager;
	}
	
	public abstract Texture getTexture();
	
	public Point getPosition(){
		return move.getPosition();
	}
	
	public void setPosition(Point p){
		notifyManager();
		this.move.setPosition(p);
	}
	
	public Vector2 getBounds(){
		if(bounds == null){
			Texture t = getTexture();
			this.bounds = new Vector2(t.getWidth(), t.getHeight());
		}
		return this.bounds;
	}
	
	public Rectangle getBoundingBox(){
		return new Rectangle(getPosition().x, getPosition().y, getBounds().x, getBounds().y/4);
	}
	
	public Rectangle getHitBox(){
		return new Rectangle(getPosition().x, getPosition().y, getBounds().x*2, getBounds().y);
	}
	
	//Reports a chanage of position to the EntityManager if he exists
	public void notifyManager(){
		if(manager!=null)
			manager.reportChange(this, this.move.getLastPosition(), this.move.getPosition());
	}
		
	public void render(SpriteBatch batch, Point renderPosition){
		renderShadow(batch, renderPosition);
		Sprite sprite = new Sprite(this.getTexture());
		sprite.setSize(sprite.getWidth(), sprite.getHeight());
		sprite.setPosition(renderPosition.x, renderPosition.y);
		sprite.draw(batch);
	}
	
	public Chunk getChunk(){
		return this.manager.chunkFromEntity(this);
	}
	
	public void tick(){
		this.move.tick();
	}
	
	public void despawn(){
		manager.despawn(this);
	}
	
	public Random random(){
		if(manager == null)
			return new Random();
		return manager.random();
	}
	
	//Is used to calculate the current velocity of an entity
	//Represented as an distance
	public Distance velocity(){
		return this.move.velocity();
	}

	public boolean collapse(Point newPoint) {
		return this.manager.collapse(this, newPoint);
	}
	
	//Get Shadow Rectangle positioned relatively from 0/0 of the texture
	public Rectangle getShadow(){
		Rectangle rectangle =  new Rectangle();
		rectangle.setSize(this.getBounds().x, (float) (this.getBounds().y * 0.2));
		rectangle.setPosition(0, -(rectangle.height/2));
		return rectangle;
	}
	
	public void renderShadow(SpriteBatch batch, Point renderPositon){
		Sprite shadow = new Sprite(TextureStorage.load("shadow"));
		Rectangle shadowRectangle = this.getShadow();
		shadow.setSize(shadowRectangle.width, shadowRectangle.height);
		shadow.setPosition(renderPositon.x + shadowRectangle.x, renderPositon.y + shadowRectangle.y);
		shadow.draw(batch);
	}
}
