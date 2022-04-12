
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
	
	public static MediaType getMediaType(String x) {
		MediaType ret = null;
		if(x.contains("MOVIE")) {
			ret = MOVIE;
		} else if(x.contains("ALBUM")) {
			ret = ALBUM;
		} else if(x.contains("BOOK")) {
			ret = BOOK;
		} else if(x.contains("TRACK")) {
			ret = TRACK;
		}
		return ret;
	}
}


