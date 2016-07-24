package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;

public class Player {

	private Point point;
	private String direction;
	private int walkCounter;
	private boolean walking;

	//STUB
	public Player(Point p){
		this.point = p;
		direction = "down";
		walkCounter = 1;
	}
	
	public Player(){
		this(new Point());
	}

	public Point getPoint() {
		return point;
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
		point.y++;
		direction = "up";
		walking = true;
	}
	
	public void goEast(){
		point.x++;
		direction = "right";
		walking = true;
	}
	
	public void goSouth(){
		point.y--;
		direction = "down";
		walking = true;
	}
	
	public void goWest(){
		point.x--;
		direction = "left";
		walking = true;
	}
	
	
}
