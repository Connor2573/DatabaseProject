import java.util.HashSet;
import java.util.Set;

import media.*;

public class MediaCollection {
	private Set<Movie> movies;
	private Set<Track> tracks;
	private Set<Album> albums;
	private Set<Book> books;
	
	
	public MediaCollection() {
		movies = new HashSet<Movie>();
		tracks = new HashSet<Track>();
		albums = new HashSet<Album>();
		books = new HashSet<Book>();
	}
	
	public boolean Add(Book b) {
		boolean added = false;
		if(!books.contains(b)) {
			books.add(b);
			added = true;
		} else {
			b.changeQ(1);
			added = false;
		}
		return added;
	}
	
	public boolean Add(Movie m) {
		boolean added = false;
		if(!movies.contains(m)) {
			movies.add(m);
			added = true;
		} else {
			m.changeQ(1);
			added = false;
		}
		return added;
	}
	
	public boolean Add(Track t) {
		boolean added = false;
		if(!tracks.contains(t)) {
			tracks.add(t);
			added = true;
		} else {
			t.changeQ(1);
			added = false;
		}
		return added;
	}
	
	public boolean Add(Album a) {
		boolean added = false;
		if(!albums.contains(a)) {
			albums.add(a);
			added = true;
		} else {
			a.changeQ(1);
			added = false;
		}
		return added;
	}
	
	public String[][] GetItems() {
		int totalItemCount = movies.size() + books.size() + albums.size() + tracks.size();
		String[][] items = new String[totalItemCount][8];
		int rowIndex = 0;
		for(Movie item: movies) {
			items[rowIndex][0] = item.getName();
			items[rowIndex][1] = item.getGenre();
			items[rowIndex][2] = ""+item.getYear();
			items[rowIndex][3] = ""+item.getLength();
			items[rowIndex][4] = item.getType();
			items[rowIndex][5] = item.getLocation();
			items[rowIndex][6] = item.getContentRating();
			items[rowIndex][7] = ""+item.getQuantity();
			rowIndex++;
		}
		for(Album item: albums) {
			items[rowIndex][0] = item.getName();
			items[rowIndex][1] = item.getGenre();
			items[rowIndex][2] = ""+item.getYear();
			items[rowIndex][3] = ""+item.getLength();
			items[rowIndex][4] = item.getType();
			items[rowIndex][5] = item.getLocation();
			items[rowIndex][6] = "NA";
			items[rowIndex][7] = ""+item.getQuantity();
			rowIndex++;
		}
		for(Track item: tracks) {
			items[rowIndex][0] = item.getName();
			items[rowIndex][1] = item.getGenre();
			items[rowIndex][2] = ""+item.getYear();
			items[rowIndex][3] = ""+item.getLength();
			items[rowIndex][4] = item.getType();
			items[rowIndex][5] = item.getLocation();
			items[rowIndex][6] = item.getContentRating();
			items[rowIndex][7] = ""+item.getQuantity();
			rowIndex++;
		}
		for(Book item: books) {
			items[rowIndex][0] = item.getName();
			items[rowIndex][1] = item.getGenre();
			items[rowIndex][2] = ""+item.getYear();
			items[rowIndex][3] = ""+item.getLength();
			items[rowIndex][4] = item.getType();
			items[rowIndex][5] = item.getLocation();
			items[rowIndex][6] = "NA";
			items[rowIndex][7] = ""+item.getQuantity();
			rowIndex++;
		}
		
		return items;
	}
	
	public String[][][] GetLabeledItems() {
		int totalItemCount = movies.size() + books.size() + albums.size() + tracks.size();
		String[][][] items = new String[4][totalItemCount][8];
		int rowIndex = 0;
		int dim = 0;
		for(Movie item: movies) {
			items[dim][rowIndex][0] = item.getName();
			items[dim][rowIndex][1] = item.getGenre();
			items[dim][rowIndex][2] = ""+item.getYear();
			items[dim][rowIndex][3] = ""+item.getLength();
			items[dim][rowIndex][4] = item.getType();
			items[dim][rowIndex][5] = item.getLocation();
			items[dim][rowIndex][6] = item.getContentRating();
			items[dim][rowIndex][7] = ""+item.getQuantity();
			rowIndex++;
		}
		dim = 2;
		for(Album item: albums) {
			items[dim][rowIndex][0] = item.getName();
			items[dim][rowIndex][1] = item.getGenre();
			items[dim][rowIndex][2] = ""+item.getYear();
			items[dim][rowIndex][3] = ""+item.getLength();
			items[dim][rowIndex][4] = item.getType();
			items[dim][rowIndex][5] = item.getLocation();
			items[dim][rowIndex][6] = "NA";
			items[dim][rowIndex][7] = ""+item.getQuantity();
			rowIndex++;
		}
		dim = 1;
		for(Track item: tracks) {
			items[dim][rowIndex][0] = item.getName();
			items[dim][rowIndex][1] = item.getGenre();
			items[dim][rowIndex][2] = ""+item.getYear();
			items[dim][rowIndex][3] = ""+item.getLength();
			items[dim][rowIndex][4] = item.getType();
			items[dim][rowIndex][5] = item.getLocation();
			items[dim][rowIndex][6] = item.getContentRating();
			items[dim][rowIndex][7] = ""+item.getQuantity();
			rowIndex++;
		}
		dim = 3;
		for(Book item: books) {
			items[dim][rowIndex][0] = item.getName();
			items[dim][rowIndex][1] = item.getGenre();
			items[dim][rowIndex][2] = ""+item.getYear();
			items[dim][rowIndex][3] = ""+item.getLength();
			items[dim][rowIndex][4] = item.getType();
			items[dim][rowIndex][5] = item.getLocation();
			items[dim][rowIndex][6] = "NA";
			items[dim][rowIndex][7] = ""+item.getQuantity();
			rowIndex++;
		}
		
		return items;
	}
}
