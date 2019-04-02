package s2ooadoop.kea.models;

import java.util.Date;

public interface PatientInterface {

	int getCPR();

	/**
	 * 
	 * @param CPR
	 */
	void setCPR(int CPR);

	Date getBirthday();

	/**
	 * 
	 * @param Birthday
	 */
	void setBirthday(Date Birthday);

	String getFirstname();

	/**
	 * 
	 * @param Firstname
	 */
	void setFirstname(String Firstname);

	String getLastname();

	/**
	 * 
	 * @param Lastname
	 */
	void setLastname(String Lastname);

	String getPhonenumber();

	/**
	 * 
	 * @param Phonenumber
	 */
	void setPhonenumber(String Phonenumber);

	String getAddress();

	/**
	 * 
	 * @param Address
	 */
	void setAddress(String Address);

	float getHeight();

	/**
	 * 
	 * @param Height
	 */
	void setHeight(float Height);

	float getWeight();

	/**
	 * 
	 * @param Weight
	 */
	void setWeight(float Weight);

	String getDescription();

	/**
	 * 
	 * @param Description
	 */
	void setDescription(String Description);

	int getID();

	/**
	 * 
	 * @param ID
	 */
	void setID(int ID);

}