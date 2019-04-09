package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Diagnose;
import s2ooadoop.kea.repositories.DiagnoseRepository;
import s2ooadoop.kea.repositories.DiagnoseRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnoseService {

	private DiagnoseRepositoryInterface DRI = new DiagnoseRepository();

	@Autowired
	PatientService ps; // = new PatientService();
	@Autowired
	IllnessService is; // = new IllnessService()

	public Diagnose getDiagnose(int ID) throws SQLException {
		ResultSet rs = DRI.getDiagnose(ID);
		Diagnose diagnose = null;
		if (rs.next()) {
			diagnose = new Diagnose(
					rs.getInt("id"),
					ps.getPatient(rs.getInt("patient_id")),
					is.getIllness(rs.getInt("illness_id")),
					rs.getString("note"),
					rs.getDate("date")
			);
		}
		return diagnose;
	}

	public List<Diagnose> getDiagnoses(int PatientID) throws SQLException {
		ResultSet rs = DRI.getDiagnoses(PatientID);
		List<Diagnose> diagnoses = new ArrayList<>();
		while (rs.next()) {
			diagnoses.add(new Diagnose(
					rs.getInt("id"),
					ps.getPatient(rs.getInt("patient_id")),
					is.getIllness(rs.getInt("illness_id")),
					rs.getString("note"),
					rs.getDate("date"))
			);
		}
		return diagnoses;

	}

	public List<Diagnose> getActiveDiagnoses(int PatientID) throws SQLException {
		ResultSet rs = DRI.getActiveDiagnoses(PatientID);
		ArrayList<Diagnose> diagnoses = new ArrayList<>();
		while (rs.next()) {
			diagnoses.add(new Diagnose(
					rs.getInt("id"),
					ps.getPatient(rs.getInt("patient_id")),
					is.getIllness(rs.getInt("illness_id")),
					rs.getString("note"),
					rs.getDate("date"))
			);
		}
		return diagnoses;
	}


	public int createDiagnose(Diagnose diagnose) throws SQLException {
		return DRI.createDiagnose(diagnose.getPatient().getID(), diagnose.getIllness().getID(), diagnose.getNote(), diagnose.getDate());
	}

	public void editDiagnose(Diagnose diagnose) throws SQLException {
		DRI.editDiagnose(diagnose.getID(), diagnose.getPatient().getID(), diagnose.getIllness().getID(), diagnose.getNote(), diagnose.getDate());
	}

	public void deleteDiagnose(int ID) throws SQLException {
		DRI.deleteDiagnose(ID);
	}

}