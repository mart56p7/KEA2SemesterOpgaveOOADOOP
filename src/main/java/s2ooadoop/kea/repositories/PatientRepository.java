package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public class PatientRepository implements PatientRepositoryInterface {

    private Database Database;

    @Override
    public ResultSet GetPatient(int ID) {
        return null;
    }

    @Override
    public ResultSet GetPatients(String SortBy, boolean asc) {
        return null;
    }

    @Override
    public int CreatePatient(int CPR, Date Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description) {
        return 0;
    }

    @Override
    public void EditPatient(int CPR, Date Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description, int ID) {

    }

    @Override
    public void DeletePatient(int ID) {

    }
}
