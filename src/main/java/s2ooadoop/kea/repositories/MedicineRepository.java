package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class MedicineRepository implements MedicineRepositoryInterface {

	private Database Database;

	@Override
	public ResultSet getMedicine(int ID) {
		return null;
	}

	@Override
	public int CreateMedicine(String Name) {
		return 0;
	}

	@Override
	public void EditMedicine(String Name) {

	}

	@Override
	public void DeleteMedicine(int ID) {

	}
}