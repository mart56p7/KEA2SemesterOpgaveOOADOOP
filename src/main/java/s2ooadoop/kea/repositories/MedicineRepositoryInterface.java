package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public interface MedicineRepositoryInterface {

	/**
	 * 
	 * @param ID
	 */
	ResultSet getMedicine(int ID);

	/**
	 * 
	 * @param Name
	 */
	int CreateMedicine(String Name);

	/**
	 * 
	 * @param Name
	 */
	void EditMedicine(String Name);

	/**
	 * 
	 * @param ID
	 */
	void DeleteMedicine(int ID);

}