package at.fwuick.herebedragons;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chunk {
	public final static int CHUNK_DIMENSION = 16;
	public final static int BLOCK_SIZE = 30;
	public final static int CHUNK_SIZE = CHUNK_DIMENSION * BLOCK_SIZE;
	
	//Ground Array: Holds every Ground object in the chunk in a BLOCK_SIZE * BLOCK_SIZE array
	private Ground[][] ground;
	
	public Chunk(){
		ground = new Ground[CHUNK_DIMENSION][CHUNK_DIMENSION];
	}
	
	//Fills the whole ground with one specific
	public void fillGround(Ground g){
		for(int i=0; i<CHUNK_DIMENSION; i++){
			for(int j=0; j<CHUNK_DIMENSION; j++){
				setGround(i, j, g);
			}
		}
	}
	
	//Sets the ground tile by tile
	private void setGround(int i, int j, Ground g) {
		ground[i][j] = g;		
	}
	
	public void render(SpriteBatch batch, Point p){
		for(int i=0; i<CHUNK_DIMENSION; i++){
			for(int j=0; j<CHUNK_DIMENSION; j++){
				int x = i*BLOCK_SIZE;
				int y = j*BLOCK_SIZE;
				ground[i][j].render(batch, new Point(x, y));
				
			}
		}
	}
}
