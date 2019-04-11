package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public interface PrescriptionRepositoryInterface {


	ResultSet getPrescription(int ID) throws SQLException;

	ResultSet getPrescriptions(int PatientID,boolean ActiveOnly)throws SQLException;

	int CreatePrescription(int PatientID, String Description, int MedicineID, Date StartDate, Date EndDate)throws SQLException;

	void EditPrescription(int PrescriptionID,int PatientID, String Description, int MedicineID, Date StartDate, Date EndDate)throws SQLException;

	void DeletePrescription(int ID)throws SQLException;

}