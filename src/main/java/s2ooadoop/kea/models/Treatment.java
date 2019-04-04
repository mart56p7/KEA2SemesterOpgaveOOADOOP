package s2ooadoop.kea.models;

import java.util.List;

public class Treatment {

	private String name;
	private List<MedicineInterface> medicine;
	private String note;
	private int ID;

	public Treatment() {
		// TODO - implement Treatment.Treatment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param medicine
	 * @param note
	 * @param ID
	 */
	public Treatment(int ID, String name, String note, List<MedicineInterface> medicine) {
		// TODO - implement Treatment.Treatment
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public List<MedicineInterface> getMedicine() {
		return this.medicine;
	}

	/**
	 * 
	 * @param medicine
	 */
	public void setMedicine(List<MedicineInterface> medicine) {
		this.medicine = medicine;
	}

	public String getNote() {
		return this.note;
	}

	/**
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	public int getID() {
		// TODO - implement Treatment.getID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		// TODO - implement Treatment.setID
		throw new UnsupportedOperationException();
	}

}