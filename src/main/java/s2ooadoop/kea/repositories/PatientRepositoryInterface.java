package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public interface PatientRepositoryInterface {
    public ResultSet getPatient(int ID) throws SQLException;
    public ResultSet getPatients(String SortBy, boolean asc) throws SQLException;
    public int createPatient(int CPR, int Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description) throws SQLException;
    public void editPatient(int ID, int CPR, int Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description) throws SQLException;
    public void deletePatient(int ID) throws SQLException;
    //Search functions
    public ResultSet findPatientFromCPR(int CPR) throws SQLException;
    public ResultSet findPatientFromBirthdayCPR(int birthday, int cpr) throws SQLException;
    public ResultSet findPatientFromName(String name) throws SQLException;
}