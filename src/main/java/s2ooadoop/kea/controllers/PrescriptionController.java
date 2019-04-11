package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.Prescription;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.MedicineService;
import s2ooadoop.kea.services.PatientService;
import s2ooadoop.kea.services.PrescriptionService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PrescriptionController {

    @Autowired
    private Logging logger;
    @Autowired
    private PatientService PS;
    @Autowired
    private MedicineService MS;
    @Autowired
    private PrescriptionService PRS;

    @GetMapping("/prescriptions/{patientID}")
    public String index(@PathVariable int patientID, Model model, HttpSession session) {
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("patient", PS.getPatient(patientID));
            model.addAttribute("prescriptions", PRS.GetPrescriptions(patientID,false));
            System.out.println(PRS.GetPrescriptions(patientID,false));
            System.out.println(PS.getPatient(patientID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "prescriptions/index";
    }

    @GetMapping("/prescriptions/create/{patientID}")
    public String create(@PathVariable int patientID, Model model, HttpSession session) {
        logger.log("create: Start");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            logger.log("create: End");
            return "users/error";
        }
        try {
            model.addAttribute("patient", PS.getPatient(patientID));
            model.addAttribute("medicines", MS.getMedicines());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.log("create: End");
        return "prescriptions/create";
    }

    @PostMapping("/prescriptions/create")
    public String createPrescription(@RequestParam(value="patientid", required=true) int patientid,
                                 @RequestParam(value="description", required=true) String description,
                                 @RequestParam(value="medicineid", required=true) int medicineid,
                                 @RequestParam(value="startdate", required=true) String startdate,
                                 @RequestParam(value="enddate", required=true) String enddate,
                                 HttpSession session, Model model){
        logger.log("createPrescriptions(): START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM yyyy");
        try {
            Date startdateformatted = new Date(format.parse(startdate).getTime()+1000*60*60*7);
            Date enddateformatted = new Date(format.parse(enddate).getTime()+1000*60*60*7);
            PRS.CreatePrescription(new Prescription(PS.getPatient(patientid),description, MS.GetMedicine(medicineid), startdateformatted,enddateformatted));
            logger.log("Created prescriptions", 1);
            logger.log("createPrescription(): END");
            return "redirect:/patients/info/" + patientid;            } catch (ParseException e) {
            logger.log("Error parsing date format");
            return "redirect:error";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createPrescriptions(): END");
            return "redirect:error";
        } catch (Exception e){
            logger.log("Prescription request denied");
            model.addAttribute("errmsg", new String(e.getMessage()));
            return "/prescriptions/error";
        }
    }
    @GetMapping("/prescriptions/info/{prescriptionID}")
    public String info(@PathVariable int prescriptionID, Model model, HttpSession session) {
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            Prescription prescription = PRS.GetPrescription(prescriptionID);
            model.addAttribute("prescription", prescription);
            model.addAttribute("patient", PS.getPatient(prescription.getPatient().getID()));
         } catch (SQLException e) {
            e.printStackTrace();
        }
        return "prescriptions/info";
    }

    @GetMapping("/prescriptions/delete/{patientID}/{prescriptionID}")
    public String delete(@PathVariable int patientID, @PathVariable int prescriptionID, Model model, HttpSession session) {
        logger.log("delete(): START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("prescription", PRS.GetPrescription(prescriptionID));
            model.addAttribute("patient", PS.getPatient(patientID));
            logger.log("delete(): END");
            return "prescriptions/delete";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("delete(): END");
            return "users/error";
        }
    }

    @PostMapping("/prescriptions/delete")
    public String deletePrescription(@RequestParam(value="patientID", required=true) int patient_id,
                                 @RequestParam(value="prescriptionID", required=true) int prescriptionID,
                                 HttpSession session) {
        logger.log("deletePrescription(): START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            PRS.DeletePrescription(prescriptionID);
            logger.log("deletePrescription(): END");
            return "redirect:/prescriptions/" + patient_id;
        } catch (SQLException e) {
            logger.log("deletePrescription(): error = " + e.getMessage());
            logger.log("deletePrescription(): END");
            return "redirect:error";
        }
    }


    @GetMapping("/prescriptions/edit/{patientID}/{prescriptionID}")
    public String edit(@PathVariable int patientID, @PathVariable int prescriptionID, Model model, HttpSession session) {
        logger.log("edit() : START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("patient", PS.getPatient(patientID));
            model.addAttribute("prescription", PRS.GetPrescription(prescriptionID));
            model.addAttribute("medicines", MS.getMedicines());
            logger.log("edit() : END");
            return "prescriptions/edit";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("edit() : END");
            return "error";
        }
    }

    @PostMapping("/prescriptions/edit")
    public String editPrescriptions(
            @RequestParam(value="prescriptionid", required=true) int prescriptionid,
            @RequestParam(value="patientid", required=true) int patientid,
            @RequestParam(value="description", required=true) String description,
            @RequestParam(value="medicineid", required=true) int medicineid,
            @RequestParam(value="startdate", required=true) String startdate,
            @RequestParam(value="enddate", required=true) String enddate,
            HttpSession session) {
        logger.log("editPrescription(): START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM yyyy");
            try {
                Date startdateformatted = new Date(format.parse(startdate).getTime()+1000*60*60*7);
                Date enddateformatted = new Date(format.parse(enddate).getTime()+1000*60*60*7);
                PRS.EditPrescription(new Prescription(prescriptionid,PS.getPatient(patientid),description,MS.GetMedicine(medicineid),startdateformatted,enddateformatted));
                logger.log("Edited prescription", 1);
            } catch (ParseException e) {
                logger.log("Error parsing date format");
                return "redirect:error";
            }
        } catch (SQLException e) {
            logger.log("Error " + e.getMessage());
            logger.log("editPrescription(): END");
            return "redirect:/users/error";
        }
        return "redirect:/prescriptions/info/" + patientid;
    }



    @ModelAttribute("userType")
    public UserType userType(HttpSession session) {
        return UserType.DOCTOR;
    }


}