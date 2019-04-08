package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IllnessRepository implements IllnessRepositoryInterface {

	private Database DB;

	public IllnessRepository(){
		DB = new Database();
	}

	@Override
	public ResultSet getIllness(int ID) throws SQLException {
		PreparedStatement stmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM illness WHERE id=?");
		stmt.setInt(1, ID);
		return DB.QuerySql(stmt);
	}

	public ResultSet getIllnesses() throws SQLException {
		PreparedStatement stmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM illness ORDER BY name");
		return DB.QuerySql(stmt);
	}

	@Override
	public int CreateIllness(String Name, int[] treatment_IDs) throws SQLException {
		PreparedStatement stmt = DB.CreateConnectionRWM().prepareStatement("INSERT INTO illness (name) VALUES (?)");
		stmt.setString(1, Name);
		int ID = DB.ExecuteSql(stmt);
		CreateIllnessTreatment(ID, treatment_IDs);
		return ID;
	}

	@Override
	public void EditIllness(int ID, String Name, int[] treatment_IDs) throws SQLException {
		PreparedStatement stmt = DB.CreateConnectionRWM().prepareStatement("UPDATE Illness SET name=? WHERE id=?");
		stmt.setString(1, Name);
		stmt.setInt(2, ID);
		EditIllnessTreatment(ID, treatment_IDs);
		DB.ExecuteSql(stmt);
	}

	@Override
	public void DeleteIllness(int ID) throws SQLException {
		PreparedStatement stmt = DB.CreateConnectionRWM().prepareStatement("DELETE FROM illness WHERE id=?");
		stmt.setInt(1, ID);
		DB.ExecuteSql(stmt);
	}


	// illnesstreatment section
	@Override
	public ResultSet getIllnessTreatmentIds(int ID) throws SQLException {
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM illnesstreatment WHERE illness_id = ?");
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	private void CreateIllnessTreatment(int illness_id, int[] treatment_ids) throws SQLException{
		if(treatment_ids == null){ return; }
		for(int i = 0; i < treatment_ids.length; i++){
			PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement("INSERT INTO illnesstreatment (`illness_id`, `treatment_id`) VALUES (?, ?)");
			pstmt.setInt(1, illness_id);
			pstmt.setInt(2, treatment_ids[i]);
			DB.ExecuteSql(pstmt);
		}
	}

	private void EditIllnessTreatment(int illness_id, int[] treatment_ids) throws SQLException{
		DeleteIllnessTreatment(illness_id);
		CreateIllnessTreatment(illness_id, treatment_ids);
	}

	private void DeleteIllnessTreatment(int ID) throws SQLException{
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement("DELETE FROM illnesstreatment WHERE illness_id = ?");
		pstmt.setInt(1, ID);
		DB.ExecuteSql(pstmt);
	}
}