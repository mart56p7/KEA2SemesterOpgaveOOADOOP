package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.Illness;
import s2ooadoop.kea.models.Treatment;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.repositories.IllnessRepository;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.IllnessService;
import s2ooadoop.kea.services.TreatmentService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class IllnessController {
    @Autowired
    IllnessService IS;

    @Autowired
    TreatmentService TS;

    @Autowired
    private Logging logger;

    @GetMapping("/illnesses/")
    public String index(Model model, HttpSession session) {
        logger.log("index(Model model): START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("illnesses", IS.getIllnesses());
            logger.log("index(Model model): END");
            return "illnesses/index";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("index(Model model): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/illnesses/index")
    public String index2(Model model, HttpSession session) {
        return index(model, session);
    }

    @GetMapping("/illnesses/info/{illnessID}")
    public String showIllness(@PathVariable int illnessID, Model model, HttpSession session) {
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        logger.log("showIllness(@PathVariable int illnessID, Model model): START");
        try {
            model.addAttribute("illness", IS.getIllness(illnessID));
            logger.log("showIllness(@PathVariable int illnessID, Model model): END");
            return "illnesses/info";
        } catch (SQLException e) {
            logger.log("Error: " + e.getMessage(), 1);
            logger.log("showIllness(@PathVariable int illnessID, Model model): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/illnesses/delete/{illnessID}")
    public String delete(@PathVariable int illnessID, Model model, HttpSession session) {
        logger.log("delete(@PathVariable int illnessID): START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("illness", IS.getIllness(illnessID));
            logger.log("delete(@PathVariable int illnessID): END");
            return "illnesses/delete";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("delete(@PathVariable int illnessID): END");
            return "redirect:/error";
        }
    }

    @PostMapping("/illnesses/delete")
    public String deleteIllness(@RequestParam(value="id", required=true) int id, HttpSession session) {
        logger.log("deleteIllness(@ModelAttribute int illnessID): START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            IS.DeleteIllness(id);
            logger.log("deleteIllness(@ModelAttribute int illnessID): END");
            return "illnesses/";
        } catch (SQLException e) {
            logger.log("deleteIllness(@ModelAttribute int illnessID): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/illnesses/create")
    public String create(Model model, HttpSession session) {
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("treatments", TS.GetTreatments());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "illnesses/create";
    }

    @PostMapping("/illnesses/create")
    public String createIllness(@RequestParam(value="name", required=true) String name,
                             @RequestParam(value="treatment_ids", required=false) int[] treatment_ids, HttpSession session){
        logger.log("createIllness(): START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            IS.CreateIllness(name, treatment_ids);
            logger.log("Created illness", 1);
            logger.log("createIllness(): END");
            return "redirect:/illnesses/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createIllness(): END");
            return "redirect:/users/error";
        }

    }



    @GetMapping("/illnesses/edit/{illnessID}")
    public String edit(@PathVariable int illnessID, Model model, HttpSession session) {
        logger.log("edit(@PathVariable int illnessID, Model model) : START");
        if(userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }
        try {
//------------------------ ADD TREATMENT ------------------ ////
            model.addAttribute("illness", IS.getIllness(illnessID));
            model.addAttribute("treatments", TS.GetTreatments());
            logger.log("edit(@PathVariable int illnessID, Model model) : END");
            return "illnesses/edit";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("edit(@PathVariable int illnessID, Model model) : END");
            return "error";
        }
    }

    @PostMapping("/illnesses/edit")
    public String editIllness(
            @RequestParam(value="id", required=true) int ID,
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="treatment_ids", required=false) int[] treatment_ids,
            HttpSession session
    )
    {
        logger.log("editIllness(): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            IS.EditIllness(ID, name, treatment_ids);
            logger.log("Illness with " + ID + " edited");

        } catch (SQLException e) {
            logger.log("Error " + e.getMessage());
            logger.log("editIllness(): END");
            return "redirect:error";
        }
        return "redirect:/illnesses/info/" + ID;
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
