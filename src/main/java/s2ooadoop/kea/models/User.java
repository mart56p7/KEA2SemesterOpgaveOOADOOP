package s2ooadoop.kea.models;

public class User {

	private String Username;
	private String Password;
	private UserType userType;
	private int ID;

	public User(){}

	public User(int ID, String Username, String Password, UserType userType){
		this.ID = ID;
		this.Username = Username;
		this.Password = Password;
		this.userType = userType;
	}

	public User(String Username, String Password, int userType){
		this(0, Username, Password, UserType.GetUserType(userType));
	}

	public User(int ID, String Username, String Password, int userType){
		this(ID, Username, Password, UserType.GetUserType(userType));
	}

	public String getUsername() {
		return this.Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

}