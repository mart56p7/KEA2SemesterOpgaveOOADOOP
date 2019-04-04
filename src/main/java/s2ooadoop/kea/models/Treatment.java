package s2ooadoop.kea.models;

public class Treatment {

	private String name;
	private MedicineInterface[] medicine;
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
	public Treatment(String name, MedicineInterface[] medicine, String note, int ID) {
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

	public MedicineInterface[] getMedicine() {
		return this.medicine;
	}

	/**
	 * 
	 * @param medicine
	 */
	public void setMedicine(MedicineInterface[] medicine) {
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