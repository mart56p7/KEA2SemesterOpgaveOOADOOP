package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IllnessRepository implements IllnessRepositoryInterface {

	@Autowired
	private Database Database;

	@Override
	public ResultSet GetIllness(int ID) throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("SELECT * FROM illness WHERE id=?");
		stmt.setInt(1, ID);
		return Database.QuerySql(stmt);
	}

	public ResultSet GetIllnesses() throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("SELECT * FROM illness ORDER BY name");
		return Database.QuerySql(stmt);
	}

	@Override
	public ResultSet getIllnessTreatmentIds(int ID) throws SQLException {
		PreparedStatement pstmt = Database.CreateConnectionR().prepareStatement("SELECT * FROM illnesstreatment WHERE illness_id = ?");
		pstmt.setInt(1, ID);
		return Database.QuerySql(pstmt);
	}

	@Override
	public int CreateIllness(String Name, int[] TreatmentID) throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("INSERT INTO illness (name) VALUES (?)");
		stmt.setString(1, Name);
		return Database.ExecuteSql(stmt);
	}

	@Override
	public void EditIllness(String Name, int[] TreatmentID, int ID) throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("UPDATE Illness SET name=? WHERE id=?");
		stmt.setString(1, Name);
		stmt.setInt(2, ID);
		Database.ExecuteSql(stmt);
	}

	@Override
	public void DeleteIllness(int ID) throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("DELETE FROM illness WHERE id=?");
		stmt.setInt(1, ID);
		Database.ExecuteSql(stmt);
	}


	// illnesstreatment section
	private void CreateIllnessTreatment(int illness_id, int[] treatment_ids) throws SQLException{
		if(treatment_ids == null){ return; }
		for(int i = 0; i < treatment_ids.length; i++){
			PreparedStatement pstmt = Database.CreateConnectionRWM().prepareStatement("INSERT INTO illnesstreatement (`illness_id`, `treatment_id`) VALUES (?, ?)");
			pstmt.setInt(1, illness_id);
			pstmt.setInt(1, treatment_ids[i]);
			Database.ExecuteSql(pstmt);
		}
	}

	private void EditTreatmentMedicine(int illness_id, int[] treatment_ids) throws SQLException{
		DeleteIllnessTreatment(illness_id);
		CreateIllnessTreatment(illness_id, treatment_ids);
	}

	private void DeleteIllnessTreatment(int ID) throws SQLException{
		PreparedStatement pstmt = Database.CreateConnectionRWM().prepareStatement("DELETE FROM illnesstreatment WHERE treatment_id = ?");
		pstmt.setInt(1, ID);
		Database.ExecuteSql(pstmt);
	}


}