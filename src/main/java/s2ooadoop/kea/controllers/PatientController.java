package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import s2ooadoop.kea.models.PatientModelInterface;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.PatientService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    PatientService ps;

    Logging logger = new Logging("PatientController");

    @GetMapping("/patients/findPatient")
    public String findPatient(){
        logger.log("findPatient(): Start");
        logger.log("findPatient(): End");
        return "patients/findPatient";
    }

    @PostMapping("/patients/findPatient")
    public String findPatient(@RequestParam(value="patientdata", required=true) String patientdata, Model model){
        logger.log("findPatient(@RequestParam(value=\"patientdata\", required=true) String patientdata): Start");
        try {
            List<PatientModelInterface> pd = ps.findPatient(patientdata);
            if(pd.size() > 0)
            {
                if(pd.size() > 1)
                {
                    //We show the list of patients to pick from
                    model.addAttribute("patients", pd);
                    return("patients/findPatient");
                }
                //We redirect to show a single patient
                return("patients/patient/");
            }
            //We return that we couldnt find any patients
            model.addAttribute("patients", pd);
            return("patients/findPatient");
        } catch (SQLException e) {
            logger.log("findPatient(@RequestParam(value=\"patientdata\", required=true) String patientdata): " + e.getMessage());
        }
        logger.log("findPatient(@RequestParam(value=\"patientdata\", required=true) String patientdata): end");
        return "error";

        //return "patients/patientlist";
    }

    @ModelAttribute("userType")
    public UserType userType(HttpSession session){
        //0 = Not logged in
        //1 = Secretary
        //2 = Doctor
        return UserType.DOCTOR;
    }
}
