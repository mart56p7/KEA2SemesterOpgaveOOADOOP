package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Consultation;
import s2ooadoop.kea.repositories.ConsultationRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A consultation is done between a doctor and a patient.
 * After the consultation is done, the consiltation containing
 * Description
 * Conclusion
 * Date
 *
 * is saved, referring to the patient
 * */

@Service
public class ConsultationService {

	@Autowired
	private ConsultationRepositoryInterface KRI;
	@Autowired
	private PatientService PS;

	/**
	 * Active consultations, are consultations that are less than 12 months old.
	 * */
	public List<Consultation> getActiveConsultations(int patientID) throws SQLException {
		ResultSet rs = KRI.getActiveConsultations(patientID);
		ArrayList<Consultation> consultations = new ArrayList<>();
		while (rs.next()) {
			consultations.add(
					new Consultation(
							rs.getInt("id"),
							PS.getPatient(rs.getInt("patient_id")),
							rs.getString("description"),
							rs.getString("conclusion"),
							rs.getDate("date"))
			);
		}
		return consultations;
	}

	public List<Consultation> getConsultations(int patientID) throws SQLException {
		ResultSet rs = KRI.getConsultations(patientID);
		ArrayList<Consultation> consultations = new ArrayList<>();
		while (rs.next()) {
			consultations.add(
					new Consultation(
							rs.getInt("id"),
							PS.getPatient(rs.getInt("patient_id")),
							rs.getString("description"),
							rs.getString("conclusion"),
							rs.getDate("date"))
			);
		}
		return consultations;
	}



	public Consultation getConsultation(int ID) throws SQLException {
		ResultSet rs = KRI.getConsultation(ID);
		Consultation consultation = null;
		if (rs.next()) {
			consultation = new Consultation(
							rs.getInt("id"),
							PS.getPatient(rs.getInt("patient_id")),
							rs.getString("description"),
							rs.getString("conclusion"),
							rs.getDate("date"));
		}
		return consultation;
	}

	public int CreateConsultation(Consultation consultation) throws SQLException {
		return KRI.createConsultation(consultation.getPatient().getID(), consultation.getDescription(), consultation.getConclusion(), consultation.getDate());
	}

	public void EditConsultation(Consultation consultation) throws SQLException {
		KRI.editConsultation(consultation.getID(), consultation.getPatient().getID(), consultation.getDescription(), consultation.getConclusion(), consultation.getDate());
	}

	public void DeleteConsultation(int ID) throws SQLException {
		KRI.deleteConsultation(ID);
	}

}