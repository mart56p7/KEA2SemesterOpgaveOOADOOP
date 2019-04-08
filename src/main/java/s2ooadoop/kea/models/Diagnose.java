package s2ooadoop.kea.models;

import java.util.Date;

public class Diagnose {

	private PatientInterface patient;
	private Illness Illness;
	private String note;
	private Date date;
	private int ID;

	public Diagnose() {
		// TODO - implement PatientIllness.PatientIllness
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param patient
	 * @param Illness
	 * @param note
	 * @param date
	 * @param ID
	 */
	public Diagnose(PatientInterface patient, Illness Illness, String note, Date date, int ID) {
		// TODO - implement PatientIllness.PatientIllness
		throw new UnsupportedOperationException();
	}

	public PatientInterface getPatient() {
		return this.patient;
	}

	/**
	 * 
	 * @param patient
	 */
	public void setPatient(PatientInterface patient) {
		this.patient = patient;
	}

	public Illness getIllness() {
		return this.Illness;
	}

	/**
	 * 
	 * @param Illness
	 */
	public void setIllness(Illness Illness) {
		this.Illness = Illness;
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

	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public int getID() {
		// TODO - implement PatientIllness.getID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		// TODO - implement PatientIllness.setID
		throw new UnsupportedOperationException();
	}


	public boolean equals(Object obj){
		return (obj instanceof Diagnose && getID() == ((Diagnose)obj).getID());
	}

}