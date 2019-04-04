package s2ooadoop.kea.repositories;

import java.sql.ResultSet;

public interface IllnessRepositoryInterface {

	/**
	 * 
	 * @param ID
	 */
	ResultSet GetIllness(int ID);

	/**
	 * 
	 * @param Name
	 * @param TreatmentID
	 */
	int CreateIllness(String Name, int[] TreatmentID);

	/**
	 * 
	 * @param Name
	 * @param TreatmentID
	 * @param ID
	 */
	void EditIllness(String Name, int[] TreatmentID, int ID);

	/**
	 * 
	 * @param ID
	 */
	void DeleteIllness(int ID);

}