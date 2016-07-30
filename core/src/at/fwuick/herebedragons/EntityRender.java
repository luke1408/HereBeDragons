package at.fwuick.herebedragons;

import java.util.Collection;
import java.util.Comparator;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
				return Integer.compare(o2.getPosition().y, o1.getPosition().y);
			}
		};
		entities.stream().sorted(backToForth).forEach(e -> {
			render(batch, e);
		});
	}

	//RENDER AN ENTIITY
	public void render(SpriteBatch batch, Entity e){
		e.render(batch, world.getRenderPosition(e.getPosition()));

	}

}
