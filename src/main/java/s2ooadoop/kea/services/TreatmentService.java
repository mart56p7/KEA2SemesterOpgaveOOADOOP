package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import s2ooadoop.kea.models.Medicine;
import s2ooadoop.kea.models.MedicineInterface;
import s2ooadoop.kea.models.Treatment;
import s2ooadoop.kea.repositories.TreatmentRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TreatmentService {
	@Autowired
	private TreatmentRepositoryInterface TRI;
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

	public int CreateTreatment(Treatment treatment) {
		// TODO - implement TreatmentService.CreateTreatment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param treatment
	 */
	public void EditTreatment(Treatment treatment) {
		// TODO - implement TreatmentService.EditTreatment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void DeleteTreatment(int ID) {
		// TODO - implement TreatmentService.DeleteTreatment
		throw new UnsupportedOperationException();
	}

}