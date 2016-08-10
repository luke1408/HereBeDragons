package at.fwuick.herebedragons.entities;

import java.util.Collection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import at.fwuick.herebedragons.item.Inventory;
import at.fwuick.herebedragons.item.Item;
import at.fwuick.herebedragons.world.Point;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Creature {
	
	private Inventory inventory;
	
	//Needed for walking animation{
	private String direction;
	private int walkCounter;
	private boolean walking;
	//}
	//Needed for punching aniation
	private int punchCooldown;
	public static final int DEFAULT_PUNCH = 20;
	private int punchingAnimation;
	public static final int DEFAULT_PUNCHING_ANIMATION = 10;

	public Player(Point p){
		super(Health.IMMORTAL);
		this.position = new PointHistory(this);
		this.position.set(p);
		direction = "down";
		walkCounter = 1;
		this.inventory = new Inventory();
	}
	
	public Player(){
		this(new Point());
	}
	
	@Override
	public void tick(){
		if(punchCooldown > 0)
			punchCooldown--;
		if(punchingAnimation > 0)
			punchingAnimation--;
	}
	
	
	
	@Override
	public void render(SpriteBatch batch, Point renderPosition) {
		// TODO Auto-generated method stub
		super.render(batch, renderPosition);
		walking = false;
	}

	public Texture getTexture(){
		String s = walking?""+getTextureIndex():"stand";
		if(punchingAnimation > 0)
			s = "punch";
		Texture t = new Texture(String.format("assets/%s_%s_%s.png", "player", direction, s));
		return t;
		
	}
	
	//This is needed for automatizing the walking animation
	private void walk(String direction){
		this.direction = direction;
		walking = true;
		if(walkCounter>=19)
			walkCounter = 1;
		else
			walkCounter++;
	}
	
	private int getTextureIndex(){
		return (walkCounter/10)+1 ;
	}
	
	public void goNorth(){
		walk("up");
		this.position.goNorth(1);
	}
	
	public void goEast(){
		walk("right");
		this.position.goEast(1);
	}
	
	public void goSouth(){
		walk("down");
		this.position.goSouth(1);
	}
	
	public void goWest(){
		walk("left");
		this.position.goWest(1);
	}
	
	public void punch(){
		if(punchCooldown > 0)
			return;
		punchCooldown = DEFAULT_PUNCH;
		punchingAnimation = DEFAULT_PUNCHING_ANIMATION;
		Collection<Entity> entities = manager.hitEntities(punchRectangle());
		entities.forEach(e -> {
			if(e instanceof Creature && !(e instanceof Player))
				((Creature)e).dealDamage(10);
		});
		
	}
	
	public Rectangle punchRectangle(){
		Rectangle rec = null;
		switch (direction){
		case "up":
			rec = new Rectangle(20, 12, 2, 12);
			break;
		case "down":
			rec = new Rectangle(20, 10, 2, 5);
			break;
		case "left":
			rec = new Rectangle(5, 12, 10, 3);
			break;
		case "right":
			rec = new Rectangle(16, 12, 9, 3);
			break;
		}
		return new Rectangle(this.getPosition().x + (rec.x*2), this.getPosition().y + (rec.y*2) ,rec.width*2, rec.height*2);
	}
	
	public Inventory inventory(){
		return inventory;
	}
	
}
