package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentRepository  implements TreatmentRepositoryInterface{

	@Autowired
	private Database DB;

	@Override
	public ResultSet GetTreatment(int ID) {
		return null;
	}

	@Override
	public int CreateTreatment(String name, int medicineID, String note) {
		return 0;
	}

	@Override
	public void EditTreatment(String name, int medicineID, String note, int ID) {

	}

	@Override
	public void DeleteTreatment(int ID) {

	}

	public ResultSet getTreatmentMedicineIds(int ID) throws SQLException  {
		String sql = "SELECT * FROM treatmentillness WHERE treatment_id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}
}