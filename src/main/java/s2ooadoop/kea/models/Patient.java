package s2ooadoop.kea.models;



import javax.validation.constraints.NotEmpty;

public class Patient implements PatientInterface {


	private int ID;
	@NotEmpty(message="Please provide cpr")
	private int cpr;
	@NotEmpty(message="Please provide birthday")
	private int birthday;
	@NotEmpty(message="Please provide First name")
	private String firstname;
	@NotEmpty(message="Please provide Last name")
	private String lastname;
	private String phonenumber;
	private String address;
	private float height;
	private float weight;
	private String description;

	public Patient(){

	}

	public Patient(int ID, int cpr, int birthday, String firstname, String lastname, String phonenumber, String address, float height, float weight, String description){
		this.ID = ID;
		this.cpr = cpr;
		this.birthday = birthday;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.address = address;
		this.height = height;
		this.weight = weight;
		this.description = description;
	}

	@Override
	public int getCPR() {
		return this.cpr;
	}

	@Override
	public void setCPR(int cpr) {
		this.cpr = cpr;
	}

	@Override
	public int getBirthday() {
		return this.birthday;
	}

	@Override
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	@Override
	public String getFirstname(){
		return this.firstname;
	}

	@Override
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String getLastname() {
		return this.lastname;
	}

	@Override
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String getPhonenumber() {
		return this.phonenumber;
	}

	@Override
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public float getHeight() {
		return this.height;
	}

	@Override
	public void setHeight(float Height) {
		this.height = Height;
	}

	@Override
	public float getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void setID(int ID) {
		this.ID = ID;
	}

	public boolean equals(Object obj){
		return (obj instanceof Patient && getID() == ((Patient)obj).getID());
	}

}