package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.Medicine;
import s2ooadoop.kea.models.User;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.MedicineService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class MedicineController {
    @Autowired
    private MedicineService MS;
    @Autowired
    private Logging logger;

    @GetMapping("/medicines/")
    public String index(Model model, HttpSession session) {
        logger.log("index(Model model): START");

        if (userType(session) != UserType.DOCTOR) {
            logger.log("User access denied");
            return "users/error";
        }

        try {
            model.addAttribute("medicines", MS.getMedicines());
            System.out.println(MS.getMedicines().size());
            logger.log("index(Model model): END");
            return "medicines/index";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("index(Model model): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/medicines/index")
    public String index2(Model model, HttpSession session)
    {
        return index(model, session);
    }

    @GetMapping("/medicines/info/{medicineID}")
    public String showMedicine(@PathVariable int medicineID, Model model, HttpSession session)
    {
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        logger.log("showMedicine(@PathVariable int medicineID, Model model): START");
        try {
            model.addAttribute("medicine", MS.GetMedicine(medicineID));
            logger.log("showMedicine(@PathVariable int medicineID, Model model): END");
            return "medicines/info";
        } catch (SQLException e) {
            logger.log("Error: " + e.getMessage(), 1);
            logger.log("showMedicine(@PathVariable int medicineID, Model model): END");
            return "redirect:/error";
        }
    }

    @GetMapping("/medicines/create")
    public String create(Model model, HttpSession session) {
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        return "medicines/create";
    }
    @PostMapping("/medicines/create")
    public String createMedicine(@RequestParam(value="name", required=true) String medicine, HttpSession session){
        logger.log("createMedicine(): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            MS.CreateMedicine(medicine);
            logger.log("Created medicines", 1);
            logger.log("createMedicine(): END");
            return "redirect:/medicines/";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log("createMedicine(): END");
            return "redirect:/users/error";
        }

    }

    @GetMapping("/medicines/edit/{medicineID}")
    public String edit(@PathVariable int medicineID, Model model, HttpSession session)
    {
        logger.log("edit() : START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("medicine", MS.GetMedicine(medicineID));
            logger.log("edit() : END");
            return "medicines/edit";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("edit() : END");
            return "users/error";
        }
    }

    @PostMapping("/medicines/edit")
    public String editMedicine(@ModelAttribute Medicine medicine, HttpSession session)
    {
        logger.log("editMedicine(): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            MS.EditMedicine(medicine);
        } catch (SQLException e) {
            logger.log("Error " + e.getMessage());
            logger.log("editMedicine(): END");
            return "redirect:/error";
        }
        return "redirect:/medicines/info/" + medicine.getID();
    }

    @GetMapping("/medicines/delete/{medicineID}")
    public String delete(@PathVariable int medicineID, Model model, HttpSession session)
    {
        logger.log("delete(@PathVariable int medicineID): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            model.addAttribute("medicine", MS.GetMedicine(medicineID));
            System.out.println(MS.GetMedicine(medicineID));
            logger.log("delete(@PathVariable int medicineID): END");
            return "medicines/delete";
        } catch (SQLException e) {
            logger.log("An error occurred " + e.getMessage(), 1);
            logger.log("delete(@PathVariable int medicineID): END");
            return "redirect:/error";
        }
    }

    @PostMapping("/medicines/delete")
    public String deleteMedicine(@RequestParam(value="id", required=true) int id, HttpSession session)
    {
        logger.log("deleteMedicine(@ModelAttribute int medicineID): START");
        if(userType(session) != UserType.DOCTOR)
        {
            logger.log("User access denied");
            return "users/error";
        }
        try {
            MS.DeleteMedicine(id);
            logger.log("deleteMedicine(@ModelAttribute int medicineID): END");
            return "redirect:/medicines/";
        } catch (SQLException e) {
            logger.log("deleteMedicine(@ModelAttribute int medicineID): END");
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
