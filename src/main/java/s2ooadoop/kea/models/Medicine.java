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
}