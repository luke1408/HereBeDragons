package at.fwuick.herebedragons;

import java.util.Random;

public class PlainFieldWorldGenerator extends WorldGenerator {

	Random r;
	public PlainFieldWorldGenerator() {
		this.r = new Random();
	}

	@Override
	public Chunk generateChunk(Point p) {
		Ground hole = Ground.getGround("hole");
		Chunk c = new Chunk(p);
		c.fillGround(Ground.getGround("grass"));
		for(int i=r.nextInt(3); i!=0; i--){
			c.setGround(r.nextInt(16), r.nextInt(16), hole);
		}
		for(int i=r.nextInt(3); i!=0; i--){
			c.setGround(r.nextInt(16), r.nextInt(16), Ground.getGround("lift"));
		}
		return c;
	}

}
