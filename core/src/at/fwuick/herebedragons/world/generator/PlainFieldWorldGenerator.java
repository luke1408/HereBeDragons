package at.fwuick.herebedragons.world.generator;

import java.util.Random;

import at.fwuick.herebedragons.entities.enemies.Dummy;
import at.fwuick.herebedragons.entities.objects.Tree;
import at.fwuick.herebedragons.world.Chunk;
import at.fwuick.herebedragons.world.Ground;
import at.fwuick.herebedragons.world.Point;
import at.fwuick.herebedragons.world.World;

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
		else{
			fillGrassChunk(c);
			if(r.nextInt(3) == 1)
				genTrees(c, w);
		}
		if(r.nextInt(2)==1){
			while(r.nextInt(3) != 1){
				Dummy d = new Dummy();
				d.setPosition(new Point(c.getWorldPosition().x+r.nextInt(Chunk.CHUNK_SIZE), c.getWorldPosition().y+r.nextInt(Chunk.CHUNK_SIZE)));
				w.entityManager.spawn(d);
			}
		}
		return c;
	}
	
	private void genTrees(Chunk c, World w) {
		for(int i=0; i!=10; i++){
			Tree t = new Tree();
			t.setPosition(new Point(c.getWorldPosition().x+r.nextInt(Chunk.CHUNK_SIZE), c.getWorldPosition().y+r.nextInt(Chunk.CHUNK_SIZE)));
			w.entityManager.spawn(t);
		}
		
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
