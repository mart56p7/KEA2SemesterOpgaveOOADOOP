package s2ooadoop.kea.models;

public class Medicine implements MedicineInterface {


    private String Name;
	private int ID;

    public Medicine() {
    }

    public Medicine(int ID, String name) {
		this.ID = ID;
		Name = name;
    }

    public String getName() {
		if(Name == null)
		{
			return "";
		}
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public boolean equals(Object obj){
		return (obj instanceof Medicine && getID() == ((Medicine)obj).getID());
	}
}