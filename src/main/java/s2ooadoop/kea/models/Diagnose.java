package s2ooadoop.kea.models;

import java.sql.Date;

public class Diagnose {

	private PatientInterface patient;
	private Illness illness;
	private String note;
	private Date date;
	private int ID;

	public Diagnose() {}

	public Diagnose(PatientInterface patient, Illness illness, String note, Date date) {
		this.patient = patient;
		this.illness = illness;
		this.note = note;
		this.date = date;
	}

	public Diagnose(int ID, PatientInterface patient, Illness illness, String note, Date date) {
		this.ID = ID;
		this.patient = patient;
		this.illness = illness;
		this.note = note;
		this.date = date;
	}

	public PatientInterface getPatient() { return this.patient; }

	public void setPatient(PatientInterface patient) { this.patient = patient; }

	public Illness getIllness() { return this.illness; }

	public void setIllness(Illness Illness) { this.illness = Illness; }

	public String getNote() { return this.note; }

	public void setNote(String note) { this.note = note; }

	public Date getDate() {	return this.date; }

	public void setDate(Date date) { this.date = date; }

	public int getID() { return this.ID; }

	public void setID(int ID) { this.ID = ID; }


	public boolean equals(Object obj){
		return (obj instanceof Diagnose && getID() == ((Diagnose)obj).getID());
	}

}