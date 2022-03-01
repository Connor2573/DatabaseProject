package media;

public class Track extends MediaItem{
	private String ContentRating;
	
	public Track(String name, String genre, int year, int length, String type, String location, String ContentRating) {
		super(name, genre, year, length, type, location);
		this.ContentRating = ContentRating;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the contentRating
	 */
	public String getContentRating() {
		return ContentRating;
	}

}
