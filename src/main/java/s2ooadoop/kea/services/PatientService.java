package s2ooadoop.kea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s2ooadoop.kea.models.PatientModel;
import s2ooadoop.kea.models.PatientModelInterface;
import s2ooadoop.kea.repositories.PatientRepositoryInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepositoryInterface pri;

    public List<PatientModelInterface> findPatient(String findPatient) throws SQLException {
        //Termer en bruger kan søge efter for at finde en patient. Fuldt navn, delvist navn, fuldt CPR nummer, eller sidste 4 cifre i CPR nummer.
        ArrayList<PatientModelInterface> patients = new ArrayList<PatientModelInterface>();
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
    }
}
