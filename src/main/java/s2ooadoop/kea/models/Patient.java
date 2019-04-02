package s2ooadoop.kea.models;

import java.util.Date;

public class Patient implements PatientInterface {

	private int CPR;
	private Date Birthday;
	private String Firstname;
	private String Lastname;
	private String Phonenumber;
	private String Address;
	private float Height;
	private float Weight;
	private String Description;
	private int ID;

	@Override
	public int getCPR() {
		return 0;
	}

	@Override
	public void setCPR(int CPR) {

	}

	@Override
	public Date getBirthday() {
		return null;
	}

	@Override
	public void setBirthday(Date Birthday) {

	}

	@Override
	public String getFirstname() {
		return null;
	}

	@Override
	public void setFirstname(String Firstname) {

	}

	@Override
	public String getLastname() {
		return null;
	}

	@Override
	public void setLastname(String Lastname) {

	}

	@Override
	public String getPhonenumber() {
		return null;
	}

	@Override
	public void setPhonenumber(String Phonenumber) {

	}

	@Override
	public String getAddress() {
		return null;
	}

	@Override
	public void setAddress(String Address) {

	}

	@Override
	public float getHeight() {
		return 0;
	}

	@Override
	public void setHeight(float Height) {

	}

	@Override
	public float getWeight() {
		return 0;
	}

	@Override
	public void setWeight(float Weight) {

	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public void setDescription(String Description) {

	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void setID(int ID) {

	}
}