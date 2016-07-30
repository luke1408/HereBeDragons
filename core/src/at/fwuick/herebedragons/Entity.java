package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	protected PointHistory position;
	protected EntityManager manager;
	
	
	
	public Entity() {
		super();
		position = new PointHistory();
	}


	public class PointHistory{
		private Point last;
		private Point current;
		
		public void set(Point p){
			this.last = current;
			this.current = p;
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
}
