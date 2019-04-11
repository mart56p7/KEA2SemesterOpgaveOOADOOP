package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Repository
public class DiagnoseRepository implements DiagnoseRepositoryInterface {

	private Database DB;

	public DiagnoseRepository(){ DB = new Database();}

	@Override
	public ResultSet getDiagnose(int ID) throws SQLException {
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM diagnoses WHERE id = ?");
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet getDiagnoses(int PatientID) throws SQLException {
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM diagnoses WHERE patient_id = ? ORDER BY id DESC");
		pstmt.setInt(1, PatientID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet getActiveDiagnoses(int PatientID) throws SQLException { // Returnerer diagnoser med PatientID, der er mindre end 1 år gammel
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM diagnoses WHERE date >= DATE_ADD(CURRENT_DATE(), INTERVAL -12 MONTH) AND patient_id = ? ORDER BY id DESC");
		pstmt.setInt(1, PatientID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public int createDiagnose(int PatientID, int IllnessID, String Note, Date date) throws SQLException {
		System.out.println("creatediagnose in repo");
		PreparedStatement stmt = DB.CreateConnectionRWM().prepareStatement("INSERT INTO diagnoses (patient_id, illness_id, note, date) VALUES (?, ?, ?, ?)");
		stmt.setInt(1, PatientID);
		stmt.setInt(2, IllnessID);
		stmt.setString(3, Note);
		stmt.setDate(4,  new java.sql.Date(date.getTime()));
		DB.ExecuteSql(stmt);
		stmt = DB.CreateConnectionR().prepareStatement("SELECT max(id) FROM diagnoses");
		ResultSet rs = DB.QuerySql(stmt);
		rs.next();
		return rs.getInt("max(id)");
	}

	@Override
	public void editDiagnose(int DiagnoseID, int PatientID, int IllnessID, String note, Date date) throws SQLException {
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement("UPDATE diagnoses SET `patient_id`=?, `illness_id`=?, `note`=?, `date`=? WHERE id = ?");
		pstmt.setInt(1, PatientID);
		pstmt.setInt(2, IllnessID);
		pstmt.setString(3, note);
		pstmt.setDate(4,  new java.sql.Date(date.getTime()));
		pstmt.setInt(5, DiagnoseID);
		DB.ExecuteSql(pstmt);
	}

	@Override
	public void deleteDiagnose(int ID) throws SQLException {
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement("DELETE FROM diagnoses WHERE id = ?");
		pstmt.setInt(1, ID);
		DB.ExecuteSql(pstmt);
	}
}