package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Consultation;
import s2ooadoop.kea.repositories.ConsultationRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationService {

	@Autowired
	private ConsultationRepositoryInterface KRI;
	@Autowired
	private PatientService PS;

	public List<Consultation> GetActiveConsultations(int personID) throws SQLException {
		ResultSet rs = KRI.GetActiveConsultations(personID);
		ArrayList<Consultation> consultations = new ArrayList<>();
		System.out.println("GOT" + personID);
		while (rs.next()) {
			System.out.println("RETURNING" + rs.getInt("id"));
			consultations.add(
					new Consultation(
							rs.getInt("id"),
							PS.GetPatient(rs.getInt("patient_id")),
							rs.getString("description"),
							rs.getString("conclusion"),
							rs.getDate("date"))
			);
		}
		return consultations;
	}

	public List<Consultation> GetConsultations(int personID) throws SQLException {
		ResultSet rs = KRI.GetConsultations(personID);
		ArrayList<Consultation> consultations = new ArrayList<>();
		while (rs.next()) {
			consultations.add(
					new Consultation(
							rs.getInt("id"),
							PS.GetPatient(rs.getInt("patient_id")),
							rs.getString("description"),
							rs.getString("conclusion"),
							rs.getDate("date"))
			);
		}
		return consultations;
	}



	public Consultation GetConsultation(int ID) throws SQLException {
		ResultSet rs = KRI.GetConsultation(ID);
		Consultation consultation = null;
		if (rs.next()) {
			consultation = new Consultation(
							rs.getInt("id"),
							PS.GetPatient(rs.getInt("patient_id")),
							rs.getString("description"),
							rs.getString("conclusion"),
							rs.getDate("date"));
		}
		return consultation;
	}

	public int CreateConsultation(Consultation consultation) throws SQLException {
		return KRI.CreateConsultation(consultation.getPatient().getID(), consultation.getDescription(), consultation.getConclusion(), consultation.getDate());
	}

	public void EditConsultation(Consultation consultation) throws SQLException {
		KRI.EditConsultation(consultation.getID(), consultation.getPatient().getID(), consultation.getDescription(), consultation.getConclusion(), consultation.getDate());
	}

	public void DeleteConsultation(int ID) throws SQLException {
		KRI.DeleteConsultation(ID);
	}

}