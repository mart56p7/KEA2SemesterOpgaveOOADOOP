package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class UserRepository implements UserRepositoryInterface {

	private Database Database;

	@Override
	public ResultSet GetUser(int ID) {
		return null;
	}

	@Override
	public boolean ValidateUser(String Name, String Password) {
		return false;
	}

	@Override
	public int CreateUser(String Username, String Password, int UserType) {
		return 0;
	}

	@Override
	public void EditUser(String Username, String Password, int UserType) {

	}

	@Override
	public void DeleteUser(int ID) {

	}
}