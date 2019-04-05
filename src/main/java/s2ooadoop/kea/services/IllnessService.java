package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Illness;
import s2ooadoop.kea.models.Treatment;
import s2ooadoop.kea.repositories.IllnessRepository;
import s2ooadoop.kea.repositories.IllnessRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IllnessService {

	@Autowired
	private IllnessRepositoryInterface IRI;

	@Autowired
	private TreatmentService ts;

	public Illness GetIllness(int ID) throws SQLException {
		ResultSet rs = IRI.GetIllness(ID);
		Illness newIllness = null;
		if(rs.next()) {
			newIllness = new Illness(rs.getString("name"), getIllnessTreatments(ID).toArray(new Treatment[0]), rs.getInt("id"));
		}
		return newIllness;
	}

	public List<Illness> GetIllnesses() throws SQLException {
		List<Illness> list = new ArrayList<>();
		ResultSet rs = IRI.GetIllnesses();
		while (rs.next()){
			list.add(new Illness(rs.getString("name"), getIllnessTreatments(rs.getInt("id")).toArray(new Treatment[0]), rs.getInt("id")));
		}
		return list;
	}

	private List<Treatment> getIllnessTreatments(int illnessid) throws SQLException {
		ResultSet rs = IRI.getIllnessTreatmentIds(illnessid);
		boolean first = true;
		int[] ids = null;
		for(int i = 0; rs.next(); i++){
			if(first){
				first = false;
				ids = new int[rs.getInt("rows")];
			}
			ids[i] = rs.getInt("treatment_id");
		}
		return ts.GetTreatments(ids);
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