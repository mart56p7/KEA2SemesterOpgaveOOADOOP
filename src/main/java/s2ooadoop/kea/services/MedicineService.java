package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Medicine;
import s2ooadoop.kea.repositories.MedicineRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService {

	private MedicineRepositoryInterface MRI;


	public Medicine GetMedicine(int ID) throws SQLException {
        ResultSet rs = MRI.getMedicine(ID);
        Medicine medicine = null;
        if (rs.next()) {
            medicine = new Medicine(rs.getString("Name"),rs.getInt("ID"));
        }
        return medicine;
	}
	public List<Medicine> getMedicines(int[] ID) throws SQLException{
		ResultSet rs = MRI.getMedicines(ID);
		Medicine medicine = null;
		List<Medicine> medicines = new ArrayList<>();
        while (rs.next()){
            medicine = new Medicine(rs.getString("Name"),rs.getInt("ID"));
            medicines.add(medicine);
		}
		return medicines;
	}


	public int CreateMedicine(Medicine Medicine) throws SQLException {
	    return MRI.CreateMedicine(Medicine.getName());
	}

	public void EditMedicine(Medicine Medicine) throws SQLException {
		MRI.EditMedicine(Medicine.getName());
	}

	public void DeleteMedicine(int ID) throws SQLException{
		MRI.DeleteMedicine(ID);
	}

}