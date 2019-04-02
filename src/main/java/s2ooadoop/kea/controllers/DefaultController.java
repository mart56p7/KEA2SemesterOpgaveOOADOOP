package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.Logging;

import javax.servlet.http.HttpSession;

@Controller
public class DefaultController {
    @Autowired
    Logging logger;

    @GetMapping("/")
    public String root(HttpSession session){
        //If the user hasnt logged in, we want to validate the user
        if(userType(session) == UserType.NOTLOGGEDIN){
            return "users/validateUser";
        }
        //Else we want to search for a patient
        return "redirect:patients/findPatient";
    }

    @ModelAttribute("userType")
    public UserType userType(HttpSession session){
        //0 = Not logged in
        //1 = Secretary
        //2 = Doctor
        return UserType.NOTLOGGEDIN;
    }
}
