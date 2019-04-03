package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public interface MedicineRepositoryInterface {

	ResultSet getMedicine(int ID);
	int CreateMedicine(String Name);
	void EditMedicine(String Name);
	void DeleteMedicine(int ID);

}