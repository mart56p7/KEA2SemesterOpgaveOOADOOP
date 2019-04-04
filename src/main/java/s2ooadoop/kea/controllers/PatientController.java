package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.*;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.PatientService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    PatientService PS;

    Logging logger = new Logging("PatientController");

    @GetMapping("/patients/")
    public String index(Model model, HttpSession session){
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("patients", PS.GetPatients("id", true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "patients/index";
    }

    @GetMapping("/patients/index")
    public String index2(Model model, HttpSession session){
        return index(model, session);
    }

    @GetMapping("/patients/info/{ID}")
    public String showPatient(@PathVariable int ID, Model model, HttpSession session)
    {
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        logger.log("showPatient(): START");
        try {
            model.addAttribute("patient", PS.GetPatient(ID));
            logger.log("showPatient(): END");
            return "patients/info";
        } catch (SQLException e) {
            logger.log("Error: " + e.getMessage(), 1);
            logger.log("showPatient(): END");
            return "redirect:/error";
        }
    }

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
            List<PatientInterface> pd = PS.findPatient(patientdata);
            if(pd.size() > 0)
            {
                if(pd.size() > 1)
                {
                    //We show the list of patients to pick from
                    model.addAttribute("patients", pd);
                    logger.log("findPatient(@RequestParam(value=\"patientdata\", required=true) String patientdata): end");
                    return("patients/findPatient");
                }
                //We redirect to show a single patient
                logger.log("findPatient(@RequestParam(value=\"patientdata\", required=true) String patientdata): end");

                return("redirect:/patients/info/" + pd.get(0).getID());
            }
            //We return that we couldnt find any patients
            model.addAttribute("patients", pd);
            logger.log("findPatient(@RequestParam(value=\"patientdata\", required=true) String patientdata): end");
            return("patients/findPatient");
        } catch (SQLException e) {
            logger.log("findPatient(@RequestParam(value=\"patientdata\", required=true) String patientdata): erorr " + e.getMessage(), 1);
        }
        logger.log("findPatient(@RequestParam(value=\"patientdata\", required=true) String patientdata): end");
        return "error";
    }

    @GetMapping("/patients/create")
    public String create(HttpSession session) {
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        return "patients/create";
    }

    @PostMapping("/patients/create")
    public String createPatient(@ModelAttribute Patient patient, HttpSession session){
        logger.log("createPatient(@ModelAttribute Patient patient): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            PS.CreatePatient(patient);
            logger.log("Created patient", 1);
            logger.log("createPatient(@ModelAttribute Patient patient): END");
            return "redirect:/users/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createPatient(@ModelAttribute Patient patient): END");
            return "redirect:/users/error";
        }

    }

    @ModelAttribute("userType")
    public UserType userType(HttpSession session){
        return UserType.DOCTOR;
        //0 = Not logged in
        //1 = Secretary
        //2 = Doctor
        /*Object user = session.getAttribute("user");
        if(user instanceof User && user != null){
            System.out.println(((User)user).getUserType().name());
            return ((User)user).getUserType();
        }
        return UserType.NOTLOGGEDIN;
    */
    }
}
