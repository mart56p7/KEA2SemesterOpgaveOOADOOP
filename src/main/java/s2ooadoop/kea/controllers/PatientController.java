package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.Patient;
import s2ooadoop.kea.models.PatientInterface;
import s2ooadoop.kea.models.User;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    PatientService PS;
    @Autowired
    ConsultationService CS;
    @Autowired
    DiagnoseService DS;
    @Autowired
    PrescriptionService PRS;

    Logging logger = new Logging("PatientController");

    @GetMapping("/patients/")
    public String index(Model model, HttpSession session){
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("patients", PS.getPatients("id", true));
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
        if(!(userType(session) == UserType.DOCTOR || userType(session) == UserType.SECRETARY))
        {
            logger.log("User access denied");
            return "users/error";
        }
        logger.log("showPatient(): START");
        try {
            model.addAttribute("patient", PS.getPatient(ID));
            model.addAttribute("consultations", CS.getActiveConsultations(ID));
            model.addAttribute("diagnoses", DS.getActiveDiagnoses(ID));
            System.out.println("Prescription size = " + PRS.GetPrescriptions(ID, true).size());
            model.addAttribute("prescriptions", PRS.GetPrescriptions(ID, true));
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
    public String createPatient(@ModelAttribute("Patient") Patient patient, HttpSession session){
        logger.log("createPatient(@ModelAttribute Patient patient): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            PS.createPatient(patient);
            logger.log("Created patient", 1);
            logger.log("createPatient(@ModelAttribute Patient patient): END");
            return "redirect:/patients/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createPatient(@ModelAttribute Patient patient): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/patients/edit/{patientID}")
    public String edit(@PathVariable int patientID, Model model, HttpSession session)
    {
        logger.log("edit() : START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("patient", PS.getPatient(patientID));
            logger.log("edit() : END");
            return "patients/edit";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("edit() : END");
            return "users/error";
        }
    }

    @PostMapping("/patients/edit")
    public String editPatient(@ModelAttribute Patient patient,HttpSession session)
    {
        logger.log("editPatient(): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            PS.editPatient(patient);

        } catch (SQLException e) {
            logger.log("Error " + e.getMessage());
            logger.log("editPatient(): END");
            return "redirect:/error";
        }
        return "redirect:/patients/info/" + patient.getID();
    }

    @GetMapping("/patients/delete/{patientID}")
    public String delete(@PathVariable int patientID, Model model, HttpSession session)
    {
        logger.log("delete(): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("patient", PS.getPatient(patientID));
            logger.log("delete(): END");
            return "patients/delete";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("delete(): END");
            return "redirect:/error";
        }
    }

    @PostMapping("/patients/delete")
    public String deletePatient(@RequestParam(value="id", required=true) int id, HttpSession session)
    {
        logger.log("deletePatient(): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            PS.deletePatient(id);
            logger.log("deletePatient(): END");
            return "redirect:/patients/";
        } catch (SQLException e) {
            logger.log("deletePatient(): Error " + e.getMessage());
            logger.log("deletePatient(): END");
            return "redirect:/error";
        }
    }

    @ModelAttribute("userType")
    public UserType userType(HttpSession session){
        //0 = Not logged in
        //1 = Secretary
        //2 = Doctor
        Object user = session.getAttribute("user");
        if(user instanceof User && user != null){
            System.out.println(((User)user).getUserType().name());
            return ((User)user).getUserType();
        }
        return UserType.NOTLOGGEDIN;
    }
}
