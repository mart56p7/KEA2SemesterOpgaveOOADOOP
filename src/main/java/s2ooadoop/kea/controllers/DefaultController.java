package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.PatientService;
import s2ooadoop.kea.services.UserService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 *  The default controller requires
 *  UserService and templates
 *  PatientService and templates
 *
 *  Custom modification are made in the template /menu that requires
 *
 * */

@Controller
public class DefaultController {
    @Autowired
    Logging logger;
    @Autowired
    UserService us;
    @Autowired
    PatientService ps;

    @GetMapping("/")
    public String root(HttpSession session){
        //If the user hasnt logged in, we want to validate the user
        if(userType(session) == UserType.NOTLOGGEDIN){
            return "validateUser";
        }
        //Else we want to search for a patient
        return "/patients/findPatient";
    }

    @PostMapping("/")
    public String validateUser(@RequestParam(value="username", required=true) String username, @RequestParam(value="password", required=true) String password,Model model, HttpSession session)  {
        try {
            //Returns a user object on success or null on failure
            session.setAttribute("user", us.ValidateUser(username, password));
            //If the user hasnt logged in, we want to validate the user
            if(userType(session) == UserType.NOTLOGGEDIN){
                return "validateUser";
            }
            //If we dont specificly update the userType, it takes 1 more load of the page for it to update..
            model.addAttribute("userType", userType(session));
            return "/patients/findPatient";
        } catch (SQLException e) {
            logger.log("Error: " + e.getMessage());
            return "error";
        }


    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        logger.log("logout(HttpSession session): START");
        session.removeAttribute("user");
        logger.log("logout(HttpSession session): END");
        return "redirect:/";
    }

    @GetMapping("/help")
    public String help(HttpSession session){
        return "help";
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
