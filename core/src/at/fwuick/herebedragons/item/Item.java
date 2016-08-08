package at.fwuick.herebedragons.item;

public class Item {
	public static final int INFINITE_USES = -22;
	public static final int DEFAULT_USE = 1;
	
	private String name;
	private int uses;
	private int index;
	
	public Item(String name, int uses){
		this.name = name;
		this.uses = uses;
	}
	
	public Item(String name){
		this(name, DEFAULT_USE);
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
