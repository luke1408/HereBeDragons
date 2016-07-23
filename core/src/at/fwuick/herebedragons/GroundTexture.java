package at.fwuick.herebedragons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GroundTexture {
	private Ground ground;
	private int textureIndex;
	
	
	
	protected GroundTexture(Ground ground, int textureIndex) {
		super();
		this.ground = ground;
		this.textureIndex = textureIndex;
	}
	
	public Ground getGround() {
		return ground;
	}
	public int getTextureIndex() {
		return textureIndex;
	}
	
	public Texture getTexture(){
		return ground.getTexture(textureIndex);
	}

	public void render(SpriteBatch batch, Point p) {
		batch.draw(getTexture(), p.x, p.y);
		
	}
	
	
}
