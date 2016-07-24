package at.fwuick.herebedragons;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

public class TextureStorage {
	public static final Map<String, Texture> textureMap = new HashMap<String, Texture>();

	public static Texture load(String key){
		if(textureMap.containsKey(key))
			return textureMap.get(key);
		Texture loaded = new Texture(String.format("assets/%s.png", key));
		textureMap.put(key, loaded);
		return loaded;
	}
	
}
