package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class DiagnoseRepository implements DiagnoseRepositoryInterface {

	private Database Database;

	@Override
	public ResultSet GetDiagnose(int ID) {
		return null;
	}

	@Override
	public int CreateDiagnose(String Name, int[] MedicineID) {
		return 0;
	}

	@Override
	public void EditDiagnose(String Name, int[] MedicineID) {

	}

	@Override
	public void DeleteDiagnose(int ID) {

	}
}