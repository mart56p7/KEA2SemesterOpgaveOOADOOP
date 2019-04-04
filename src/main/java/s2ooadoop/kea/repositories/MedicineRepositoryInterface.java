package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public interface MedicineRepositoryInterface {

	ResultSet getMedicine(int ID)throws SQLException;
	int CreateMedicine(String Name)throws SQLException;
	void EditMedicine(int ID,String Name)throws SQLException;
	void DeleteMedicine(int ID)throws SQLException;
	ResultSet getMedicines(int[] ID)throws SQLException;


}
