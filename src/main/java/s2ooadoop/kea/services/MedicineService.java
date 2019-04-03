package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Medicine;
import s2ooadoop.kea.repositories.MedicineRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

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