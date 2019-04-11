package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TreatmentRepository  implements TreatmentRepositoryInterface{

	@Autowired
	private Database DB;

	public TreatmentRepository(){
		DB = new Database();
	}

	@Override
	public ResultSet getTreatment(int ID) throws SQLException {
		String sql = "SELECT treatment.id AS treatment_id, " +
						"treatment.name AS treatment_name, " +
						"treatment.note AS treatment_note, " +
						"medicine.id AS medicine_id, " +
						"medicine.name AS medicine_name " +
						"FROM treatment " +
						"LEFT JOIN treatmentmedicine ON treatment.id = treatmentmedicine.treatment_id " +
						"LEFT JOIN medicine ON medicine_id = medicine.id " +
						"WHERE treatment.id = ? ORDER BY medicine.name";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet getTreatments() throws SQLException {
		String sql = "SELECT treatment.id AS treatment_id, " +
				"treatment.name AS treatment_name, " +
				"treatment.note AS treatment_note, " +
				"medicine.id AS medicine_id, " +
				"medicine.name AS medicine_name " +
				"FROM treatment " +
				"LEFT JOIN treatmentmedicine ON treatment.id = treatmentmedicine.treatment_id " +
				"LEFT JOIN medicine ON medicine_id = medicine.id " +
				"ORDER BY treatment.id, medicine.name";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet getTreatments(int[] ids) throws SQLException {
		if(ids == null || ids.length == 0) { return null; }
		String idslist = "";
		for (int id : ids) {
			idslist += "?,";
		}
		idslist = idslist.substring(0, idslist.length()-1);
		String sql = "SELECT treatment.id AS treatment_id, " +
				"treatment.name AS treatment_name, " +
				"treatment.note AS treatment_note, " +
				"medicine.id AS medicine_id, " +
				"medicine.name AS medicine_name " +
				"FROM treatment " +
				"LEFT JOIN treatmentmedicine ON treatment.id = treatmentmedicine.treatment_id " +
				"LEFT JOIN medicine ON medicine_id = medicine.id " +
				"WHERE treatment.id IN (" + idslist + ") ORDER BY treatment.id, medicine.name";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		for (int i = 1; i < ids.length+1; i++) {
			pstmt.setInt(i, ids[i-1]);
		}
		return DB.QuerySql(pstmt);
	}

	@Override
	public int createTreatment(String name, String note, int[] medicines) throws SQLException {
		String sql = "INSERT INTO treatment (`name`, `note`) VALUES (?, ?)";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, note);
		DB.ExecuteSql(pstmt);
		pstmt = DB.CreateConnectionR().prepareStatement("SELECT id FROM treatment ORDER BY id DESC LIMIT 1");
		ResultSet rs = DB.QuerySql(pstmt);
		rs.next();
		int aid = rs.getInt("id");
		CreateTreatmentMedicine(aid, medicines);
		return aid;
	}

	@Override
	public void editTreatment(int ID, String name, String note, int[] medicines) throws SQLException {
		String sql = "UPDATE treatment SET name = ?, note = ? WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, note);
		pstmt.setInt(3, ID);
		EditTreatmentMedicine(ID, medicines);
		DB.ExecuteSql(pstmt);
	}


	@Override
	public void deleteTreatment(int ID) throws SQLException {
		String sql = "DELETE FROM treatment WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setInt(1, ID);
		DB.ExecuteSql(pstmt);
	}

	public ResultSet getTreatmentMedicineIds(int ID) throws SQLException  {
		String sql = "SELECT * FROM treatmentmedicine WHERE treatment_id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	//Works on the table: treatmentmedicine
	private void CreateTreatmentMedicine(int treatment_id, int[] medicine_ids) throws SQLException{
		if(medicine_ids == null){ return; }
		for(int i = 0; i < medicine_ids.length; i++){
			String sql = "INSERT INTO treatmentmedicine (`treatment_id`, `medicine_id`) VALUES (?, ?)";
			PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
			pstmt.setInt(1, treatment_id);
			pstmt.setInt(2, medicine_ids[i]);
			DB.ExecuteSql(pstmt);
		}
	}

	//Works on the table: treatmentmedicine
	private void EditTreatmentMedicine(int treatment_id, int[] medicine_ids) throws SQLException{
		DeleteTreatmentMedicine(treatment_id);
		CreateTreatmentMedicine(treatment_id, medicine_ids);
	}


	//Works on the table: treatmentmedicine
	private void DeleteTreatmentMedicine(int ID) throws SQLException{
		String sql = "DELETE FROM treatmentmedicine WHERE treatment_id = ?";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setInt(1, ID);
		DB.ExecuteSql(pstmt);
	}
}