package s2ooadoop.kea.models;

public enum UserType {
    NOTLOGGEDIN (0),
    SECRETARY (1),
    DOCTOR(2);

    UserType(int ID){
        this.ID = ID;
    }

    private int ID = 0;
    public int getID()
    {
                return ID;
    }

    public static UserType getUserType(int ID){
        for (UserType usertype : UserType.values()) {
            if(ID == usertype.getID()){
                return usertype;
            }
        }

        return null;
    }
}
