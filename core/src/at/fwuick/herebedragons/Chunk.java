package at.fwuick.herebedragons;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chunk {
	public final static int CHUNK_DIMENSION = 16;
	public final static int BLOCK_SIZE = 30;
	public final static int CHUNK_SIZE = CHUNK_DIMENSION * BLOCK_SIZE;
	private Point index;
	//Ground Array: Holds every Ground object in the chunk in a BLOCK_SIZE * BLOCK_SIZE array
	private GroundTexture[][] ground;
	
	public Chunk(Point p){
		ground = new GroundTexture[CHUNK_DIMENSION][CHUNK_DIMENSION];
		this.index = p;
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
	public void setGround(int i, int j, Ground g) {
		ground[i][j] = g.getRandomGroundTexture();		
	}
	
	public void render(SpriteBatch batch, Point p){
		for(int i=0; i<CHUNK_DIMENSION; i++){
			for(int j=0; j<CHUNK_DIMENSION; j++){
				int x = i*BLOCK_SIZE +p.x;
				int y = j*BLOCK_SIZE + p.y;
				ground[i][j].render(batch, new Point(x, y));
				
			}
		}
	}
	
	public Point getWorldPosition(){
		return new Point(this.index.x*CHUNK_SIZE, this.index.y*CHUNK_SIZE);
	}
	
	public static int indexFromCoord(int coord){
		int preIndex = coord/CHUNK_SIZE;
		return coord<0?preIndex-1:preIndex;
	}
	
	public static Point indexFromCoord(Point p){
		return new Point(indexFromCoord(p.x), indexFromCoord(p.y));
	}
}
