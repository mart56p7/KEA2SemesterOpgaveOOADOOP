package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public class PatientDiagnoseRepository implements PatientDiagnoseRepositoryInterface {

	private Database Database;

	@Override
	public ResultSet GetPatientDiagnose(int ID) {
		return null;
	}

	@Override
	public int CreatePatientDiagnose(int PatientID, int DiagnoseID, String Note, Date Date) {
		return 0;
	}

	@Override
	public void EditPatientDiagnose(int PatientID, int DiagnoseID, String Note, Date Date) {

	}

	@Override
	public void DeletePatientDiagnose(int ID) {

	}
}