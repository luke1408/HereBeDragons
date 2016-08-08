package at.fwuick.herebedragons.world.generator;

import at.fwuick.herebedragons.world.Chunk;
import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.World;

public abstract class WorldGenerator {
	//Generates a chunk on the position p
	public abstract Chunk generateChunk(World w, Point p);
	
}
