package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IllnessRepository implements IllnessRepositoryInterface {

	@Autowired
	private Database Database;

	@Override
	public ResultSet GetIllness(int ID) throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("SELECT * FROM illnesses WHERE id=?");
		stmt.setInt(1, ID);
		return Database.QuerySql(stmt);
	}

	@Override
	public int CreateIllness(String Name, int[] TreatmentID) throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("INSERT INTO illnesses (name) VALUES (?)");
		stmt.setString(1, Name);
		return Database.ExecuteSql(stmt);
	}

	@Override
	public void EditIllness(String Name, int[] TreatmentID, int ID) throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("UPDATE Illnesses SET name=? WHERE id=?");
		stmt.setString(1, Name);
		stmt.setInt(2, ID);
		Database.ExecuteSql(stmt);
	}

	@Override
	public void DeleteIllness(int ID) throws SQLException {
		PreparedStatement stmt = Database.CreateConnectionRWM().prepareStatement("DELETE FROM illnesses WHERE id=?");
		stmt.setInt(1, ID);
		Database.ExecuteSql(stmt);
	}
}