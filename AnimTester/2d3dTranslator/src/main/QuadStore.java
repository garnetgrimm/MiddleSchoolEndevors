package main;

public class QuadStore implements Comparable<QuadStore> {
	
	public final String value;
	public final Integer Order;
	
	public int sortType = 0;
	
	public QuadStore(String value, int Order) {
		this.value = value;
		this.Order = Order;
	}
	
	public String getVal() {
		return value;
	}
	public int getOrder() {
		return Order;
	}

	@Override
	public int compareTo(QuadStore o) {
		int p = this.Order.compareTo(o.Order);
		return p;
	}
}
