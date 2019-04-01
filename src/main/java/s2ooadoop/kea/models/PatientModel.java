package s2ooadoop.kea.models;


import java.util.Date;

public class PatientModel implements PatientModelInterface {
    private int CPR;
    private Date Birthday;
    private String Firstname;
    private String Lastname;
    private String Phonenumber;
    private String Address;
    private float Height;
    private float Weight;
    private String Description;

    @Override
    public int getCPR() {
        return CPR;
    }

    @Override
    public void setCPR(int CPR) {
        this.CPR = CPR;
    }

    @Override
    public Date getBirthday() {
        return Birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    @Override
    public String getFirstname() {
        return Firstname;
    }

    @Override
    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    @Override
    public String getLastname() {
        return Lastname;
    }

    @Override
    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    @Override
    public String getPhonenumber() {
        return Phonenumber;
    }

    @Override
    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    @Override
    public String getAddress() {
        return Address;
    }

    @Override
    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public float getHeight() {
        return Height;
    }

    @Override
    public void setHeight(float height) {
        Height = height;
    }

    @Override
    public float getWeight() {
        return Weight;
    }

    @Override
    public void setWeight(float weight) {
        Weight = weight;
    }

    @Override
    public String getDescription() {
        return Description;
    }

    @Override
    public void setDescription(String description) {
        Description = description;
    }
}
