package at.fwuick.herebedragons;

import java.util.Collection;
import java.util.Comparator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import at.fwuick.herebedragons.entities.Entity;
import at.fwuick.herebedragons.entities.Player;
import at.fwuick.herebedragons.entities.objects.Tree;
import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.World;

public class EntityRender {
	
	private Collection<Entity> lastRendered;
	private World world;
	
	
	public EntityRender(World world) {
		super();
		this.world = world;
	}

	//Call this function only once per frame
	//This function renders all the entities given to it
	public void renderEntities(Collection<Entity> entities, SpriteBatch batch){
		Comparator<Entity> backToForth = new Comparator<Entity>() {

			@Override
			public int compare(Entity o1, Entity o2) {
				return Float.compare(o2.getPosition().y, o1.getPosition().y);
			}
		};
		entities.stream().sorted(backToForth).forEach(e -> {
			render(batch, e);
		});
		/*Texture hitbox = TextureStorage.load("hitbox");
		entities.stream().sorted(backToForth).forEach(e -> {
			if(e instanceof Player || e instanceof Tree){
				Rectangle rek = e.getBoundingBox();
				Point recPoint = new Point(rek.x, rek.y);
				Point renderPoint = world.getRenderPosition(recPoint);
				Sprite sprite = new Sprite(hitbox);
				sprite.setSize(rek.width, rek.height);
				sprite.setPosition(renderPoint.x, renderPoint.y);
				sprite.draw(batch);
			}
		});*/
	}

	//RENDER AN ENTIITY
	public void render(SpriteBatch batch, Entity e){
		e.render(batch, world.getRenderPosition(e.getPosition()));
		
	}

}
