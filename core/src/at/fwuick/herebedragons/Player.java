package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Creature {

	//Needed for walking animation{
	private String direction;
	private int walkCounter;
	private boolean walking;
	//}

	public Player(Point p){
		super(Health.IMMORTAL);
		this.position = new PointHistory();
		this.position.set(p);
		direction = "down";
		walkCounter = 1;
	}
	
	public Player(){
		this(new Point());
	}
	
	public Texture getTexture(){
		Texture t = new Texture(String.format("assets/%s_%s_%s.png", "player", direction, walking?getTextureIndex():"stand"));
		bounds.height=t.getHeight();
		bounds.width=t.getWidth();
		walking = false;
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
	public void refreshBounds(){
		bounds.x=position.x;
		bounds.y=position.y;
	}
	
	private int getWalkCounter(){
		refreshBounds();
		walkCounter++;
		return walkCounter/10%2+1;
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
	
	
	
	
	
}
