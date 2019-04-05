package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;
import s2ooadoop.kea.models.Consultation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public interface ConsultationRepositoryInterface {

	ResultSet GetConsultation(int ID) throws SQLException;
	ResultSet GetConsultations(int personID) throws SQLException;
	//Contains Consultations where its less than 1 year since the consultation
	ResultSet GetActiveConsultations(int personID) throws SQLException;

	int CreateConsultation(int PatientID, String Description, String Conclusion, Date Date) throws SQLException;
	void EditConsultation(int ID, int PatientID, String Description, String Conclusion, Date Date) throws SQLException;
	void DeleteConsultation(int ID) throws SQLException;
}