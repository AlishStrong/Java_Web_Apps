//DAO class that contains all methods needed for the project

package ExerciseSet1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import ExerciseSet1.ConnectionParameters;

public class StudentDAO {
	
	private final String username;			 
	private final String password; 
	private final String databaseURL;
	
	public StudentDAO() throws Exception {
		username = ConnectionParameters.username;			 
		password = ConnectionParameters.password;
		databaseURL = ConnectionParameters.databaseURL;
		
		// In *Tomcat 8* the JDBC driver must be explicitly loaded as below
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} 
    }
	
	private Connection openConnection() throws SQLException	{
		Connection dbConnection = DriverManager.getConnection(databaseURL, username, password);
		return dbConnection;
	}
	private void closeConnection(Connection dbConnection) throws SQLException {
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
	
	public ArrayList<Student> getAllStudents() throws Exception {
		ArrayList<Student> allStudents = new ArrayList<Student>();
		Connection dbConnection = null;
		try {
			dbConnection = openConnection();
			
			String sqlText = "SELECT * FROM Student ORDER BY id DESC";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstname = resultSet.getString(2);
				String lastname = resultSet.getString(3);
				String streetaddress = resultSet.getString(4);
				int postcode = resultSet.getInt(5);
				String postoffice = resultSet.getString(6);
				
				allStudents.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));
			}
			return allStudents;
		} catch (SQLException sqle) {
			throw sqle;
		} finally {
			closeConnection(dbConnection);
		}
	}

	public Student getStudentById (int StudentID) throws Exception {
		Student neededStudent = null;
		Connection dbConnection = null;
		try {
			dbConnection = openConnection();
			
			String sqlText = "SELECT * FROM Student WHERE id = ?";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			preparedStatement.setInt(1, StudentID);
			ResultSet resultSet = preparedStatement.executeQuery();
	
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstname = resultSet.getString(2);
				String lastname = resultSet.getString(3);
				String streetaddress = resultSet.getString(4);
				int postcode = resultSet.getInt(5);
				String postoffice = resultSet.getString(6);
				neededStudent = new Student(id, firstname, lastname, streetaddress, postcode, postoffice);
			}
			return neededStudent;
		} catch (SQLException sqle)	{
			throw sqle;
		} finally {
			closeConnection(dbConnection);
		}
	}

	public int insertStudent (Student student) throws Exception {
		Connection dbConnection = null;
		try {
			dbConnection = openConnection();
			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, "
					+ "postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedSt = dbConnection.prepareStatement(sqlText);
			preparedSt.setInt(1, student.getId());
			preparedSt.setString(2, student.getFirstname());
			preparedSt.setString(3, student.getLastname());
			preparedSt.setString(4, student.getStreetaddress());
			preparedSt.setInt(5, student.getPostcode());
			preparedSt.setString(6, student.getPostoffice());
			preparedSt.executeUpdate();
			return 0;
		} catch (SQLException sqle)	{
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				return 1;
			} else {
				return -1;
			}
		} finally {
			closeConnection(dbConnection);
		}
	}

	public String getAllStudentsJSON() throws Exception {
		Connection dbConnection = null;
		ArrayList<Student> JSON = new ArrayList<Student>();
		try {
			dbConnection = openConnection();
			String sqlText = "SELECT * FROM Student";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstname = resultSet.getString(2);
				String lastname = resultSet.getString(3);
				String streetaddress = resultSet.getString(4);
				int postcode = resultSet.getInt(5);
				String postoffice = resultSet.getString(6);
				JSON.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));
			}
			
			//Column names
			ResultSetMetaData rsMeta = resultSet.getMetaData();
			int colSize = rsMeta.getColumnCount();
			ArrayList<String> colName = new ArrayList<String>();
			for (int i = 1; i <= colSize; i++) {
				colName.add(rsMeta.getColumnName(i).toUpperCase());
			}
			
			//JSON String
			String stringJSON = "[";
			for (int i = 0; i < JSON.size(); i++) {
				stringJSON += "{\""+colName.get(0)+"\":\""+JSON.get(i).getId()+"\",\""
						+colName.get(1)+"\":\""+JSON.get(i).getFirstname()+"\",\""
						+colName.get(2)+"\":\""+JSON.get(i).getLastname()+"\",\""
						+colName.get(3)+"\":\""+JSON.get(i).getStreetaddress()+"\",\""
						+colName.get(4)+"\":\""+JSON.get(i).getPostcode()+"\",\""
						+colName.get(5)+"\":\""+JSON.get(i).getPostoffice()+"\"}, ";
			}
			stringJSON = stringJSON.substring(0, stringJSON.length()-2)+"]";
			return stringJSON;
		} catch (SQLException sqle) {
			throw sqle;
		} finally {
			closeConnection(dbConnection);
		}
	}
}
