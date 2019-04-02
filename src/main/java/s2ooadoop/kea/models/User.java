package s2ooadoop.kea.models;

public class User {

	private String Username;
	private String Password;
	private UserType userType;
	private int ID;

	public String getUsername() {
		return this.Username;
	}

	/**
	 * 
	 * @param Username
	 */
	public void setUsername(String Username) {
		this.Username = Username;
	}

	public String getPassword() {
		return this.Password;
	}

	/**
	 * 
	 * @param Password
	 */
	public void setPassword(String Password) {
		this.Password = Password;
	}

	public UserType getUserType() {
		return this.userType;
	}

	/**
	 * 
	 * @param userType
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getID() {
		return this.ID;
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

}