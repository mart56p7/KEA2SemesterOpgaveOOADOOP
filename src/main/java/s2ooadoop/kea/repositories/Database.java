package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class Database {

	private Connection Conn = null;
	//444 = READMODE, 777 = RWM MODE
	private int mode = 0 ;

	/**
	 * If there already is an active database connection it will be returned, else we will create one.
	 * This database connection can be used for READ, WRITE and MODIFY
	 *
	 * Note: We choose to make the CreateConnectionRWM() and CreateConnectionR() the same
	 */
	public Connection CreateConnectionRWM() throws SQLException {
		try {
			if(Conn == null){
				return NewRWMConnection();
			}
			else if(Conn.isValid(100)){
				return Conn;
			}
			else{
				return NewRWMConnection();
			}
		} catch (SQLException e) {
			return NewRConnection();
		}
	}

	/**
	 * Create a new RWM database conenction
	 */
	private Connection NewRWMConnection() throws SQLException {
		String username = "patientsystemkea";
		String password = "Yj1d!ZqWp-Sd";
		String server   = "den1.mysql2.gear.host";
		String dbName   = "patientsystemkea";
		String port     = "3306";
		this.Conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + dbName, username, password);
		mode = 777;
		return Conn;
	}

	/**
	 * If there already is an active database connection it will be returned, else we will create one.
	 * This database connection can be used for READ
	 *
	 * Note: We choose to make the CreateConnectionRWM() and CreateConnectionR() the same
	 */
	public Connection CreateConnectionR() throws SQLException {
		try {
			if(Conn == null){
				return NewRWMConnection();
			}
			else if(Conn.isValid(100)){
				return Conn;
			}
			else{
				return NewRWMConnection();
			}
		} catch (SQLException e) {
			return NewRConnection();
		}
	}

	private Connection NewRConnection() throws SQLException {
		String username = "patientsystemkea";
		String password = "Yj1d!ZqWp-Sd";
		String server   = "den1.mysql2.gear.host";
		String dbName   = "patientsystemkea";
		String port     = "3306";
		this.Conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + dbName, username, password);
		mode = 444;
		return Conn;
	}



	public void CloseConnection() throws SQLException {
		if(Conn != null) {
			Conn.close();
		}
	}

	public int ExecuteSql(PreparedStatement stmt) throws SQLException {
		return stmt.executeUpdate();
	}

	public ResultSet QuerySql(PreparedStatement stmt) throws SQLException {
		System.out.println("SQL KALD");
		return stmt.executeQuery();
	}

}