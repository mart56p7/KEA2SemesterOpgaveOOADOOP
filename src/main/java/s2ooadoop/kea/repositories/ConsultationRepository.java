package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Repository
public class ConsultationRepository implements ConsultationRepositoryInterface {
	@Autowired
	private Database DB;

	@Override
	public ResultSet getConsultation(int ID) throws SQLException {
		String sql = "SELECT * FROM consultations WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet getConsultations(int patientID) throws SQLException {
		String sql = "SELECT * FROM consultations WHERE patient_id = ? ORDER BY id DESC";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, patientID);
		return DB.QuerySql(pstmt);
	}

	/**
	 * An active Consultation is less than 12 months old
	 */
	@Override
	public ResultSet getActiveConsultations(int patientID) throws SQLException {
		String sql = "SELECT * FROM consultations WHERE date >= DATE_ADD(CURRENT_DATE(), INTERVAL -12 MONTH) AND patient_id = ? ORDER BY id DESC";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, patientID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public int createConsultation(int patientID, String description, String conclusion, Date date) throws SQLException {
		String sql = "INSERT INTO consultations (`patient_id`, `description`, `conclusion`, `date`) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setInt(1, patientID);
		pstmt.setString(2, description);
		pstmt.setString(3, conclusion);
		pstmt.setDate(4, new java.sql.Date(date.getTime()+1));
		return DB.ExecuteSql(pstmt);
	}

	@Override
	public void editConsultation(int id, int patientID, String description, String conclusion, Date date) throws SQLException {
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
	public void deleteConsultation(int id) throws SQLException {
		String sql = "DELETE FROM consultations WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setInt(1, id);
		DB.ExecuteSql(pstmt);
	}
}