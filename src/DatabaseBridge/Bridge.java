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
    
    private static String getMembersSQL = "Select * From MEMBER;";
    
    public static ArrayList<String[]> GetMembersItems(Connection conn) {
    	ArrayList<String[]> rows = new ArrayList<String[]>();
    	try {
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(getMembersSQL);
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	while (rs.next()) {
        		String[] row = new String[columnCount];
        		row[0] = ""+rs.getInt(1);
        		row[1] = ""+rs.getInt(2);
        		row[2] = rs.getString(3);
        		row[3] = rs.getString(4);
        		row[4] = rs.getString(5);
        		rows.add(row);
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return rows;
    }
    
    private static String getEmployeeSQL = "Select * From EMPLOYEE;";
    
    public static ArrayList<String[]> GetEmployeeItems(Connection conn) {
    	ArrayList<String[]> rows = new ArrayList<String[]>();
    	try {
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(getEmployeeSQL);
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	while (rs.next()) {
        		String[] row = new String[columnCount];
        		row[0] = ""+rs.getInt(1);
        		row[1] = ""+rs.getInt(2);
        		row[2] = rs.getString(3);
        		rows.add(row);
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return rows;
    }
    
    private static String selectMovieSql = "Select * From Movie Where MediaID = ?;";
    
    public static String[] getMovieWithID(Connection conn, int id) {
    	String[] str = null;
    	try {
    		PreparedStatement stmt = conn.prepareStatement(selectMovieSql);
    		stmt.setInt(1, id);
    		ResultSet rs = stmt.executeQuery();
    		ResultSetMetaData rsmd = rs.getMetaData();
    		str = new String[rsmd.getColumnCount()];
    		str[0] = ""+rs.getInt(1);
    		str[1] = rs.getString(2);
    		str[2] = rs.getString(3);
    		str[3] = rs.getString(4);
    		str[4] = rs.getString(5);
    		str[5] = ""+rs.getInt(6);
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return str;
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
    
    private static String removeSQL = "DELETE FROM MEDIA_ITEM WHERE MediaID = ?";
    private static String removeMovieSQL = "DELETE FROM MOVIE WHERE MediaID = ?";
    private static String removeBookSQL = "DELETE FROM BOOK WHERE MediaID = ?";
    private static String removeTrackSQL = "DELETE FROM TRACK WHERE TrackID = ?";
    private static String removeAlbumSQL = "DELETE FROM ALBUM WHERE MediaID = ?";
    
    public static void RemoveMediaWithID(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeSQL);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	RemoveMovieWithID(conn, id);
    	RemoveBookWithID(conn, id);
    	RemoveAlbumWithID(conn, id);
    }
    
    public static void RemoveMovieWithID(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeMovieSQL);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void RemoveBookWithID(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeBookSQL);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void RemoveTrackWithID(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeTrackSQL);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void RemoveAlbumWithID(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeAlbumSQL);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String getOrdersSQL = "Select * From ORDER_LIST;";
    
    public static ArrayList<String[]> GetAllOrders(Connection conn) {
    	ArrayList<String[]> rows = new ArrayList<String[]>();
    	try {
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(getOrdersSQL);
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	while (rs.next()) {
        		String[] row = new String[columnCount];
        		row[0] = ""+rs.getInt(1);
        		row[1] = ""+rs.getInt(2);
        		row[2] = ""+rs.getInt(3);
        		row[3] = ""+rs.getInt(4);
        		row[4] = ""+rs.getDouble(5);
        		row[5] = ""+rs.getInt(6);
        		row[6] = rs.getString(7);
        		row[7] = rs.getString(8);
        		rows.add(row);
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return rows;
    }
    
    private static String orderSql = "Insert Into ORDER_LIST (EntryID, OrderID, EmployeeID, MediaID, Price, NumCopies, OrderDate, EstArrivalDate) values(?, ?, ?, ?, ?, ?, ?, ?);";
	
    public static boolean addNewOrder(Connection conn, String[] args) {
    	boolean success = false;
    	try {
    		PreparedStatement stmt = conn.prepareStatement(orderSql);
    		stmt.setInt(1, Integer.parseInt(args[0]));
    		stmt.setInt(2, Integer.parseInt(args[1]));
    		stmt.setInt(3, Integer.parseInt(args[2]));
    		stmt.setInt(4, Integer.parseInt(args[3]));
    		stmt.setDouble(5, Double.parseDouble(args[4]));
    		stmt.setInt(6, Integer.parseInt(args[5]));
    		stmt.setString(7, args[6]);
    		stmt.setString(8, args[7]);
    		stmt.executeUpdate();
    		success = true;
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return success;
    }
    
    private static String activateItemSQL = "UPDATE MEDIA_ITEM SET Status='available' WHERE MediaID = ?";
    private static String removeOrderSQL = "DELETE FROM ORDER_LIST WHERE entryID = ?";
    
    public static void activateOrder(int entryID, int mediaID, Connection conn) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(activateItemSQL);
    		ps.setInt(1, mediaID);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeOrderSQL);
    		ps.setInt(1, entryID);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String addEmployeeSql = "Insert into EMPLOYEE (EmployeeID, PersonID, Email) values(?, ?, ?);";
    
    public static void addEmployee(Connection conn, String[] vals) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(addEmployeeSql);
    		ps.setInt(1, Integer.parseInt(vals[0]));
    		ps.setInt(2, Integer.parseInt(vals[1]));
    		ps.setString(3, vals[2]);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String addPersonSql = "Insert into PERSON (PersonID, Name) values(?, ?);";
    
    public static void addPerson(Connection conn, String[] vals) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(addPersonSql);
    		ps.setInt(1, Integer.parseInt(vals[0]));
    		ps.setString(2, vals[1]);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String removeEmployeeSql = "DELETE FROM EMPLOYEE where EmployeeID = ?;";
    
    public static void removeEmployee(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeEmployeeSql);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String removePersonSql = "DELETE FROM PERSON where PersonID = ?;";
    
    public static void removePerson(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removePersonSql);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String getPersonSql = "Select * From PERSON Where PersonID = ?;";
    
    public static String[] getPerson(Connection conn, int id) {
    	String[] data = new String[2];
    	try {
    		PreparedStatement ps = conn.prepareStatement(getPersonSql);
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
        	data[0] = ""+rs.getInt(1);
        	data[1] = rs.getString(2);
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return data;
    }
    
    private static String addMemberSql = "Insert into MEMBER (MemberID, PersonID, Email, Address, PhoneNumber) values(?, ?, ?, ?, ?);";
    
    public static void addMember(Connection conn, String[] vals) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(addMemberSql);
    		ps.setInt(1, Integer.parseInt(vals[0]));
    		ps.setInt(2, Integer.parseInt(vals[1]));
    		ps.setString(3, vals[2]);
    		ps.setString(4, vals[3]);
    		ps.setString(5, vals[4]);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String removeMemberSql = "DELETE FROM MEMBER where MemberID = ?;";
    
    public static void removeMember(Connection conn, int id) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(removeMemberSql);
    		ps.setInt(1, id);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String getContentCreatorSql = "Select * From CONTENT_CREATOR JOIN PERSON ON CONTENT_CREATOR.PersonID = PERSON.PersonID Where PERSON.Name = ?;";
    
    public static boolean getContentCreator(Connection conn, String name) {
    	boolean empty = false;
    	try {
    		PreparedStatement ps = conn.prepareStatement(getContentCreatorSql);
    		ps.setString(1, name);
    		ResultSet rs = ps.executeQuery();
    		empty = rs.next();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	return empty;
    }
    
    private static String addContentCreatorSql = "Insert into CONTENT_CREATOR (CreatorID, PersonID) values(?, ?);";
    
    public static void addContentCreator(Connection conn, int createID, int personID) {
    	try {
    		PreparedStatement ps = conn.prepareStatement(addContentCreatorSql);
    		ps.setInt(1, createID);
    		ps.setInt(2, personID);
    		ps.executeUpdate();
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void PrintArgs(String[] args) {
    	for (String x: args) {
    		System.out.println(x);
    	}
    }
}
