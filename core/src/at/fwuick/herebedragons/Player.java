package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Creature {

	private String direction;
	private int walkCounter;
	private boolean walking;

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
		Texture t = new Texture(String.format("assets/%s_%s_%s.png", "player", direction, walking?getWalkCounter():"stand"));
		walking = false;
		return t;
		
	}
	
	private int getWalkCounter(){
		walkCounter++;
		return walkCounter/10%2+1;
	}
	


	@Override
	public void render(SpriteBatch batch, Point renderPosition) {
		// TODO Auto-generated method stub
		super.render(batch, renderPosition);
	}
	
	
	
	
}
