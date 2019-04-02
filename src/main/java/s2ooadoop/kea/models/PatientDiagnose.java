package s2ooadoop.kea.models;

import java.util.Date;

public class PatientDiagnose {

	private PatientInterface patient;
	private Diagnose diagnose;
	private String note;
	private Date date;
	private int ID;

	public PatientDiagnose() {
		// TODO - implement PatientDiagnose.PatientDiagnose
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param patient
	 * @param diagnose
	 * @param note
	 * @param date
	 * @param ID
	 */
	public PatientDiagnose(PatientInterface patient, Diagnose diagnose, String note, Date date, int ID) {
		// TODO - implement PatientDiagnose.PatientDiagnose
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

	public Diagnose getDiagnose() {
		return this.diagnose;
	}

	/**
	 * 
	 * @param diagnose
	 */
	public void setDiagnose(Diagnose diagnose) {
		this.diagnose = diagnose;
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
		// TODO - implement PatientDiagnose.getID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		// TODO - implement PatientDiagnose.setID
		throw new UnsupportedOperationException();
	}

}