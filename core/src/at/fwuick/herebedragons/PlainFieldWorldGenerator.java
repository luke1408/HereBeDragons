package at.fwuick.herebedragons;

import java.util.Random;

public class PlainFieldWorldGenerator extends WorldGenerator {

	Random r;
	public PlainFieldWorldGenerator() {
		this.r = new Random();
	}

	@Override
	public Chunk generateChunk(World w, Point p) {
		Chunk c = new Chunk(p);
		if(r.nextInt(5)==1)
			fillWithTiles(c);
		else
			fillGrassChunk(c);
		if(r.nextInt(2)==1){
			while(r.nextInt(3) != 1){
				Dummy d = new Dummy(w.entityManager);
				d.setPosition(new Point(c.getWorldPosition().x+r.nextInt(Chunk.CHUNK_SIZE), c.getWorldPosition().y+r.nextInt(Chunk.CHUNK_SIZE)));
				w.entityManager.spawn(d);
			}
		}
		return c;
	}
	
	public void fillGrassChunk(Chunk c){
		Ground hole = Ground.getGround("hole");
		c.fillGround(Ground.getGround("grass"));
		for(int i=r.nextInt(3); i!=0; i--){
			c.setGround(r.nextInt(16), r.nextInt(16), hole);
		}
		for(int i=r.nextInt(3); i!=0; i--){
			c.setGround(r.nextInt(16), r.nextInt(16), Ground.getGround("lift"));
		}
	}
	
	public void fillWithTiles(Chunk c){
		c.fillGround(Ground.getGround("tile"));
	}


}
