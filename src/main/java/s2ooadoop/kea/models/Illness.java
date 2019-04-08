package s2ooadoop.kea.models;

import java.util.List;

public class Illness {

	private String name;
	private List<Treatment> treatment;
	private int ID;

	public Illness() {
	}

	public Illness(int ID, String name, List<Treatment> treatment) {
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

	public List<Treatment> getTreatment() {
		return this.treatment;
	}

	public void setTreatment(List<Treatment> treatment) {
		this.treatment = treatment;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}


	public boolean equals(Object obj){
		return (obj instanceof Illness && getID() == ((Illness)obj).getID());
	}

}