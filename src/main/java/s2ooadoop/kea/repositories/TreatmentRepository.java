package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import s2ooadoop.kea.models.MedicineInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TreatmentRepository  implements TreatmentRepositoryInterface{

	//@Autowired
	private Database DB;

	public TreatmentRepository(){
		DB = new Database();
	}

	@Override
	public ResultSet GetTreatment(int ID) throws SQLException {
		String sql = "SELECT * FROM treatment WHERE id  = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet GetTreatments() throws SQLException {
		String sql = "SELECT * FROM treatment ORDER BY name";
		System.out.println("1");
		if(DB == null){
			System.out.println("DB = null");
		}
		if(DB.CreateConnectionR() == null){
			System.out.println("DB.CreateConnectionR() = null");
		}
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		System.out.println("2");
		return DB.QuerySql(pstmt);
	}

	@Override
	public ResultSet GetTreatments(int[] ids) throws SQLException {
		if(ids == null || ids.length == 0) { return null; }
		String idslist = "";
		for (int id : ids) {
			idslist += "?,";
		}
		idslist = idslist.substring(0, idslist.length()-1);
		String sql = "SELECT * FROM treatment WHERE id IN (" + idslist + ") ORDER BY name";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		for (int i = 1; i < ids.length+1; i++) {
			pstmt.setInt(i, ids[i-1]);
		}
		System.out.println(pstmt.toString());
		return DB.QuerySql(pstmt);
	}

	@Override
	public int CreateTreatment(String name, String note, int[] medicines) throws SQLException {
		String sql = "INSERT INTO treatment (`name`, `note`) VALUES (?, ?)";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, note);
		int aid = DB.ExecuteSql(pstmt);
		CreateTreatmentMedicine(aid, medicines);
		return aid;
	}

	@Override
	public void EditTreatment(int ID, String name, String note, int[] medicines) throws SQLException {
		String sql = "UPDATE treatment SET name = ?, note = ? WHERE id = ?";
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, note);
		pstmt.setInt(3, ID);
		EditTreatmentMedicine(ID, medicines);
		DB.ExecuteSql(pstmt);
	}


	@Override
	public void DeleteTreatment(int ID) throws SQLException {
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
			String sql = "INSERT INTO treatementmedicine (`threatment_id`, `medicine_id`) VALUES (?, ?)";
			PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement(sql);
			pstmt.setInt(1, treatment_id);
			pstmt.setInt(1, medicine_ids[i]);
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