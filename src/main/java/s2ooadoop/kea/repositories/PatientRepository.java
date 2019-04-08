package s2ooadoop.kea.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PatientRepository implements PatientRepositoryInterface {
    @Autowired
    private Database DB;

    @Override
    public ResultSet getPatient(int ID) throws SQLException {
        String sql = "SELECT * FROM patients WHERE id = ?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setInt(1, ID);
        return DB.QuerySql(pstmt);
    }

    @Override
    public ResultSet getPatients(String SortBy, boolean asc) throws SQLException {
        String sql = "SELECT * FROM patients ORDER BY ? ?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setString(1, SortBy);
        if(asc){
            pstmt.setString(2, "ASC");
        }
        else{
            pstmt.setString(2, "DESC");
        }
        return DB.QuerySql(pstmt);
    }

    @Override
    public int createPatient(int CPR, int Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description) throws SQLException {
        String sql = "INSERT INTO patients (`cpr`, `birthday`, `firstname`, `lastname`, `phonenumber`, `address`, `height`, `weight`, `description`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setInt(1, CPR);
        pstmt.setInt(2, Birthday);
        pstmt.setString(3, Firstname);
        pstmt.setString(4, Lastname);
        pstmt.setString(5, Phonenumber);
        pstmt.setString(6, Address);
        pstmt.setFloat(7, Height);
        pstmt.setFloat(8, Weight);
        pstmt.setString(9, Description);
        return DB.ExecuteSql(pstmt);
    }

    @Override
    public void editPatient(int ID, int CPR, int Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description) throws SQLException {
        String sql = "UPDATE patients SET cpr = ?, birthday = ?, firstname = ?, lastname = ?, phonenumber = ?, address = ?, height = ?, weight = ?, description = ? WHERE id = ?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setInt(1, CPR);
        pstmt.setInt(2, Birthday);
        pstmt.setString(3, Firstname);
        pstmt.setString(4, Lastname);
        pstmt.setString(5, Phonenumber);
        pstmt.setString(6, Address);
        pstmt.setFloat(7, Height);
        pstmt.setFloat(8, Weight);
        pstmt.setString(9, Description);
        pstmt.setInt(10, ID);
        DB.ExecuteSql(pstmt);
    }

    @Override
    public void deletePatient(int ID) throws SQLException {
        String sql = "DELETE FROM patients WHERE id = ?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setInt(1, ID);
        DB.ExecuteSql(pstmt);
    }

    @Override
    public ResultSet findPatientFromCPR(int CPR) throws SQLException {
        String sql = "SELECT * FROM patients WHERE cpr = ?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setInt(1, CPR);
        return DB.QuerySql(pstmt);
    }

    @Override
    public ResultSet findPatientFromBirthdayCPR(int birthday, int cpr) throws SQLException {
        String sql = "SELECT * FROM patients WHERE birthday=? and cpr=?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setInt(1, birthday);
        pstmt.setInt(2, cpr);
        return DB.QuerySql(pstmt);
    }

    @Override
    public ResultSet findPatientFromName(String name) throws SQLException {
        String sql = "SELECT * FROM patients WHERE concat(firstname, lastname) LIKE ?";
        PreparedStatement pstmt = DB.CreateConnectionR().prepareStatement(sql);
        pstmt.setString(1, "%" + name + "%");
        return DB.QuerySql(pstmt);
    }
}
