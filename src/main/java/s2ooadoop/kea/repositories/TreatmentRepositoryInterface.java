package s2ooadoop.kea.repositories;

import java.sql.ResultSet;

public interface TreatmentRepositoryInterface {

	/**
	 * 
	 * @param ID
	 */
	ResultSet GetTreatment(int ID);

	/**
	 * 
	 * @param name
	 * @param medicineID
	 * @param note
	 */
	int CreateTreatment(String name, int medicineID, String note);

	/**
	 * 
	 * @param name
	 * @param medicineID
	 * @param note
	 * @param ID
	 */
	void EditTreatment(String name, int medicineID, String note, int ID);

	/**
	 * 
	 * @param ID
	 */
	void DeleteTreatment(int ID);

}