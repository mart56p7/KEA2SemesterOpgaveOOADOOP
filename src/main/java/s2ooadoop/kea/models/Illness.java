package s2ooadoop.kea.models;

public class Illness {

	private String name;
	private Treatment[] treatment;
	private int ID;

	public Illness() {
		// TODO - implement Diagnose.Diagnose
		throw new UnsupportedOperationException();
	}

	public Illness(String name, Treatment[] treatment, int ID) {
		this.name = name;
		this.treatment = treatment;
		this.ID = ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Treatment[] getTreatment() {
		return this.treatment;
	}

	public void setTreatment(Treatment[] treatment) {
		this.treatment = treatment;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

}