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

	private IllnessRepositoryInterface IRI = new IllnessRepository();

	@Autowired
	private TreatmentService ts;

	public Illness getIllness(int ID) throws SQLException {
		ResultSet rs = IRI.getIllness(ID);
		Illness newIllness = null;
		if(rs.next()) {
			newIllness = new Illness(rs.getInt("id"), rs.getString("name"), getIllnessTreatments(ID));
		}
		return newIllness;
	}

	public List<Illness> getIllnesses() throws SQLException {
		ResultSet rs = IRI.getIllnesses();
		List<Illness> list = new ArrayList<>();
		while (rs.next()){
			list.add(new Illness(rs.getInt("id"), rs.getString("name"), getIllnessTreatments(rs.getInt("id"))));
		}
		return list;
	}

	public List<Treatment> getIllnessTreatments(int illnessID) throws SQLException {
		ResultSet rs = IRI.getIllnessTreatmentIds(illnessID);
		rs.last();
		int[] ids = new int[rs.getRow()];
		rs.beforeFirst();
		for(int i = 0; rs.next(); i++){
			ids[i] = rs.getInt("treatment_id");
		}
		return ts.getTreatments(ids);
	}

	public int CreateIllness(String name, int[] treatment_IDs) throws SQLException {
		return IRI.CreateIllness(name, treatment_IDs);
	}

	public void EditIllness(int ID, String name, int[] treatment_IDs) throws SQLException {
		IRI.EditIllness(ID, name, treatment_IDs);
	}

	public void DeleteIllness(int ID) throws SQLException {
		IRI.DeleteIllness(ID);
	}

}
/*
 	public List<Treatment> GetTreatments(int[] IDS) throws SQLException{
		if(IDS == null){
	        return null;
        }
		ResultSet rs = TRI.GetTreatments(IDS);
		List<Treatment> treatments = new ArrayList<>();
        while (rs.next()){
            treatments.add(new Treatment(rs.getInt("id"), rs.getString("name"), rs.getString("note"), getTreatmentMedicines(rs.getInt("id"))));
		}
		return treatments;
	}
*/