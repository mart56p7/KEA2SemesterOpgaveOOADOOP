package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public interface UserRepositoryInterface {

	ResultSet getUser(int ID) throws SQLException;
	ResultSet getUsers() throws SQLException;
	ResultSet validateUser(String Username, String Password) throws SQLException;
	int createUser(String Username, String Password, int UserType) throws SQLException;
	void editUser(int ID, String Username, String Password, int UserType) throws SQLException;
	void deleteUser(int ID) throws SQLException;

}