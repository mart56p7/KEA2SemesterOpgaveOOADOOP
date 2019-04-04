package s2ooadoop.kea.models;



import java.util.Date;

public class Patient implements PatientInterface {

	private int ID;
	private int CPR;
	private int Birthday;
	private String Firstname;
	private String Lastname;
	private String Phonenumber;
	private String Address;
	private float Height;
	private float Weight;
	private String Description;

	public Patient(){

	}

	public Patient(int ID, int CPR, int Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description){
		this.ID = ID;
		this.CPR = CPR;
		this.Birthday = Birthday;
		this.Firstname = Firstname;
		this.Lastname = Lastname;
		this.Phonenumber = Phonenumber;
		this.Address = Address;
		this.Height = Height;
		this.Weight = Weight;
		this.Description = Description;
	}

	@Override
	public int getCPR() {
		return this.CPR;
	}

	@Override
	public void setCPR(int CPR) {
		this.CPR = CPR;
	}

	@Override
	public int getBirthday() {
		return this.Birthday;
	}

	@Override
	public void setBirthday(int Birthday) {
		this.Birthday = Birthday;
	}

	@Override
	public String getFirstname(){
		return this.Firstname;
	}

	@Override
	public void setFirstname(String Firstname) {
		this.Firstname = Firstname;
	}

	@Override
	public String getLastname() {
		return this.Lastname;
	}

	@Override
	public void setLastname(String Lastname) {
		this.Lastname = Lastname;
	}

	@Override
	public String getPhonenumber() {
		return this.Phonenumber;
	}

	@Override
	public void setPhonenumber(String Phonenumber) {
		this.Phonenumber = Phonenumber;
	}

	@Override
	public String getAddress() {
		return this.Address;
	}

	@Override
	public void setAddress(String Address) {
		this.Address = Address;
	}

	@Override
	public float getHeight() {
		return this.Height;
	}

	@Override
	public void setHeight(float Height) {
		this.Height = Height;
	}

	@Override
	public float getWeight() {
		return this.Weight;
	}

	@Override
	public void setWeight(float Weight) {
		this.Weight = Weight;
	}

	@Override
	public String getDescription() {
		return this.Description;
	}

	@Override
	public void setDescription(String Description) {
		this.Description = Description;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void setID(int ID) {
		this.ID = ID;
	}
}