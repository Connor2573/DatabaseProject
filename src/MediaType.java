
public enum MediaType {
	TRACK("Track"), ALBUM("Album"), MOVIE("Movie"), BOOK("Book");
	
	private String str;
	
	MediaType(String name) 
	{
		str = name;
	}
	
	@Override
	public String toString() {
		return str;
	}
}

