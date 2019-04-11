package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Medicine;
import s2ooadoop.kea.models.MedicineInterface;
import s2ooadoop.kea.repositories.MedicineRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The medicin service gives access to CRUD and getting a single Medicin or multiple.
 *
 * A medicin contains a name and a database ID.
 */


@Service
public class MedicineService {
    @Autowired
	private MedicineRepositoryInterface MRI;


	public Medicine GetMedicine(int ID) throws SQLException {
        ResultSet rs = MRI.getMedicine(ID);
        Medicine medicine = null;
        if (rs.next()) {
            medicine = new Medicine(rs.getInt("ID"),rs.getString("Name"));
        }
        return medicine;
	}
	public List<MedicineInterface> getMedicines(int[] ID) throws SQLException{
	    if(ID == null){
	        return  new ArrayList<MedicineInterface>();
        }
		ResultSet rs = MRI.getMedicines(ID);
		Medicine medicine = null;
		List<MedicineInterface> medicines = new ArrayList<>();
		if(rs != null) {
			while (rs.next()) {
				medicines.add(new Medicine(rs.getInt("ID"), rs.getString("Name")));
			}
		}
		return medicines;
	}
	public  List<MedicineInterface> getMedicines() throws SQLException{
        ResultSet rs = MRI.getMedicines();
        Medicine medicine = null;
        List<MedicineInterface> medicines = new ArrayList<>();
        while (rs.next()){
            medicines.add(new Medicine(rs.getInt(("ID")), rs.getString("Name")));
        }
        return medicines;
    }


	public int CreateMedicine(String medicine) throws SQLException {
	    return MRI.CreateMedicine(medicine);
	}

	public void EditMedicine(Medicine medicine) throws SQLException {
		MRI.EditMedicine(medicine.getID(),medicine.getName());
	}

	public void DeleteMedicine(int ID) throws SQLException{
		MRI.DeleteMedicine(ID);
	}



}