package s2ooadoop.kea.models;

import java.util.Date;

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
        Patient = patient;
        Description = description;
        Medicine = medicine;
        StartDate = startDate;
        EndDate = endDate;
    }

    public Prescription(int ID, PatientInterface patient, String description, MedicineInterface medicine, Date startDate, Date endDate) {
        this.ID = ID;
        Patient = patient;
        Description = description;
        Medicine = medicine;
        StartDate = startDate;
        EndDate = endDate;

    }
    public PatientInterface getPatient() {
		return Patient;
	}

	public void setPatient(PatientInterface patient) {
		Patient = patient;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public MedicineInterface getMedicine() {
		return this.Medicine;
	}

	public void setMedicine(MedicineInterface medicine) {
		Medicine = medicine;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
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