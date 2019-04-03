package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public interface UserRepositoryInterface {

	ResultSet GetUser(int ID) throws SQLException;
	ResultSet GetUsers() throws SQLException;
	ResultSet ValidateUser(String Username, String Password) throws SQLException;
	int CreateUser(String Username, String Password, int UserType) throws SQLException;
	void EditUser(int ID, String Username, String Password, int UserType) throws SQLException;
	void DeleteUser(int ID) throws SQLException;

}