import java.util.HashSet;
import java.util.Set;

import ordering.Order;

import media.*;

public class OrderCollection {
	
	private Set<Order> orders;

	
	
	public OrderCollection() {
		orders = new HashSet<Order>();
	}
	
	public void Add(Order order) {
		orders.add(order);
	}
	
	public String[][] GetOrders() {
		String[][] returnOrders = new String[this.orders.size()][4];
		int rowIndex = 0;
		for (Order order: this.orders) {
			returnOrders[rowIndex][0] = order.getName();
			returnOrders[rowIndex][1] = order.getItem().getName();
			returnOrders[rowIndex][2] = String.valueOf(order.getQuantity());
			returnOrders[rowIndex][3] = order.getEstDelivery();
			rowIndex++;
		}
		return returnOrders;
	}
}
