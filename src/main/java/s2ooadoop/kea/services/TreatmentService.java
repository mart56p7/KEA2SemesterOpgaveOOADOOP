package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.MedicineInterface;
import s2ooadoop.kea.models.Treatment;
import s2ooadoop.kea.repositories.TreatmentRepository;
import s2ooadoop.kea.repositories.TreatmentRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {

	private TreatmentRepositoryInterface TRI = new TreatmentRepository() {
	};
	@Autowired
	private MedicineService MS;

	public Treatment GetTreatment(int ID) throws SQLException {
		ResultSet rs = TRI.GetTreatment(ID);
		Treatment treatment = null;
		if (rs.next()) {
			treatment = new Treatment(rs.getInt("id"), rs.getString("name"), rs.getString("note"), getTreatmentMedicines(rs.getInt("id")));
		}
		return treatment;
	}

	public List<Treatment> GetTreatments() throws SQLException {
		ResultSet rs = TRI.GetTreatments();
		ArrayList<Treatment> treatments = new ArrayList<>();
		while (rs.next()) {
			treatments.add(new Treatment(rs.getInt("id"), rs.getString("name"), rs.getString("note"), getTreatmentMedicines(rs.getInt("id"))));
		}
		return treatments;
	}

	public List<Treatment> GetTreatments(int[] IDS) throws SQLException{
		return null;
	}

	private List<MedicineInterface> getTreatmentMedicines(int treatmentid) throws SQLException {
		ResultSet rs = TRI.getTreatmentMedicineIds(treatmentid);
		boolean first = true;
		int[] ids = null;
		for(int i = 0; rs.next(); i++){
			if(first){
				first = false;
				ids = new int[rs.getInt("rows")];
			}
			ids[i] = rs.getInt("medicine_id");
		}
		return MS.getMedicines(ids);
	}

	public int CreateTreatment(String name, String note, int[] medicine_ids) throws SQLException {
		return TRI.CreateTreatment(name, note, medicine_ids);
	}


	public void EditTreatment(int ID, String name, String note, int[] medicine_ids) throws SQLException {
		TRI.EditTreatment(ID, name, note, medicine_ids);
	}


	public void DeleteTreatment(int ID) throws SQLException {
		TRI.DeleteTreatment(ID);
	}

}