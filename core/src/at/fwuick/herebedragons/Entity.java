package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	protected Point position;
	protected EntityManager manager;
	
	public Entity(EntityManager manager){
		this.manager = manager;
	}
	
	public abstract Texture getTexture();
	
	public Point getPosition(){
		return position;
	}
	
	public void setPosition(Point p){
		manager.reportChange(this, position, p);
		this.position = p;
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
