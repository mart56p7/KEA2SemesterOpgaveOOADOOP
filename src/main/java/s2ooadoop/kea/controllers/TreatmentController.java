package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.Treatment;
import s2ooadoop.kea.models.User;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.MedicineService;
import s2ooadoop.kea.services.TreatmentService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class TreatmentController {
    @Autowired
    private TreatmentService TS;

    @Autowired
    private MedicineService MS;

    @Autowired
    private Logging logger;

    @GetMapping("/treatments/")
    public String index(Model model, HttpSession session) {
        logger.log("index(Model model): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("treatments", TS.getTreatments());
            logger.log("index(Model model): END");
            return "treatments/index";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("index(Model model): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/treatments/index")
    public String index2(Model model, HttpSession session)
    {
        return index(model, session);
    }

    @GetMapping("/treatments/info/{treatmentID}")
    public String showTreatment(@PathVariable int treatmentID, Model model, HttpSession session)
    {
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        logger.log("showTreatment(@PathVariable int treatmentID, Model model): START");
        try {
            model.addAttribute("treatment", TS.getTreatment(treatmentID));
            logger.log("showTreatment(@PathVariable int treatmentID, Model model): END");
            return "treatments/info";
        } catch (SQLException e) {
            logger.log("Error: " + e.getMessage(), 1);
            logger.log("showTreatment(@PathVariable int treatmentID, Model model): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/treatments/delete/{treatmentID}")
    public String delete(@PathVariable int treatmentID, Model model, HttpSession session)
    {
        logger.log("delete(@PathVariable int treatmentID): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("treatment", TS.getTreatment(treatmentID));
            logger.log("delete(@PathVariable int treatmentID): END");
            return "treatments/delete";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("delete(@PathVariable int treatmentID): END");
            return "redirect:/error";
        }
    }

    @PostMapping("/treatments/delete")
    public String deleteTreatment(@RequestParam(value="id", required=true) int id, HttpSession session)
    {
        logger.log("deleteTreatment(@ModelAttribute int treatmentID): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            TS.deleteTreatment(id);
            logger.log("deleteTreatment(@ModelAttribute int treatmentID): END");
            return "redirect:/treatments/";
        } catch (SQLException e) {
            logger.log("deleteTreatment(@ModelAttribute int treatmentID): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/treatments/create")
    public String create(Model model, HttpSession session) {
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }

        try {
            model.addAttribute("medicines", MS.getMedicines());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "treatments/create";
    }

    @PostMapping("/treatments/create")
    public String createTreatment(@RequestParam(value="name", required=true) String name,
                             @RequestParam(value="note", required=false) String note,
                             @RequestParam(value="medicine_ids", required=false) int[] medicine_ids, HttpSession session){
        logger.log("createTreatment(): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            TS.createTreatment(name, note, medicine_ids);
            logger.log("Created treatment", 1);
            logger.log("createTreatment(): END");
            return "redirect:/treatments/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createTreatment(): END");
            return "redirect:/users/error";
        }

    }



    @GetMapping("/treatments/edit/{treatmentID}")
    public String edit(@PathVariable int treatmentID, Model model, HttpSession session)
    {
        logger.log("edit(@PathVariable int treatmentID, Model model) : START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            Treatment t = TS.getTreatment(treatmentID);
            model.addAttribute("medicines", MS.getMedicines());
            model.addAttribute("treatment", TS.getTreatment(treatmentID));
            logger.log("edit(@PathVariable int treatmentID, Model model) : END");
            return "treatments/edit";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("edit(@PathVariable int treatmentID, Model model) : END");
            return "error";
        }
    }

    @PostMapping("/treatments/edit")
    public String editTreatment(
            @RequestParam(value="id", required=true) int ID,
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="note", required=false) String note,
            @RequestParam(value="medicine_ids", required=false) int[] medicine_ids,
            HttpSession session
    )
    {
        logger.log("editTreatment(): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            TS.editTreatment(ID, name, note, medicine_ids);
            logger.log("Treatment with " + ID + " edited");

        } catch (SQLException e) {
            logger.log("Error " + e.getMessage());
            logger.log("editTreatment(): END");
            return "redirect:error";
        }
        return "redirect:/treatments/info/" + ID;
    }


    @ModelAttribute("userType")
    public UserType userType(HttpSession session){
        //0 = Not logged in
        //1 = Secretary
        //2 = Doctor
        Object user = session.getAttribute("user");
        if(user instanceof User && user != null){
            return ((User)user).getUserType();
        }
        return UserType.NOTLOGGEDIN;
    }
}
