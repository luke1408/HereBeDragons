package at.fwuick.herebedragons.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import at.fwuick.herebedragons.TextureStorage;
import at.fwuick.herebedragons.world.Chunk;
import at.fwuick.herebedragons.world.Point;

public abstract class Entity {
	protected PointHistory position;
	protected EntityManager manager;
	//Hitbox/bounds of an entity
	protected Vector2 bounds;
	
	
	
	public Entity() {
		super();
		position = new PointHistory(this);
	}


	public class PointHistory{
		private Point last;
		private Point current;
		private Entity entity;
		
		public PointHistory(Entity e){
			this.entity = e;
		}
		
		public void set(Point p){
			if(manager == null || !manager.collapse(this.entity, p)){
				this.last = current;
				this.current = p;
			}
		}
		
		public Point get(){
			return current;
		}
		
		public Point history(){
			return last;
		}
		
		public void goNorth(int i){
			set(new Point(get()).goNorth(i));
			notifyManager();
		}
		
		public void goEast(int i){
			set(new Point(get()).goEast(i));
			notifyManager();
		}
		
		public void goSouth(int i){
			set(new Point(get()).goSouth(i));
			notifyManager();
		}
		
		public void goWest(int i){
			set(new Point(get()).goWest(i));
			notifyManager();
		}
	}
	
	public void setEntityManager(EntityManager manager){
		this.manager = manager;
	}
	
	public abstract Texture getTexture();
	
	public Point getPosition(){
		return position.current;
	}
	
	public void setPosition(Point p){
		notifyManager();
		this.position.set(p);
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
			manager.reportChange(this, position.last, position.get());
	}
		
	public void render(SpriteBatch batch, Point renderPosition){
		Sprite shadow = new Sprite(TextureStorage.load("shadow"));
		Sprite sprite = new Sprite(this.getTexture());
		sprite.setSize(sprite.getWidth()*2, sprite.getHeight()*2);
		shadow.setSize(sprite.getWidth(), sprite.getHeight());
		sprite.setPosition(renderPosition.x, renderPosition.y);
		shadow.setPosition(renderPosition.x, renderPosition.y-10);
		shadow.draw(batch);
		sprite.draw(batch);
	}
	
	public Chunk getChunk(){
		return this.manager.chunkFromEntity(this);
	}
}
