package main;

public class Tuple implements Comparable<Tuple> {
	
	public final Double x;
	public final Double y;
	public final Double z;
	
	public int sortType = 0;
	
	public Tuple(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getSortType() {
		return sortType;
	}
	
	public void setSortType(int type) {
		sortType = type;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public String toAString() {
		return "v " + x + " " + y + " " + z;
	}

	@Override
	public int compareTo(Tuple o) {
		int p = this.x.compareTo(o.x);
		if(p == 0) p = this.y.compareTo(o.y);
		return p;
	}
}
