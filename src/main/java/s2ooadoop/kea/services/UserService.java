package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.User;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.repositories.UserRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class UserService {
	@Autowired
	private UserRepositoryInterface URI;

	public User GetUser(int ID) throws SQLException {
		ResultSet rs = URI.GetUser(ID);
		User user = null;
		if (rs.next()) {
			user = new User(rs.getInt("ID"), rs.getString("Username"), "", rs.getInt("userType"));
		}
		return user;
	}

	public ArrayList<User> GetUsers() throws SQLException {
		ResultSet rs = URI.GetUsers();
		ArrayList<User> users = new ArrayList<User>();
		while (rs.next()) {
			users.add(new User(rs.getInt("ID"), rs.getString("Username"), "", rs.getInt("userType")));
		}
		return users;
	}

	public User ValidateUser(String name, String password) throws SQLException {
		ResultSet rs = URI.ValidateUser(name, password);
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

	public int CreateUser(User User) throws SQLException {
		System.out.println(User.getUsername());
		System.out.println(User.getPassword());
		System.out.println(User.getUserType().GetID());
		return URI.CreateUser(User.getUsername(), User.getPassword(), User.getUserType().GetID());
	}

	public void EditUser(User User) throws SQLException {
		URI.EditUser(User.getID(), User.getUsername(), User.getPassword(), User.getUserType().GetID());
	}

	public void DeleteUser(int ID) throws SQLException {
		URI.DeleteUser(ID);
	}

}