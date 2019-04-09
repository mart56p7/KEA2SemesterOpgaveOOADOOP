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
	public ResultSet getIllness(int ID) throws SQLException { // Returnerer specifik illness baseret på ID
		PreparedStatement stmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM illness WHERE id=?");
		stmt.setInt(1, ID);
		return DB.QuerySql(stmt);
	}

	@Override
	public ResultSet getIllnesses() throws SQLException { // Returnerer alle illnesses
		PreparedStatement stmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM illness ORDER BY name");
		return DB.QuerySql(stmt);
	}

	@Override
	public int createIllness(String Name, int[] treatment_IDs) throws SQLException { // Opretter en ny illness i databasen, og tilhørende illnesstreatment relationer
		PreparedStatement stmt = DB.CreateConnectionRWM().prepareStatement("INSERT INTO illness (name) VALUES (?)");
		stmt.setString(1, Name);
		DB.ExecuteSql(stmt);
		stmt = DB.CreateConnectionR().prepareStatement("SELECT max(id) FROM illness");
		ResultSet rs = DB.QuerySql(stmt);
		rs.next();
		int id = rs.getInt("max(id)");
		createIllnessTreatment(id, treatment_IDs);
		return id;
	}

	@Override
	public void editIllness(int ID, String Name, int[] treatment_IDs) throws SQLException { // Opdaterer en illness i databasen, og tilhørende illnesstreatment relationer
		PreparedStatement stmt = DB.CreateConnectionRWM().prepareStatement("UPDATE Illness SET name=? WHERE id=?");
		stmt.setString(1, Name);
		stmt.setInt(2, ID);
		editIllnessTreatment(ID, treatment_IDs);
		DB.ExecuteSql(stmt);
	}

	@Override
	public void deleteIllness(int ID) throws SQLException { // Sletter en illness fra databsen
		PreparedStatement stmt = DB.CreateConnectionRWM().prepareStatement("DELETE FROM illness WHERE id=?");
		stmt.setInt(1, ID);
		DB.ExecuteSql(stmt);
	}


	// illnesstreatment section
	@Override
	public ResultSet getIllnessTreatmentIds(int ID) throws SQLException { // Finder ID på tilhørende treatments, når den får illness ID
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM illnesstreatment WHERE illness_id = ?");
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}

	private void createIllnessTreatment(int illness_id, int[] treatment_ids) throws SQLException{ // Opretter nye relationer mellem illness og treatment
		if(treatment_ids == null){ return; }
		for(int i = 0; i < treatment_ids.length; i++){
			PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement("INSERT INTO illnesstreatment (`illness_id`, `treatment_id`) VALUES (?, ?)");
			pstmt.setInt(1, illness_id);
			pstmt.setInt(2, treatment_ids[i]);
			DB.ExecuteSql(pstmt);
		}
	}

	private void editIllnessTreatment(int illness_id, int[] treatment_ids) throws SQLException{ // Sletter gamle illnesstreatment relationer, og opretter nye.
		deleteIllnessTreatment(illness_id);
		createIllnessTreatment(illness_id, treatment_ids);
	}

	private void deleteIllnessTreatment(int ID) throws SQLException{ // Sletter en illnesstreatment relation.
		PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement("DELETE FROM illnesstreatment WHERE illness_id = ?");
		pstmt.setInt(1, ID);
		DB.ExecuteSql(pstmt);
	}
}