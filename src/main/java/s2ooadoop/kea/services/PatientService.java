package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.Patient;
import s2ooadoop.kea.models.PatientModel;
import s2ooadoop.kea.models.PatientInterface;
import s2ooadoop.kea.repositories.PatientRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepositoryInterface PRI;

    public List<PatientInterface> findPatient(String findPatient) throws SQLException {
        System.out.println("SERVICE findPatient: " + findPatient);
        //Termer en bruger kan søge efter for at finde en patient. Fuldt navn, delvist navn, fuldt CPR nummer, eller sidste 4 cifre i CPR nummer.
        //if findPatient = 4 digits and is a number search for CPR PRI.findPatientFromCPR
        //if findPatient = 10 digits search for birthday + cpr PRI.findPatientFromBirthdayCPR
        //if findPatient is a string, search in firstname lastname PRI.findPatientFromName
        ResultSet rs = null;
        if(findPatient.length() > 0)
        {
            try
            {
                int intvalue = Integer.parseInt(findPatient);
                if(findPatient.length() == 4)
                {
                    rs = PRI.findPatientFromCPR(intvalue);
                }
                else if(findPatient.length() == 4)
                {
                    rs = PRI.findPatientFromBirthdayCPR(intvalue);
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
        System.out.println("1");
        List<PatientInterface> patients = new ArrayList<>();
        while(rs.next()){
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
        System.out.println("2");
        return patients;
        /*
        ArrayList<PatientInterface> patients = new ArrayList<PatientInterface>();
        if(findPatient.equalsIgnoreCase("Oliver") || findPatient.equalsIgnoreCase("Rasmus"))
        {
            PatientModel p = new PatientModel();
            p.setFirstname("Oliver");
            p.setLastname("Varnild");
            patients.add(p);
            p = new PatientModel();
            p.setFirstname("Rasmus Langelund");
            p.setLastname("Christensen");
            patients.add(p);
        }
        return patients;
        */
    }

    public PatientInterface GetPatient(int ID) throws SQLException {
        ResultSet rs = PRI.GetPatient(ID);
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

    public List<PatientInterface> GetPatients(String SortBy, boolean asc) throws SQLException{
        ResultSet rs = PRI.GetPatients(SortBy, asc);
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

    /**
     *
     * @param Patient
     */
    public int CreatePatient(Patient Patient) throws SQLException {
        // TODO - implement PatientService.CreatePatient
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Patient
     */
    public void EditPatient(Patient Patient) throws SQLException {
        // TODO - implement PatientService.EditPatient
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param ID
     */
    public void DeletePatient(int ID) throws SQLException {
        // TODO - implement PatientService.DeletePatient
        throw new UnsupportedOperationException();
    }

}
