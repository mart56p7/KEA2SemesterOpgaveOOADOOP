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

/**
 * The Illness Service gives access to CRUD for illnesses.
 *
 * A illness contains a name and a treatment.
 *
 * Note: In version 2, we will be adding the option to have multiple treatments to one diagnose.
 * */

@Service
public class IllnessService {

	private IllnessRepositoryInterface IRI = new IllnessRepository();

	@Autowired
	private TreatmentService ts;

	public Illness getIllness(int ID) throws SQLException { // Returner specifik illness ud fra id
		ResultSet rs = IRI.getIllness(ID);
		Illness newIllness = null;
		if(rs.next()) {
			newIllness = new Illness(rs.getInt("id"), rs.getString("name"), getIllnessTreatments(ID));
		}
		return newIllness;
	}

	public List<Illness> getIllnesses() throws SQLException { // Læser et resultset og opretter Illness objekter baseret på indhold. Returner alle i en liste.
		ResultSet rs = IRI.getIllnesses();
		List<Illness> list = new ArrayList<>();
		while (rs.next()){
			list.add(new Illness(rs.getInt("id"), rs.getString("name"), getIllnessTreatments(rs.getInt("id"))));
		}
		return list;
	}

	public List<Treatment> getIllnessTreatments(int illnessID) throws SQLException { // Opretter og returnerer en liste over treatments der hører til illness med indsat ID
		ResultSet rs = IRI.getIllnessTreatmentIds(illnessID);
		rs.last();
		int[] ids = new int[rs.getRow()];
		rs.beforeFirst();
		for(int i = 0; rs.next(); i++){
			ids[i] = rs.getInt("treatment_id");
		}
		return ts.getTreatments(ids);
	}

	public int createIllness(String name, int[] treatment_IDs) throws SQLException { // Kalder til repository for at oprette ny Illness
		return IRI.createIllness(name, treatment_IDs);
	}

	public void editIllness(int ID, String name, int[] treatment_IDs) throws SQLException { // Kalder til repository for at opdatere en illness
		IRI.editIllness(ID, name, treatment_IDs);
	}

	public void deleteIllness(int ID) throws SQLException { // Kalder til repository for at slette en illness
		IRI.deleteIllness(ID);
	}

}