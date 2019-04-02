package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class Database {

	private int Connection;

	public void CreateConnectionRWM() {
		// TODO - implement Database.CreateConnectionRWM
		throw new UnsupportedOperationException();
	}

	public void CreateConnectionR() {
		// TODO - implement Database.CreateConnectionR
		throw new UnsupportedOperationException();
	}

	public void CloseConnection() {
		// TODO - implement Database.CloseConnection
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param stmt
	 */
	public int ExecuteSql(PreparedStatement stmt) {
		// TODO - implement Database.ExecuteSql
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param stmt
	 */
	public ResultSet QuerySql(PreparedStatement stmt) {
		// TODO - implement Database.QuerySql
		throw new UnsupportedOperationException();
	}

}