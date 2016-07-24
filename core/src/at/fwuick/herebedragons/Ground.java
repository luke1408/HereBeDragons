package at.fwuick.herebedragons;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ground {
	//VARIABLES
	private String name;
	private Texture[] textures;
	//Registry holds all instances of Ground class
	public final static Map<String, Ground> REGISTRY = new HashMap<>();
	private final static Random randomness = new Random();
	
	public static void init(){
		//Register Grounds here
		register(new Ground("grass", 2));
		register(new Ground("hole", 1));
		register(new Ground("lift", 1));
		register(new Ground("tile", 1));
	}
	
	//Registers the entry in the static REGISTRY
	private static void register(String s, Ground g){
		REGISTRY.put(s.toUpperCase(), g);
	}
	//Registers the entry in the static REGISTRY with own name
	private static void register(Ground g){
		register(g.name, g);
	}
	
	
	
	//CONSTRUCTORS
	//Sets the Texture to be a png with the name in the assets directory
	private Ground(String name){
		this(name, loadTexturesFromName(name, 1));
	}
	
	private Ground(String name, Texture... textures){
		this.name = name;
		//TODO: Look if texture exists... If not Exception
		this.textures = textures;
	}
	
	private static Texture[] loadTexturesFromName(String name, int amount){
		Texture[] texs = new Texture[amount];
		for(int j =0; j<amount; j++){
			texs[j] = loadTextureFromPath(String.format("assets/%s%d.png", name, j+1));
		}
		return texs;
	}
	
	private static Texture loadTextureFromPath(String path){
		return new Texture(path);
	}
	
	private Ground(String name, int i) {
		
		this(name, loadTexturesFromName(name, i));
	}
	//
	


	public int howManyTextures(){
		return textures.length;
	}
	
	//Picks the only or a random
	public Texture getTexture(){
		return howManyTextures()>1?
				getTexture(randomness.nextInt(howManyTextures())):
					textures[0];
	}
	
	public void render(SpriteBatch batch, Point p){
		batch.draw(getTexture(), p.x, p.y);
	}
	
	
	public Texture getTexture(int index){
		if(index >= howManyTextures())
			return textures[howManyTextures()-1];
		else
			return textures[index];
	}

	public static Ground getGround(String string) {
		return REGISTRY.get(string.toUpperCase());
	}

	//Gets a Random GroundTexture
	public GroundTexture getRandomGroundTexture() {
		return getGroundTexture(randomIndex());
	}
	
	//Gets a GroundTexture object for the Texture Index given
	//KEEPS CONSISTENCY
	private Map<Integer, GroundTexture> groundtextures = new HashMap<Integer, GroundTexture>();
	public GroundTexture getGroundTexture(int index){
		if(!groundtextures.containsKey(index))
			groundtextures.put(index, new GroundTexture(this, index));
		return groundtextures.get(index);
	}
	
	//Returns a valid random texture index
	public int randomIndex(){
		return howManyTextures()>1?randomness.nextInt(howManyTextures()):0;
	}
}
