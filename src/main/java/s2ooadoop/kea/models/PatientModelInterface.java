package s2ooadoop.kea.models;

import java.util.Date;

public interface PatientModelInterface {

    public int getCPR();
    public void setCPR(int CPR);
    public Date getBirthday();
    public void setBirthday(Date birthday);
    public String getFirstname();
    public void setFirstname(String firstname);
    public String getLastname();
    public void setLastname(String lastname);
    public String getPhonenumber();
    public void setPhonenumber(String phonenumber);
    public String getAddress();
    public void setAddress(String address);
    public float getHeight();
    public void setHeight(float height);
    public float getWeight();
    public void setWeight(float weight);
    public String getDescription();
    public void setDescription(String description);

}
