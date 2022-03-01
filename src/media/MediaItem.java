package media;

public class MediaItem {
	private String name;
	private String genre;
	private int year;
	private int length;
	private String type;
	private String location;
	private int quantity;
	
	public MediaItem(String name, String genre, int year, int length, String type, String location) {
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.length = length;
		this.type = type;
		this.location = location;
		quantity = 1;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Negative numbers decrease quantity positive increase
	 * @param x to change the quantity by
	 */
	public void changeQ(int x) {
		quantity += x;
	}
}