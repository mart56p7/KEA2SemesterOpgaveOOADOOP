package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.User;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.repositories.UserRepositoryInterface;

@Service
public class UserService {

	private UserRepositoryInterface URI;

	/**
	 * 
	 * @param ID
	 */
	public User GetUser(int ID) {
		// TODO - implement UserService.GetUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param password
	 */
	public User ValidateUser(String name, String password) {
		User user = null;
		if(name.equalsIgnoreCase("orm") && password.equalsIgnoreCase("password"))
		{
			user = new User();
			user.setID(0);
			user.setUsername(name);
			user.setUserType(UserType.DOCTOR);
		}

		return user;
	}

	/**
	 * 
	 * @param User
	 */
	public int CreateUser(User User) {
		// TODO - implement UserService.CreateUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param User
	 */
	public void EditUser(User User) {
		// TODO - implement UserService.EditUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void DeleteUser(int ID) {
		// TODO - implement UserService.DeleteUser
		throw new UnsupportedOperationException();
	}

}