package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Medicine;
import s2ooadoop.kea.models.MedicineInterface;
import s2ooadoop.kea.models.Treatment;
import s2ooadoop.kea.repositories.TreatmentRepository;
import s2ooadoop.kea.repositories.TreatmentRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Treatment service, handle business logic for treatments in the patient system.
 * The treatment service uses the MedicineService to keep track of which medicines are used in a Treatment
 */

@Service
public class TreatmentService {

	private TreatmentRepositoryInterface TRI = new TreatmentRepository();

	@Autowired
	private MedicineService MS;

	public Treatment getTreatment(int ID) throws SQLException {
		ResultSet rs = TRI.getTreatment(ID);
		Treatment treatment = null;
		boolean first = true;
		List<MedicineInterface> meds = new ArrayList<>();
		while (rs.next()) {
			if(first){
				treatment = new Treatment(rs.getInt("treatment_id"), rs.getString("treatment_name"), rs.getString("treatment_note"), meds);

			}
			if(rs.getInt("medicine_id") != 0 ){
				meds.add(new Medicine(rs.getInt("medicine_id"), rs.getString("medicine_name")));
			}
		}
		return treatment;
	}

	public List<Treatment> getTreatments() throws SQLException {
		ResultSet rs = TRI.getTreatments();
		ArrayList<Treatment> treatments = new ArrayList<>();
		boolean first = true;
		int cid = 0;
		List<MedicineInterface> meds = new ArrayList<>();
		while (rs.next()) {
			//Create the treatment
			if(cid != rs.getInt("treatment_id")){
				meds = new ArrayList<>();
				treatments.add(new Treatment(rs.getInt("treatment_id"), rs.getString("treatment_name"), rs.getString("treatment_note"), meds));
				cid = rs.getInt("treatment_id");
			}
			//Add meds to it
			if(rs.getInt("medicine_id") != 0 ){
				meds.add(new Medicine(rs.getInt("medicine_id"), rs.getString("medicine_name")));
			}
		}
		return treatments;
	}

	public List<Treatment> getTreatments(int[] IDS) throws SQLException{
		if(IDS == null || IDS.length == 0){
			return new ArrayList<Treatment>();
		}
		ResultSet rs = TRI.getTreatments(IDS);
		ArrayList<Treatment> treatments = new ArrayList<>();
		boolean first = true;
		int cid = 0;
		List<MedicineInterface> meds = new ArrayList<>();
		while (rs.next()) {
			//Create the treatment
			if(cid != rs.getInt("treatment_id")){
				meds = new ArrayList<>();
				treatments.add(new Treatment(rs.getInt("treatment_id"), rs.getString("treatment_name"), rs.getString("treatment_note"), meds));
				cid = rs.getInt("treatment_id");
			}
			//Add meds to it
			if(rs.getInt("medicine_id") != 0 ){
				meds.add(new Medicine(rs.getInt("medicine_id"), rs.getString("medicine_name")));
			}
		}
		return treatments;
	}

	public int createTreatment(String name, String note, int[] medicine_ids) throws SQLException {
		return TRI.createTreatment(name, note, medicine_ids);
	}


	public void editTreatment(int ID, String name, String note, int[] medicine_ids) throws SQLException {
		TRI.editTreatment(ID, name, note, medicine_ids);
	}


	public void deleteTreatment(int ID) throws SQLException {
		TRI.deleteTreatment(ID);
	}

}