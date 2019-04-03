package s2ooadoop.kea.repositories;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
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
	 */
	public Connection CreateConnectionRWM() throws SQLException {
		if(mode == 777) {
			try {
				if(Conn.isValid(100)){
					return Conn;
				}
				else{
					return NewRWMConnection();
				}
			} catch (SQLException e) {
				return NewRWMConnection();
			}
		}
		else
		{
			CloseConnection();
			return NewRWMConnection();
		}
	}

	/**
	 * Create a new RWM database conenction
	 */
	private Connection NewRWMConnection() throws SQLException {
		String username = "s2oo";
		String password = "Password1234";
		String server   = "localhost";
		String dbName   = "dat18a";
		String port     = "3306";
		this.Conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + dbName, username, password);
		mode = 777;
		return Conn;
	}

	/**
	 * If there already is an active database connection it will be returned, else we will create one.
	 * This database connection can be used for READ
	 */
	public Connection CreateConnectionR() throws SQLException {
		if(mode == 444) {
			try {
				if(Conn.isValid(100)){
					return Conn;
				}
				else{
					return NewRConnection();
				}
			} catch (SQLException e) {
				return NewRConnection();
			}
		}
		else
		{
			CloseConnection();
			return NewRConnection();
		}
	}

	private Connection NewRConnection() throws SQLException {
		String username = "s2oo";
		String password = "Password1234";
		String server   = "Localhost";
		String dbName   = "dat18a";
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
		return stmt.executeQuery();
	}

}