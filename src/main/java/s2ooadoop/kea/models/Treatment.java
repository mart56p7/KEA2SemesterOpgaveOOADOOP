package s2ooadoop.kea.models;

import java.util.List;

public class Treatment {

	private String name;
	private List<MedicineInterface> medicine;
	private String note;
	private int ID;

	public Treatment() {

	}

	public Treatment(int ID, String name, String note, List<MedicineInterface> medicine) {
		setID(ID);
		setName(name);
		setNote(note);
		setMedicine(medicine);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MedicineInterface> getMedicine() {
		return this.medicine;
	}

	public void setMedicine(List<MedicineInterface> medicine) {
		this.medicine = medicine;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public boolean equals(Object obj){
		return (obj instanceof Treatment && getID() == ((Treatment)obj).getID());
	}

}