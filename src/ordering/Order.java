package ordering;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import media.MediaItem;

public class Order {
	private MediaItem item;
	private String name;
	private int quantity;
	private String estDelivery;
	public int ID;
	
	private static int count = 0;
	
	
	
	public Order(MediaItem item, String name, int quantity, String estDelivery) {
		this.item = item;
		this.name = name;
		this.quantity = quantity;
		this.estDelivery = estDelivery;
		
		count++;
		ID = count;
	}
	
	/**
	 * @return the item
	 */
	public MediaItem getItem() {
		return item;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @return the estDelivery
	 */
	public String getEstDelivery() {
		return estDelivery;
	}
	
	
	
	
	
}
