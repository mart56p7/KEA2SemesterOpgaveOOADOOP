package s2ooadoop.kea.models;

public enum UserType {
    NOTLOGGEDIN (0),
    SECRETARY (1),
    DOCTOR(2);

    UserType(int id){
        this.id = id;
    }

    private int id = 0;
    public int getId()
    {
                return id;
    }

    public static UserType getUserType(int id){
        for (UserType usertype : UserType.values()) {
            if(id == usertype.getId()){
                return usertype;
            }
        }

        return null;
    }
}
