package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public class PrescriptionRepository implements PrescriptionRepositoryInterface {

	private Database Database;

	@Override
	public ResultSet getPrescription(int ID) {
		return null;
	}

	@Override
	public ResultSet getAllPrescriptions(int PatientID, boolean ActiveOnly) {
		return null;
	}

	@Override
	public int CreatePrescription(int PatientID, String Description, int MedicineID, Date StartDate, Date EndDate) {
		return 0;
	}

	@Override
	public void EditPrescription(int PatientID, String Description, int MedicineID, Date StartDate, Date EndDate) {

	}

	@Override
	public void DeletePrescription(int ID) {

	}
}