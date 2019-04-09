package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.Diagnose;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.DiagnoseService;
import s2ooadoop.kea.services.IllnessService;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.PatientService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DiagnoseController {

	@Autowired
	private DiagnoseService DS;

	@Autowired
	private PatientService PS;

	@Autowired
	private IllnessService IS;

	@Autowired
	private Logging logger;

	@GetMapping("/diagnoses/{patientID}")
	public String index(@PathVariable int patientID, Model model, HttpSession session) {
		if(userType(session) != UserType.DOCTOR) {
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("patient", PS.getPatient(patientID));
			model.addAttribute("diagnoses", DS.getDiagnoses(patientID));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "diagnoses/index";
	}

	@GetMapping("/diagnoses/info/{diagnoseID}")
	public String info(@PathVariable int diagnoseID, Model model, HttpSession session) {
		if(userType(session) != UserType.DOCTOR) {
			logger.log("User access denied");
			return "users/error";
		}
		try {
			Diagnose diagnose = DS.getDiagnose(diagnoseID);
			model.addAttribute("diagnose", diagnose);
			model.addAttribute("patient", PS.getPatient(diagnose.getPatient().getID()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "diagnoses/info";
	}

	@GetMapping("/diagnoses/create/{patientID}")
	public String create(@PathVariable int patientID, Model model, HttpSession session) {
		if(userType(session) != UserType.DOCTOR) {
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("patient", PS.getPatient(patientID));
			model.addAttribute("diagnoses", DS.getActiveDiagnoses(patientID));
			model.addAttribute("illnesses", IS.getIllnesses());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "diagnoses/create";
	}

	@PostMapping("/diagnoses/create")
	public String createDiagnose(@RequestParam(value="patientid", required=true) int patientid,
								 @RequestParam(value="illnessid", required=true) int illnessid,
								 @RequestParam(value="note", required=true) String note,
								 @RequestParam(value="date", required=true) String date,
								 HttpSession session){
		logger.log("createDiagnose(): START");
		if(userType(session) != UserType.DOCTOR) {
			logger.log("User access denied");
			return "users/error";
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM yyyy");
			try {
				Date dateformatted = format.parse(date);
				DS.createDiagnose(new Diagnose(PS.getPatient(patientid), IS.getIllness(illnessid), note, dateformatted));
				logger.log("Created diagnoses", 1);
			} catch (ParseException e) {
				logger.log("Error parsing date format");
				return "redirect:error";
			}

			logger.log("createDiagnose(): END");
			return "redirect:/patients/info/" + patientid;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.log("createDiagnose(): END");
			return "redirect:error";
		}
	}


	@GetMapping("/diagnoses/delete/{patientID}/{diagnoseID}")
	public String delete(@PathVariable int patientID, @PathVariable int diagnoseID, Model model, HttpSession session) {
		logger.log("delete(): START");
		if(userType(session) != UserType.DOCTOR) {
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("diagnose", DS.getDiagnose(diagnoseID));
			model.addAttribute("patient", PS.getPatient(patientID));
			logger.log("delete(): END");
			return "diagnoses/delete";
		} catch (SQLException e) {
			logger.log("An error occurred " + e.getMessage(), 1);
			logger.log("delete(): END");
			return "users/error";
		}
	}

	@PostMapping("/diagnoses/delete")
	public String deleteDiagnose(@RequestParam(value="patient_id", required=true) int patient_id,
								 @RequestParam(value="diagnose_id", required=true) int diagnose_id,
								 HttpSession session) {
		logger.log("deleteDiagnose(): START");
		if(userType(session) != UserType.DOCTOR) {
			logger.log("User access denied");
			return "users/error";
		}
		try {
			DS.deleteDiagnose(diagnose_id);
			logger.log("deleteDiagnose(): END");
			return "redirect:/diagnoses/" + patient_id;
		} catch (SQLException e) {
			logger.log("deleteDiagnose(): error = " + e.getMessage());
			logger.log("deleteDiagnose(): END");
			return "redirect:error";
		}
	}


	@GetMapping("/diagnoses/edit/{patientID}/{diagnoseID}")
	public String edit(@PathVariable int patientID, @PathVariable int diagnoseID, Model model, HttpSession session) {
		logger.log("edit() : START");
		if(userType(session) != UserType.DOCTOR) {
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("patient", PS.getPatient(patientID));
			model.addAttribute("diagnose", DS.getDiagnose(diagnoseID));
			model.addAttribute("illnesses", IS.getIllnesses());
			logger.log("edit() : END");
			return "diagnoses/edit";
		} catch (SQLException e) {
			logger.log("An error occurred " + e.getMessage(), 1);
			logger.log("edit() : END");
			return "error";
		}
	}

	@PostMapping("/diagnoses/edit")
	public String editDiagnoses(
			@RequestParam(value="diagnoseid", required=true) int diagnoseid,
			@RequestParam(value="patientid", required=true) int patientid,
			@RequestParam(value="illnessid", required=true) int illnessid,
			@RequestParam(value="note", required=true) String note,
			@RequestParam(value="date", required=true) String date,
			HttpSession session) {
		logger.log("editDiagnose(): START");
		if(userType(session) != UserType.DOCTOR) {
			logger.log("User access denied");
			return "users/error";
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM yyyy");
			try {
				Date dateformatted = format.parse ( date );
				DS.editDiagnose(new Diagnose(diagnoseid, PS.getPatient(patientid), IS.getIllness(illnessid), note, dateformatted));
				logger.log("Edited diagnose", 1);
			} catch (ParseException e) {
				logger.log("Error parsing date format");
				return "redirect:error";
			}
		} catch (SQLException e) {
			logger.log("Error " + e.getMessage());
			logger.log("editDiagnose(): END");
			return "redirect:/users/error";
		}
        return "redirect:/diagnoses/info/" + diagnoseid;
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