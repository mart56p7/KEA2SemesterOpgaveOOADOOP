package s2ooadoop.kea.repositories;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public interface PatientRepositoryInterface {


    ResultSet GetPatient(int ID);

    ResultSet GetPatients(String SortBy, boolean asc);


    int CreatePatient(int CPR, Date Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description);

    void EditPatient(int CPR, Date Birthday, String Firstname, String Lastname, String Phonenumber, String Address, float Height, float Weight, String Description, int ID);

  
    void DeletePatient(int ID);

}