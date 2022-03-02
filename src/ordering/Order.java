package ordering;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import media.MediaItem;

public class Order {
	
	
	private int orderID;
	private int empID;
	private MediaItem item;
	private String name;
	private int quantity;
	private String estDelivery;
	
	int orderIDtally = 01;
	
	
	
	public Order(int orderID, int empID, MediaItem item, String name, int quantity, String estDelivery) {
		
		this.orderID = orderIDtally;
		this.empID = empID;
		this.item = item;
		this.name = name;
		this.quantity = quantity;
		this.estDelivery = estDelivery;
		
		orderIDtally++;
		
	}
	
	
	
}
