package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public interface UserRepositoryInterface {

	/**
	 * 
	 * @param ID
	 */
	ResultSet GetUser(int ID);

	/**
	 * 
	 * @param Name
	 * @param Password
	 */
	boolean ValidateUser(String Name, String Password);

	/**
	 * 
	 * @param Username
	 * @param Password
	 * @param UserType
	 */
	int CreateUser(String Username, String Password, int UserType);

	/**
	 * 
	 * @param Username
	 * @param Password
	 * @param UserType
	 */
	void EditUser(String Username, String Password, int UserType);

	/**
	 * 
	 * @param ID
	 */
	void DeleteUser(int ID);

}