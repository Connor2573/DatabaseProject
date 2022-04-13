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
		String[][] returnOrders = new String[this.orders.size()][5];
		int rowIndex = 0;
		for (Order order: this.orders) {
			returnOrders[rowIndex][0] = String.valueOf(order.getID());
			returnOrders[rowIndex][1] = order.getName();
			returnOrders[rowIndex][2] = order.getItem().getName();
			returnOrders[rowIndex][3] = String.valueOf(order.getQuantity());
			returnOrders[rowIndex][4] = order.getEstDelivery();
			rowIndex++;
		}
		return returnOrders;
	}
	
	public void Activate(int id) {
		Order target = null;
		for (Order order: this.orders) {
			if (order.getID() == id) {
				target = order;
			}
		}
		MediaItem targetItem = target.getItem();
		String mt = targetItem.getType();
		for (int i = 0; i < target.getQuantity(); i++) {
			switch(mt) {
				case "Book":
					//Bridge.Add(new Book(targetItem.getName(), targetItem.getGenre(), targetItem.getYear(), targetItem.getLength(), targetItem.getType(), targetItem.getLocation()));
					break;
				case "Movie":
					//Core.media.Add(new Movie(targetItem.getName(), targetItem.getGenre(), targetItem.getYear(), targetItem.getLength(), targetItem.getType(), targetItem.getLocation(), ((Movie)targetItem).getContentRating()));
					break;
				case "Track":
					//Core.media.Add(new Track(targetItem.getName(), targetItem.getGenre(), targetItem.getYear(), targetItem.getLength(), targetItem.getType(), targetItem.getLocation(), ((Track)targetItem).getContentRating()));
					break;
				case "Album":
					//Core.media.Add(new Album(targetItem.getName(), targetItem.getGenre(), targetItem.getYear(), targetItem.getLength(), targetItem.getType(), targetItem.getLocation()));
					break; 
		   }
		}
		orders.remove(target);
	}
}
