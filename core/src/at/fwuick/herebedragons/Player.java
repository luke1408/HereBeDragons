package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Creature {

	private String direction;
	private int walkCounter;
	private boolean walking;

	public Player(Point p){
		super(Health.IMMORTAL);
		this.position = p;
		direction = "down";
		walkCounter = 1;
	}
	
	public Player(){
		this(new Point());
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
		position.y++;
		direction = "up";
		walking = true;
	}
	
	public void goEast(){
		position.x++;
		direction = "right";
		walking = true;
	}
	
	public void goSouth(){
		position.y--;
		direction = "down";
		walking = true;
	}
	
	public void goWest(){
		position.x--;
		direction = "left";
		walking = true;
	}
	
	
}
