package s2ooadoop.kea.models;

import java.util.Date;

public interface PatientInterface {

	int getCPR();
	void setCPR(int CPR);
	int getBirthday();
	void setBirthday(int Birthday);
	String getFirstname();
	void setFirstname(String Firstname);
	String getLastname();
	void setLastname(String Lastname);
	String getPhonenumber();
	void setPhonenumber(String Phonenumber);
	String getAddress();
	void setAddress(String Address);
	float getHeight();
	void setHeight(float Height);
	float getWeight();
	void setWeight(float Weight);
	String getDescription();
	void setDescription(String Description);
	int getID();
	void setID(int ID);
}