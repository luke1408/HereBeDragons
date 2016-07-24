package at.fwuick.herebedragons;

public class Point extends java.awt.Point {

	public Point(int x, int y) {
		super(x, y);
	}

	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Point(java.awt.Point p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = result * prime + new Integer(this.x).hashCode();
		result = result * prime + new Integer(this.y).hashCode();
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
	
	public class Distance extends Point{

		public Distance(int i, int j) {
			super(i,j);
		}
		
	}
	
	
}
