package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public interface PrescriptionRepositoryInterface {

	/**
	 * 
	 * @param ID
	 */
	ResultSet getPrescription(int ID);

	/**
	 * 
	 * @param PatientID
	 * @param ActiveOnly
	 */
	ResultSet getAllPrescriptions(int PatientID, boolean ActiveOnly);

	/**
	 * 
	 * @param PatientID
	 * @param Description
	 * @param MedicineID
	 * @param StartDate
	 * @param EndDate
	 */
	int CreatePrescription(int PatientID, String Description, int MedicineID, Date StartDate, Date EndDate);

	/**
	 * 
	 * @param PatientID
	 * @param Description
	 * @param MedicineID
	 * @param StartDate
	 * @param EndDate
	 */
	void EditPrescription(int PatientID, String Description, int MedicineID, Date StartDate, Date EndDate);

	/**
	 * 
	 * @param ID
	 */
	void DeletePrescription(int ID);

}