package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public interface DiagnoseRepositoryInterface {

	ResultSet getDiagnose(int ID) throws SQLException;

	ResultSet getDiagnoses(int PatientID) throws SQLException;

	ResultSet getActiveDiagnoses(int PatientID) throws SQLException;

	int createDiagnose(int PatientID, int IllnessID, String Note, Date Date) throws SQLException;

	void editDiagnose(int DiagnoseID, int PatientID, int IllnessID, String Note, Date Date) throws SQLException;

	void deleteDiagnose(int ID) throws SQLException;

}