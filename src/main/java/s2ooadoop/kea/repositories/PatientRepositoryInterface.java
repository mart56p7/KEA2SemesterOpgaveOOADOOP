package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public interface PatientRepositoryInterface {
    public ResultSet GetPatient(int ID) throws SQLException;
    public ResultSet GetPatients(String SortBy, boolean asc) throws SQLException;
    public int CreatePatient(int CPR, int Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description) throws SQLException;
    public void EditPatient(int ID, int CPR, int Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description) throws SQLException;
    public void DeletePatient(int ID) throws SQLException;
    //Search functions
    public ResultSet findPatientFromCPR(int CPR) throws SQLException;
    public ResultSet findPatientFromBirthdayCPR(int BirthdayCPR) throws SQLException;
    public ResultSet findPatientFromName(String name) throws SQLException;
}