package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Illness;
import s2ooadoop.kea.models.MedicineInterface;
import s2ooadoop.kea.models.Treatment;
import s2ooadoop.kea.repositories.IllnessRepository;
import s2ooadoop.kea.repositories.IllnessRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class IllnessService {

	@Autowired
	private IllnessRepositoryInterface IRI;

	public Illness GetIllness(int ID) throws SQLException {
		ResultSet rs = IRI.GetIllness(ID);
		if(rs.next()) {
			return new Illness(
				/*rs.getString("name"),
				new Treatment[]();
				rs.getInt("ID");*/
			);
		} else {
			throw new SQLException("Diagnose does not exist");
		}
	}

	public int CreateIllness(Illness illness) throws SQLException {
		int[] TreatmentID = new int[illness.getTreatment().length];
		Treatment[] treatment = illness.getTreatment();
		for(int i = 0; i < treatment.length; i++){
			TreatmentID[i] = treatment[i].getID();
		}
		return IRI.CreateIllness(illness.getName(), TreatmentID);
	}

	public void EditIllness(Illness illness) throws SQLException {
		int[] TreatmentID = new int[illness.getTreatment().length];
		Treatment[] treatment = illness.getTreatment();
		for(int i = 0; i < treatment.length; i++){
			TreatmentID[i] = treatment[i].getID();
		}
		IRI.EditIllness(illness.getName(), TreatmentID, illness.getID());
	}

	public void DeleteIllness(int ID) throws SQLException {
		IRI.DeleteIllness(ID);
	}

}