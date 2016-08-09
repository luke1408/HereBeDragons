package at.fwuick.herebedragons.item;

public class Inventory {
	public static final int DEFAULT_ROWS = 5;
	public static final int DEFAULT_COLUMNS = 8;
	
	private Item[] slots;
	private int row_size;
	
	public Inventory(int rows, int column){
		slots = new Item[rows*column];
		row_size = column;
	}
	
	//DEFAULT CONSTRUCTOR
	public Inventory(){
		this(DEFAULT_ROWS, DEFAULT_COLUMNS);
	}
	
	public int rows(){
		return slots.length/row_size;
	}
	
	public int columns(){
		return row_size;
	}
	
	public Item get(int row, int column){
		return get(index(row, column));
	}

	public Item get(int index) {
		return slots[index];
	}
	
	public boolean empty(int index){
		return slots[index] == null;
	}
	
	public boolean full(int index){
		return !empty(index);
	}
	
	public int index(int row, int column){
		return row*row_size + column;
	}
	
	public int nextEmptyIndex(){
		for(int i=0; i!= slots.length; i++ ){
			if(empty(i))
				return i;
		}
		return -1;
	}
	
	public boolean full(){
		return nextEmptyIndex() == -1;
	}
	
	public void delete(int index){
		slots[index] = null;
	}
	
	public Item takeout(int index){
		Item item = get(index);
		delete(index);
		return item;
	}
	
	public void set(Item item, int index){
		if(empty(index))
			replace(item, index);
	}
	
	public void add(Item item){
		int index = nextEmptyIndex();
		if(index >= 0)
			replace(item, index);
	}
	
	public void replace(Item item, int index){
		slots[index] = item;
		item.setIndex(index);
	}
}
