package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.Consultation;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.ConsultationService;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.PatientService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@Controller
public class ConsultationController {

	@Autowired
	private ConsultationService CS;
	@Autowired
	private PatientService PS;

	@Autowired
	private Logging logger;

	@GetMapping("/consultations/{patientID}")
	public String index(@PathVariable int patientID, Model model, HttpSession session) {
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("patient", PS.getPatient(patientID));
			model.addAttribute("consultations", CS.getConsultations(patientID));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "consultations/index";
	}

	@GetMapping("/consultations/info/{consultationID}")
	public String info(@PathVariable int consultationID, Model model, HttpSession session) {
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			Consultation c = CS.getConsultation(consultationID);
			model.addAttribute("consultation", c);
			model.addAttribute("patient", PS.getPatient(c.getPatient().getID()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "consultations/info";
	}

	@GetMapping("/consultations/create/{patientID}")
	public String create(@PathVariable int patientID, Model model, HttpSession session) {
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("patient", PS.getPatient(patientID));
			model.addAttribute("consultations", CS.getActiveConsultations(patientID));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "consultations/create";
	}

	@PostMapping("/consultations/create")
	public String createConsultation(@RequestParam(value="description", required=true) String description,
							 @RequestParam(value="conclusion", required=true) String conclusion,
							 @RequestParam(value="date", required=true) String date,
							 @RequestParam(value="patientid", required=true) int patientid,
							 HttpSession session){
		logger.log("createConsultation(): START");

		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM yyyy");
			format.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
			try {
				Date dateformatted = new Date(format.parse ( date ).getTime()+1000*60*60*7);
				CS.CreateConsultation(new Consultation(PS.getPatient(patientid), description, conclusion, dateformatted));
				logger.log("Created consultation", 1);
			} catch (ParseException e) {
				logger.log("Error parsing date format");
				return "redirect:error";
			}

			logger.log("createConsultation(): END");
			return "redirect:/patients/info/" + patientid;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.log("createConsultation(): END");
			return "redirect:error";
		}
	}


	@GetMapping("/consultations/delete/{consultationID}")
	public String delete(@PathVariable int consultationID, Model model, HttpSession session)
	{
		logger.log("delete(): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			Consultation c =  CS.getConsultation(consultationID);
			model.addAttribute("consultation", c);
			model.addAttribute("patient", c.getPatient());
			logger.log("delete(): END");
			return "consultations/delete";
		} catch (SQLException e) {
			logger.log("An error occurred " + e.getMessage(), 1);
			logger.log("delete(): END");
			return "users/error";
		}
	}

	@PostMapping("/consultations/delete")
	public String deleteConsultation(@RequestParam(value="patient_id", required=true) int patient_id,
									 @RequestParam(value="consultation_id", required=true) int consultation_id, HttpSession session)
	{
		logger.log("deleteConsultation(): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			CS.DeleteConsultation(consultation_id);
			logger.log("deleteConsultation(): END");
			return "redirect:/consultations/" + patient_id;
		} catch (SQLException e) {
			logger.log("deleteConsultation(): error = " + e.getMessage());
			logger.log("deleteConsultation(): END");
			return "redirect:error";
		}
	}


	@GetMapping("/consultations/edit/{consultationID}")
	public String edit(@PathVariable int consultationID, Model model, HttpSession session)
	{
		logger.log("edit() : START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			Consultation c = CS.getConsultation(consultationID);
			model.addAttribute("consultation", c);
			model.addAttribute("patient", c.getPatient());

			logger.log("edit() : END");
			return "consultations/edit";
		} catch (SQLException e) {
			logger.log("An error occurred " + e.getMessage(), 1);
			logger.log("edit() : END");
			return "error";
		}
	}

	@PostMapping("/consultations/edit")
	public String editConsultation(
			@RequestParam(value="consultationid", required=true) int consultationid,
			@RequestParam(value="description", required=true) String description,
			@RequestParam(value="conclusion", required=true) String conclusion,
			@RequestParam(value="date", required=true) String date,
			@RequestParam(value="patientid", required=true) int patientid,
			HttpSession session
	)
	{
		logger.log("editConsultation(): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM yyyy");
			try {
				Date dateformatted = new Date(format.parse ( date ).getTime()+1000*60*60*7);
				CS.EditConsultation(new Consultation(consultationid, PS.getPatient(patientid), description, conclusion, dateformatted));
				logger.log("Edited consultation", 1);
			} catch (ParseException e) {
				logger.log("Error parsing date format");
				return "redirect:error";
			}


		} catch (SQLException e) {
			logger.log("Error " + e.getMessage());
			logger.log("editConsultation(): END");
			return "redirect:/users/error";
		}
		return "redirect:/consultations/info/" + consultationid;
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