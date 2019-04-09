package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MedicineRepository implements MedicineRepositoryInterface {

	@Autowired
	private Database DB;

	@Override
	public ResultSet getMedicine(int ID) throws SQLException {
        String sql = "SELECT * FROM medicine WHERE ID = ?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setInt(1, ID);
        return DB.QuerySql(pstmt);
	}
	@Override
    public ResultSet getMedicines(int[] ID) throws SQLException{
        if(ID == null || ID.length == 0) { return null; }
        String idslist = "";
        for (int id : ID) {
            idslist += "?,";
        }
        idslist = idslist.substring(0, idslist.length()-1);
        String sql = "SELECT * FROM medicine WHERE id IN (" + idslist + ") ORDER BY name";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        for (int i = 1; i < ID.length+1; i++) {
            pstmt.setInt(i, ID[i-1]);
        }
        return DB.QuerySql(pstmt);
    }

    @Override
    public ResultSet getMedicines() throws SQLException {
        String sql = "SELECT * FROM medicine ORDER BY name";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        return DB.QuerySql(pstmt);
    }

    @Override
	public int CreateMedicine(String Name) throws SQLException{
        String sql = "INSERT INTO medicine (`Name`) VALUES (?)";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setString(1, Name);
        return DB.ExecuteSql(pstmt);
	}
	@Override
	public void EditMedicine(int ID, String Name) throws SQLException {
        String sql = "UPDATE medicine SET `Name` = ? WHERE id = ?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setString(1, Name);
        pstmt.setInt(2, ID);
        DB.ExecuteSql(pstmt);
	}
	@Override
	public void DeleteMedicine(int ID) throws SQLException{
	    String sql = "DELETE FROM medicine WHERE id=?";
	    PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
	    pstmt.setInt(1,ID);
	    DB.ExecuteSql(pstmt);
	}

}