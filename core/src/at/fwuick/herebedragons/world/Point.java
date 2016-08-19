package at.fwuick.herebedragons.world;

import com.badlogic.gdx.math.Vector2;

public class Point extends Vector2  {

	public Point(float x, float y) {
		super(x, y);
	}

	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Point(Vector2 p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = result * prime + new Float(x).hashCode();
		result = result * prime + new Float(this.y).hashCode();
		return result;
	}
	
	public Distance getDistanceTo(Point p){
		return new Distance(p.x-x, p.y-y);
	}
	
	public Point moveDistance(Distance d){
		Point p = new Point(this);
		p.x += d.x;
		p.y += d.y;
		return p;
	}
	
	public class Distance extends Vector2{

		public Distance(float i, float j) {
			super(i,j);
		}

		public Distance() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Distance(Vector2 v) {
			super(v);
			// TODO Auto-generated constructor stub
		}
		
		
		
	}
	
	public Point goNorth(int i){
		this.y+=i;
		return this;
	}
	
	public Point goEast(int i){
		this.x+=i;
		return this;
	}
	
	public Point goSouth(int i){
		this.y-=i;
		return this;
	}
	
	public Point goWest(int i){
		this.x-=i;
		return this;
	}
	
	public int intX(){
		return Math.round(x);
	}
	
	public int intY(){
		return Math.round(y);
	}

	@Override
	public Point clone(){
		// TODO Auto-generated method stub
		return new Point(this);
	}

	
	
	
	
}
