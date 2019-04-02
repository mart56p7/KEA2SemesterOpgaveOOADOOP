package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public interface DiagnoseRepositoryInterface {

	/**
	 * 
	 * @param ID
	 */
	ResultSet GetDiagnose(int ID);

	/**
	 * 
	 * @param Name
	 * @param MedicineID
	 */
	int CreateDiagnose(String Name, int[] MedicineID);

	/**
	 * 
	 * @param Name
	 * @param MedicineID
	 */
	void EditDiagnose(String Name, int[] MedicineID);

	/**
	 * 
	 * @param ID
	 */
	void DeleteDiagnose(int ID);

}