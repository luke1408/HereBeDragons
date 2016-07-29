package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Creature {

	private String direction;
	private int walkCounter;
	private boolean walking;

	public Player(EntityManager m, Point p){
		super(m, Health.IMMORTAL);
		this.position = p;
		direction = "down";
		walkCounter = 1;
	}
	
	public Player(EntityManager m){
		this(m, new Point());
	}

	public Point getPoint() {
		return position;
	}
	
	public Texture getTexture(){
		Texture t = new Texture(String.format("assets/%s_%s_%s.png", "player", direction, walking?getWalkCounter():"stand"));
		walking = false;
		return t;
		
	}
	
	private int getWalkCounter(){
		walkCounter++;
		return walkCounter/10%2+1;
	}
	
	public void goNorth(){
		Point old = new Point(position);
		position.y++;
		manager.reportChange(this, old, position);
		direction = "up";
		walking = true;
	}
	
	public void goEast(){
		Point old = new Point(position);
		position.x++;
		manager.reportChange(this, old, position);
		direction = "right";
		walking = true;
	}
	
	public void goSouth(){
		Point old = new Point(position);
		position.y--;
		manager.reportChange(this, old, position);
		direction = "down";
		walking = true;
	}
	
	public void goWest(){
		Point old = new Point(position);
		position.x--;
		manager.reportChange(this, old, position);
		direction = "left";
		walking = true;
	}
	
	
}
