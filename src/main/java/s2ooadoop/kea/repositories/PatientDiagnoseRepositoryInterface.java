package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public interface PatientDiagnoseRepositoryInterface {

	/**
	 * 
	 * @param ID
	 */
	ResultSet GetPatientDiagnose(int ID);

	/**
	 * 
	 * @param PatientID
	 * @param DiagnoseID
	 * @param Note
	 * @param Date
	 */
	int CreatePatientDiagnose(int PatientID, int DiagnoseID, String Note, Date Date);

	/**
	 * 
	 * @param PatientID
	 * @param DiagnoseID
	 * @param Note
	 * @param Date
	 */
	void EditPatientDiagnose(int PatientID, int DiagnoseID, String Note, Date Date);

	/**
	 * 
	 * @param ID
	 */
	void DeletePatientDiagnose(int ID);

}