package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;
import s2ooadoop.kea.models.Consultation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Repository
public interface ConsultationRepositoryInterface {

	ResultSet getConsultation(int ID) throws SQLException;
	ResultSet getConsultations(int personID) throws SQLException;
	//Contains Consultations where its less than 1 year since the consultation
	ResultSet getActiveConsultations(int personID) throws SQLException;

	int createConsultation(int PatientID, String Description, String Conclusion, Date Date) throws SQLException;
	void editConsultation(int ID, int PatientID, String Description, String Conclusion, Date Date) throws SQLException;
	void deleteConsultation(int ID) throws SQLException;
}