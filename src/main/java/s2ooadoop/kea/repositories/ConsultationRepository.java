package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public class ConsultationRepository implements ConsultationRepositoryInterface {
	@Autowired
	private Database DB;

	@Override
	public ResultSet GetConsultation(int ID) throws SQLException {
		String sql = "SELECT * FROM consultations WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet GetConsultations(int patientID) throws SQLException {
		String sql = "SELECT * FROM consultations WHERE patient_id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, patientID);
		return DB.QuerySql(pstmt);
	}

	/**
	 * An active Consultation is less than 12 months old
	 */
	@Override
	public ResultSet GetActiveConsultations(int patientID) throws SQLException {
		String sql = "SELECT * FROM consultations WHERE date >= DATE_ADD(CURRENT_DATE(), INTERVAL -12 MONTH) AND patient_id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, patientID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public int CreateConsultation(int patientID, String description, String conclusion, Date date) throws SQLException {
		String sql = "INSERT INTO consultations (`patient_id`, `description`, `conclusion`, `date`) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setInt(1, patientID);
		pstmt.setString(2, description);
		pstmt.setString(3, conclusion);
		pstmt.setDate(4, new java.sql.Date(date.getTime()));
		return DB.ExecuteSql(pstmt);
	}

	@Override
	public void EditConsultation(int id, int patientID, String description, String conclusion, Date date) throws SQLException {
		String sql = "UPDATE consultations SET `patient_id`=?, `description`=?, `conclusion`=?, `date`=? WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setInt(1, patientID);
		pstmt.setString(2, description);
		pstmt.setString(3, conclusion);
		pstmt.setDate(4, new java.sql.Date(date.getTime()));
		pstmt.setInt(5, id);
		DB.ExecuteSql(pstmt);
	}

	@Override
	public void DeleteConsultation(int id) throws SQLException {
		String sql = "DELETE FROM consultations WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setInt(1, id);
		DB.ExecuteSql(pstmt);
	}
}