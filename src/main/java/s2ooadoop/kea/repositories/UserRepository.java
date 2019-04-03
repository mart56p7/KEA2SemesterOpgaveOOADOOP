package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository implements UserRepositoryInterface {

	@Autowired
	private Database DB;

	@Override
	public ResultSet GetUser(int ID) throws SQLException {
		String sql = "SELECT * FROM users WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet GetUsers() throws SQLException{
		String sql = "SELECT * FROM users ORDER BY id";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet ValidateUser(String Username, String Password) throws SQLException {
		String sql = "SELECT * FROM users WHERE username = ? and password = md5(?)";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setString(1, Username);
		pstmt.setString(2, Password);
		return DB.QuerySql(pstmt);
	}

	@Override
	public int CreateUser(String Username, String Password, int UserType) throws SQLException {
		String sql = "INSERT INTO users (`username`, `password`, `usertype`) VALUES (?, md5(?), ?)";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setString(1, Username);
		pstmt.setString(2, Password);
		pstmt.setInt(3, UserType);
		return DB.ExecuteSql(pstmt);
	}

	@Override
	public void EditUser(int ID, String Username, String Password, int UserType) throws SQLException {
		String sql = "UPDATE users SET `username` = ?, `password` = md5(?), `usertype` = ? WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setString(1, Username);
		pstmt.setString(2, Password);
		pstmt.setInt(3, UserType);
		pstmt.setInt(4, ID);
		DB.ExecuteSql(pstmt);
	}

	@Override
	public void DeleteUser(int ID) throws SQLException {
		String sql = "DELETE FROM  users WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		DB.ExecuteSql(pstmt);
	}
}