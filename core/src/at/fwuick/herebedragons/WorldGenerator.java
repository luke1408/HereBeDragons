package at.fwuick.herebedragons;

import java.awt.Point;

public interface WorldGenerator {

	//Generates a chunk on the position p
	public Chunk generateChunk(Point p);
}
