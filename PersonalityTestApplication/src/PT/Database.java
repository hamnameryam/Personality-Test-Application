package PT;


import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class Database {
 private final String DB_URL = "jdbc:ucanaccess://C:/Users/HAMNA/Desktop/SPRING 2025/JAVA/ProjectDB.accdb";

 public Connection getConnection() throws SQLException {
     return DriverManager.getConnection(DB_URL);
 }

 public boolean emailExists(String email) {
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement("SELECT * FROM [User] WHERE Email = ?")) {
         ps.setString(1, email);
         ResultSet rs = ps.executeQuery();
         return rs.next();
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }
 public boolean validateLogin(String email, String password) {
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement("SELECT * FROM [User] WHERE Email = ? AND Password = ?")) {
         ps.setString(1, email);
         ps.setString(2, password);
         ResultSet rs = ps.executeQuery();
         return rs.next();
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }
 public boolean insertUser(String email, String password) {
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement("INSERT INTO [User] (Email, Password) VALUES (?, ?)")) {
         ps.setString(1, email);
         ps.setString(2, password);
         return ps.executeUpdate() > 0;
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }
 public int getUserIdByEmail(String email) {
	    String query = "SELECT UserID FROM [User] WHERE Email = ?";
	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("UserID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1; // Not found
	 }
 public boolean insertQuestion(String question, String a, String b, String c, String d) {
    String query = "INSERT INTO Questions (QuestionText, OptionA, OptionB, OptionC, OptionD) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, question);
        ps.setString(2, a);
        ps.setString(3, b);
        ps.setString(4, c);
        ps.setString(5, d);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
 }
 public boolean clearPersonalityCounts(int userId) {
	    String sql = "UPDATE Personality SET Count = 0 WHERE UserID = ?";
	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, userId);
	        int rowsUpdated = pstmt.executeUpdate();
	        return rowsUpdated > 0;  
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

 public List<Question> getAllQuestions() {
    List<Question> list = new ArrayList<>();
    String query = "SELECT * FROM Questions";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            list.add(new Question(
                rs.getString("QuestionText"),
                rs.getString("OptionA"),
                rs.getString("OptionB"),
                rs.getString("OptionC"),
                rs.getString("OptionD")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
 }



 

 public boolean saveDominantResult(int userId, String personality, double percentage) {
     String sql = "INSERT INTO Results (UserID, Personality, Percentage) VALUES (?, ?, ?)";
     try (Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setInt(1, userId);
         ps.setString(2, personality);
         ps.setDouble(3, percentage);
         return ps.executeUpdate() > 0;
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }

 public List<PersonalityCount> getPersonalityCountsSimple(int userId) {
    String query = "SELECT Personality, Count FROM Personality WHERE UserID = ?";
    List<PersonalityCount> counts = new ArrayList<>();
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String personality = rs.getString("Personality");
            int count = rs.getInt("Count");
            counts.add(new PersonalityCount(personality, count));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return counts;
 }



 public boolean incrementPersonalityCount(int userId, String personality) {
    String checkQuery = "SELECT Count FROM Personality WHERE UserID = ? AND Personality = ?";
    String updateQuery = "UPDATE Personality SET Count = Count + 1 WHERE UserID = ? AND Personality = ?";
    String insertQuery = "INSERT INTO Personality (UserID, Personality, Count) VALUES (?, ?, 1)";

    try (Connection conn = getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

        checkStmt.setInt(1, userId);
        checkStmt.setString(2, personality);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, userId);
                updateStmt.setString(2, personality);
                return updateStmt.executeUpdate() > 0;
            }
        } else {
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, userId);
                insertStmt.setString(2, personality);
                return insertStmt.executeUpdate() > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
 }


}

