package s2ooadoop.kea.services;

import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Medicine;
import s2ooadoop.kea.models.MedicineInterface;
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
	public List<MedicineInterface> getMedicines(int[] ID) throws SQLException{
	    if(ID == null){
	        return null;
        }
		ResultSet rs = MRI.getMedicines(ID);
		Medicine medicine = null;
		List<MedicineInterface> medicines = new ArrayList<>();
        while (rs.next()){
            medicines.add(new Medicine(rs.getString("Name"),rs.getInt("ID")));
		}
		return medicines;
	}
	public  List<MedicineInterface> getMedicines() throws SQLException{
        ResultSet rs = MRI.getMedicines();
        Medicine medicine = null;
        List<MedicineInterface> medicines = new ArrayList<>();
        while (rs.next()){
            medicines.add(new Medicine(rs.getString("Name"),rs.getInt(("ID"))));
        }
        return medicines;
    }


	public int CreateMedicine(Medicine Medicine) throws SQLException {
	    return MRI.CreateMedicine(Medicine.getName());
	}

	public void EditMedicine(Medicine Medicine) throws SQLException {
		MRI.EditMedicine(Medicine.getID(),Medicine.getName());
	}

	public void DeleteMedicine(int ID) throws SQLException{
		MRI.DeleteMedicine(ID);
	}



}