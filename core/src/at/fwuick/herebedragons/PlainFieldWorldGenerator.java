package at.fwuick.herebedragons;

import java.awt.Point;

public class PlainFieldWorldGenerator implements WorldGenerator {

	@Override
	public Chunk generateChunk(Point p) {
		Chunk c = new Chunk();
		c.fillGround(Ground.getGround("grass"));
		return c;
	}

}
