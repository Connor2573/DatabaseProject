package DatabaseBridge;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bridge {
	
	private static String databaseFileName = "dpFinalCopy.db";
	
    public static Connection initializeDB() {
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            if (conn == null) {
            } else {
            	System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("There was a problem connecting to the database.");
        }
        return conn;
    }

	private static String mediaSql = "Insert Into MEDIA_ITEM (MediaID, Name, Year, Type, Price, Location, Status, Certificate) values(?, ?, ?, ?, ?, ?, ?, ?);";
	
    public static boolean addNewMedia(Connection conn, String[] args) {
    	boolean success = false;
    	try {
    		PreparedStatement stmt = conn.prepareStatement(mediaSql);
    		stmt.setInt(1, Integer.parseInt(args[0]));
    		stmt.setString(2, args[1]);
    		stmt.setInt(3, Integer.parseInt(args[2]));
    		stmt.setString(4, args[3]);
    		stmt.setDouble(5, Double.parseDouble(args[4]));
    		stmt.setString(6, args[5]);
    		stmt.setString(7, args[6]);
    		stmt.setString(8, args[7]);
    		stmt.executeUpdate();
    		success = true;
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return success;
    }
    
    private static String albumSql = "Insert Into ALBUM (AlbumID, MediaID, Artist, Genre, ContentRating) values(?, ?, ?, ?, ?);";
	
    public static void addAlbum(Connection conn, String[] args) {
    	try {
    		PreparedStatement stmt = conn.prepareStatement(albumSql);
    		stmt.setInt(1, Integer.parseInt(args[0]));
    		stmt.setInt(2, Integer.parseInt(args[1]));
    		stmt.setString(3, args[2]);
    		stmt.setString(4, args[3]);
    		stmt.setString(5, args[4]);
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    private static String bookSql = "Insert Into BOOK (MediaID, AuthorName, PublisherName, Genre, Chapters) values(?, ?, ?, ?, ?);";
	
    public static void addBook(Connection conn, String[] args) {
    	try {
    		PreparedStatement stmt = conn.prepareStatement(bookSql);
    		stmt.setInt(1, Integer.parseInt(args[0]));
    		stmt.setString(2, args[1]);
    		stmt.setString(3, args[2]);
    		stmt.setString(4, args[3]);
    		stmt.setInt(5, Integer.parseInt(args[4]));
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
	
    private static String movieSql = "Insert Into MOVIE (MediaID, LeadActor, DirectorName, Genre, ContentRating, Length) values(?, ?, ?, ?, ?, ?);";
	
    public static void addMovie(Connection conn, String[] args) {
    	try {
    		PreparedStatement stmt = conn.prepareStatement(movieSql);
    		stmt.setInt(1, Integer.parseInt(args[0]));
    		stmt.setString(2, args[1]);
    		stmt.setString(3, args[2]);
    		stmt.setString(4, args[3]);
    		stmt.setString(5, args[4]);
    		stmt.setInt(6, Integer.parseInt(args[5]));
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
	private static String trackSql = "Insert Into TRACK (TrackID, AlbumID, TrackName, Length) values(?, ?, ?, ?);";
	
    public static void addTrack(Connection conn, String[] args) {
    	try {
    		PreparedStatement stmt = conn.prepareStatement(trackSql);
    		stmt.setInt(1, Integer.parseInt(args[0]));
    		stmt.setInt(2, Integer.parseInt(args[1]));
    		stmt.setString(3, args[2]);
    		stmt.setInt(4, Integer.parseInt(args[3]));
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    private static String getMediaItemsSQL = "Select * From MEDIA_ITEM;";
    
    public static ArrayList<String[]> GetAllMediaItems(Connection conn) {
    	ArrayList<String[]> rows = new ArrayList<String[]>();
    	try {
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(getMediaItemsSQL);
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	while (rs.next()) {
        		String[] row = new String[columnCount];
        		row[0] = ""+rs.getInt(1);
        		row[1] = rs.getString(2);
        		row[2] = ""+rs.getInt(3);
        		row[3] = rs.getString(4);
        		row[4] = ""+rs.getDouble(5);
        		row[5] = rs.getString(6);
        		row[6] = rs.getString(7);
        		row[7] = rs.getString(8);
        		rows.add(row);
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return rows;
    }
    
    private static String removeSQL = "DELETE FROM MEDIA_ITEM WHERE MediaID = ?";
    
    public static void RemoveMediaWithID(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeSQL);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
