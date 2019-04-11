package s2ooadoop.kea.models;

import java.sql.Date;

public class Prescription {



    private PatientInterface Patient;


    private String Description;
	private MedicineInterface Medicine;
	private Date StartDate;
	private Date EndDate;
	private int ID;

    public Prescription() {
    }

    public Prescription(PatientInterface patient, String description, MedicineInterface medicine, Date startDate, Date endDate) {
		this.Patient = patient;
		this.Description = description;
		this.Medicine = medicine;
		this.StartDate = startDate;
		this.EndDate = endDate;
    }

    public Prescription(int ID, PatientInterface patient, String description, MedicineInterface medicine, Date startDate, Date endDate) {
        this.ID = ID;
        this.Patient = patient;
		this.Description = description;
		this.Medicine = medicine;
		this.StartDate = startDate;
		this.EndDate = endDate;

    }
    public PatientInterface getPatient() {
		return Patient;
	}

	public void setPatient(PatientInterface patient) {
		this.Patient = patient;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public MedicineInterface getMedicine() {
		return this.Medicine;
	}

	public void setMedicine(MedicineInterface medicine) {
		this.Medicine = medicine;
	}

	public Date getStartDate() {
		return this.StartDate;
	}

	public void setStartDate(Date startDate) {
		this.StartDate = startDate;
	}

	public Date getEndDate() {
		return this.EndDate;
	}

	public void setEndDate(Date endDate) {
		this.EndDate = endDate;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}




	public boolean equals(Object obj){
		return (obj instanceof Prescription && getID() == ((Prescription)obj).getID());
	}

}