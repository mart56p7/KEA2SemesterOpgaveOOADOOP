package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
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
	public ResultSet getTreatmentMedicineIds(int ID) throws SQLException;
}