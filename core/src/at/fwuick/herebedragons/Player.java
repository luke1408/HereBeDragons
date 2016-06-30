package at.fwuick.herebedragons;


public class Player {

	private Point point;

	//STUB
	public Player(Point p){
		this.point = p;
	}
	
	public Player(){
		this(new Point());
	}

	public Point getPoint() {
		return point;
	}
	
	public void goNorth(){
		point.y++;
	}
	
	public void goEast(){
		point.x++;
	}
	
	public void goSouth(){
		point.y--;
	}
	
	public void goWest(){
		point.x--;
	}
	
	
}
