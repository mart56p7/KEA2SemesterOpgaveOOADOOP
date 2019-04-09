package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Patient;
import s2ooadoop.kea.models.PatientInterface;
import s2ooadoop.kea.repositories.PatientRepository;
import s2ooadoop.kea.repositories.PatientRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * The PatientService contains core patient functionality. (CRUD / GET single or all patients)
 * */
@Service
public class PatientService {
    @Autowired
    PatientRepositoryInterface PRI;

    public List<PatientInterface> findPatient(String findPatient) throws SQLException {
        //Termer en bruger kan søge efter for at finde en patient. Fuldt navn, delvist navn, fuldt CPR nummer, eller sidste 4 cifre i CPR nummer.
        //if findPatient = 4 digits and is a number search for CPR PRI.findPatientFromCPR
        //if findPatient = 10 digits search for birthday + cpr PRI.findPatientFromBirthdayCPR
        //if findPatient is a string, search in firstname lastname PRI.findPatientFromName
        ResultSet rs = null;
        if(findPatient.length() > 0)
        {
            try
            {

                if(findPatient.length() == 4)
                {
                    int intvalue = Integer.parseInt(findPatient);
                    rs = PRI.findPatientFromCPR(intvalue);
                }
                else if(findPatient.length() == 10)
                {
                    int cpr = Integer.parseInt(findPatient.substring(0, 4));
                    int birthday = Integer.parseInt(findPatient.substring(4, 10));
                    rs = PRI.findPatientFromBirthdayCPR(birthday, cpr);
                }
                else
                {
                    //Bit weird, but we will search for it!
                    rs = PRI.findPatientFromName(findPatient);
                }
            }
            catch (NumberFormatException e){
                rs = PRI.findPatientFromName(findPatient);
            }
            catch (Exception e){
                System.out.println("I caught something unexpected!");
            }


        }
        List<PatientInterface> patients = new ArrayList<>();
        while(rs != null && rs.next()){
            patients.add(new Patient(
                    rs.getInt("id"),
                    rs.getInt("cpr"),
                    rs.getInt("birthday"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("phonenumber"),
                    rs.getString("address"),
                    rs.getFloat("height"),
                    rs.getFloat("weight"),
                    rs.getString("description")
            ));    
        }
        return patients;
    }

    public PatientInterface getPatient(int ID) throws SQLException {
        ResultSet rs = PRI.getPatient(ID);
        Patient patient = null;
        if (rs.next()) {
            patient = new Patient(
                rs.getInt("id"),
                rs.getInt("cpr"),
                rs.getInt("birthday"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("phonenumber"),
                rs.getString("address"),
                rs.getFloat("height"),
                rs.getFloat("weight"),
                rs.getString("description")
            );
        }
        return patient;
    }

    public List<PatientInterface> getPatients(String SortBy, boolean asc) throws SQLException{
        ResultSet rs = PRI.getPatients(SortBy, asc);
        ArrayList<PatientInterface> patients = new ArrayList<>();
        while (rs.next()) {
            patients.add( new Patient(
                rs.getInt("id"),
                rs.getInt("cpr"),
                rs.getInt("birthday"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("phonenumber"),
                rs.getString("address"),
                rs.getFloat("height"),
                rs.getFloat("weight"),
                rs.getString("description")
            ));
        }
        return patients;
    }

    public int createPatient(Patient patient) throws SQLException {
        return PRI.createPatient(patient.getCPR(),
                patient.getBirthday(),
                patient.getFirstname(),
                patient.getLastname(),
                patient.getPhonenumber(),
                patient.getAddress(),
                patient.getHeight(),
                patient.getWeight(),
                patient.getDescription()
        );
    }

    public void editPatient(Patient patient) throws SQLException {
        PRI.editPatient(
                patient.getID(),
                patient.getCPR(),
                patient.getBirthday(),
                patient.getFirstname(),
                patient.getLastname(),
                patient.getPhonenumber(),
                patient.getAddress(),
                patient.getHeight(),
                patient.getWeight(),
                patient.getDescription()
        );
    }

    public void deletePatient(int ID) throws SQLException {
        PRI.deletePatient(ID);
    }

}
