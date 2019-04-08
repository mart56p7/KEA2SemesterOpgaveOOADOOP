package s2ooadoop.kea.models;

public class User {

	private String username;
	private String password;
	private UserType userType;
	private int ID;

	public User(){}

	public User(int ID, String Username, String Password, UserType userType){
		this.ID = ID;
		this.username = Username;
		this.password = Password;
		this.userType = userType;
	}

	public User(String Username, String Password, int userType){
		this(0, Username, Password, UserType.getUserType(userType));
	}

	public User(int ID, String Username, String Password, int userType){
		this(ID, Username, Password, UserType.getUserType(userType));
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String Username) {
		this.username = Username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String Password) {
		this.password = Password;
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

	public boolean equals(Object obj){
		return (obj instanceof User && getID() == ((User)obj).getID());
	}

}