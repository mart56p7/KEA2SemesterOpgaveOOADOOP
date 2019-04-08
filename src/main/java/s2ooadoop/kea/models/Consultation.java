package s2ooadoop.kea.models;

import java.util.Date;

public class Consultation {

	private PatientInterface patient;
	private String description;
	private String conclusion;
	private java.util.Date date;
	private int id;

	public Consultation(){

	}

	public Consultation(PatientInterface patient, String description, String conclusion, Date date){
		this(0, patient, description, conclusion, date);
	}

	public Consultation(int id, PatientInterface patient, String description, String conclusion, Date date){
		this.id = id;
		this.patient = patient;
		this.description = description;
		this.conclusion = conclusion;
		this.date = date;
	}

	public PatientInterface getPatient() {
		return this.patient;
	}

	public void setPatient(PatientInterface patient) {
		this.patient = patient;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getConclusion() {
		return this.conclusion;
	}

	public String getConclusion(int length) {
		return this.conclusion.substring(0, Math.min(length, conclusion.length()));
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public boolean equals(Object obj){
		return (obj instanceof Consultation && getID() == ((Consultation)obj).getID());
	}

}