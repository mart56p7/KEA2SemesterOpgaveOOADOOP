package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentRepository {

	@Autowired
	private Database DB;

	public ResultSet getTreatmentMedicineIds(int ID) throws SQLException  {
		String sql = "SELECT * FROM treatmentillness WHERE treatment_id = ?";
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}
}