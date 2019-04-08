package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.User;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.repositories.UserRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The User service provides users and userstypes for the system.
 * The user access handling is done in the Controller layer
 * UserTypes are hardcoded in s2ooadoop.kea.models.UserType
 * */
@Service
public class UserService {
	@Autowired
	private UserRepositoryInterface URI;

	public User getUser(int ID) throws SQLException {
		ResultSet rs = URI.getUser(ID);
		User user = null;
		if (rs.next()) {
			user = new User(rs.getInt("ID"), rs.getString("Username"), "", rs.getInt("userType"));
		}
		return user;
	}

	public ArrayList<User> getUsers() throws SQLException {
		ResultSet rs = URI.getUsers();
		ArrayList<User> users = new ArrayList<User>();
		while (rs.next()) {
			users.add(new User(rs.getInt("ID"), rs.getString("Username"), "", rs.getInt("userType")));
		}
		return users;
	}

	public User validateUser(String name, String password) throws SQLException {
		ResultSet rs = URI.validateUser(name, password);
		User user = null;
		if (rs.next()) {
			user = new User(rs.getInt("ID"), rs.getString("Username"), "", rs.getInt("userType"));
		}
//TIL TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(user == null)
		{
			if(name.equalsIgnoreCase("orm") && password.equalsIgnoreCase("password"))
			{
				user = new User();
				user.setID(0);
				user.setUsername(name);
				user.setUserType(UserType.DOCTOR);
			}
		}
//TIL TEST SLUUUUUUT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return user;
	}

	public int createUser(User User) throws SQLException {
		System.out.println(User.getUsername());
		System.out.println(User.getPassword());
		System.out.println(User.getUserType().getID());
		return URI.createUser(User.getUsername(), User.getPassword(), User.getUserType().getID());
	}

	public void editUser(User User) throws SQLException {
		URI.editUser(User.getID(), User.getUsername(), User.getPassword(), User.getUserType().getID());
	}

	public void deleteUser(int ID) throws SQLException {
		URI.deleteUser(ID);
	}

}