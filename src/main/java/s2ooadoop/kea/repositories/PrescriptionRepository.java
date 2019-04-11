package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Repository
public class PrescriptionRepository implements PrescriptionRepositoryInterface {

	private Database DB = new Database();

	@Override
	public ResultSet getPrescription(int ID) throws SQLException {
		PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM prescription WHERE id = ?");
		pstmt.setInt(1, ID);
		return DB.QuerySql(pstmt);
	}


	@Override
	public ResultSet getPrescriptions(int PatientID,boolean ActiveOnly) throws SQLException {
        PreparedStatement pstmt = null;
        System.out.println(DB);
	    if(ActiveOnly){
            pstmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM prescription WHERE enddate >= DATE_ADD(CURRENT_DATE(), INTERVAL -12 MONTH) and patient_id = ?");
        } else {
            pstmt = DB.CreateConnectionR().prepareStatement("SELECT * FROM prescription WHERE patient_id = ?");
        }
        pstmt.setInt(1, PatientID);
        return DB.QuerySql(pstmt);
	}

	@Override
	public int CreatePrescription(int PatientID, String Description, int MedicineID, Date StartDate, Date EndDate) throws SQLException {
        PreparedStatement stmt = DB.CreateConnectionRWM().prepareStatement("INSERT INTO prescription (startdate, enddate, description, patient_id,medicine_id) VALUES (?, ?, ?, ?,?)");
        stmt.setDate(1,  new java.sql.Date(StartDate.getTime()));
        stmt.setDate(2,  new java.sql.Date(EndDate.getTime()));
        stmt.setString(3, Description);
        stmt.setInt(4, PatientID);
        stmt.setInt(5, MedicineID);
        System.out.println("DB Kald");
        return DB.ExecuteSql(stmt);
	}

	@Override
	public void EditPrescription(int PrescriptionID,int PatientID, String Description, int MedicineID, Date StartDate, Date EndDate) throws SQLException {
        PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement("UPDATE prescription SET `patient_id`=?, `description`=?, `medicine_id`=?, `startdate`=?,`enddate`=? WHERE id = ?");
        pstmt.setInt(1, PatientID);
        pstmt.setString(2,Description );
        pstmt.setInt(3, MedicineID);
        pstmt.setDate(4,  new java.sql.Date(StartDate.getTime()));
        pstmt.setDate(5,  new java.sql.Date(EndDate.getTime()));
        pstmt.setInt(6, PrescriptionID);
        DB.ExecuteSql(pstmt);
    }

	@Override
	public void DeletePrescription(int ID) throws SQLException{
        PreparedStatement pstmt = DB.CreateConnectionRWM().prepareStatement("DELETE FROM prescription WHERE id = ?");
        pstmt.setInt(1, ID);
        DB.ExecuteSql(pstmt);
	}
}