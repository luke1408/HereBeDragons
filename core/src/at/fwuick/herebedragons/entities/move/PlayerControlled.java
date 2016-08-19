package at.fwuick.herebedragons.entities.move;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import at.fwuick.herebedragons.entities.Entity;
import at.fwuick.herebedragons.entities.Player;
import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.Point.Distance;

public class PlayerControlled extends VelocityBuildUp{

	public PlayerControlled() {
		super(new Point());
	}
	
	public Player getPlayer(){
		return (Player) entity;
	}
	
	public void setPlayer(Player player){
		this.entity = player;
	}
	
	

	@Override
	public void setEntity(Entity entity) {
		if(entity instanceof Player)
			setPlayer((Player) entity);
	}

	@Override
	public void move() {
		int x = 0;
		int y = 0;
		if(Gdx.input.isKeyPressed(Keys.W)){
			y++;
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			x--;
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			y--;
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			x++;
		}
		Distance velocity = this.position.new Distance(x, y);
		if(!this.velocity.equals(velocity) )
			this.setVelocity(velocity);
		super.move();
		if(!stopped(velocity)){
			if(Math.signum(velocity.x) == -1)
				getPlayer().walk("left");
			if(Math.signum(velocity.x) == 1)
				getPlayer().walk("right");
			if(Math.signum(velocity.y) == -1)
				getPlayer().walk("down");
			if(Math.signum(velocity.y) == 1)
				getPlayer().walk("up");
		}
	}

	
}
