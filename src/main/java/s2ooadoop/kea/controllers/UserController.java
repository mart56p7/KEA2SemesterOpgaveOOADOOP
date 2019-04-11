package s2ooadoop.kea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s2ooadoop.kea.models.User;
import s2ooadoop.kea.models.UserType;
import s2ooadoop.kea.services.Logging;
import s2ooadoop.kea.services.UserService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * CRUD for Users + Get a single target
 * */

@Controller
public class UserController {
	@Autowired
	private UserService US;
	@Autowired
	private Logging logger;
	
	@GetMapping("/users/")
	public String index(Model model, HttpSession session) {
		logger.log("index(Model model): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("users", US.getUsers());
			logger.log("index(Model model): END");
			return "users/index";
		} catch (SQLException e) {
			logger.log("An error occurred " + e.getMessage(), 1);
			logger.log("index(Model model): END");
			return "users/error";
		}
	}

	@GetMapping("/users/index")
	public String index2(Model model, HttpSession session)
	{
		logger.log("index2(Model model): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try
		{
			model.addAttribute("users", US.getUsers());
			logger.log("index2(Model model): END");
			return "users/index";
		} catch (SQLException e) {
			logger.log("An error occurred " + e.getMessage(), 1);
			logger.log("index2(Model model): END");
			return "users/error";
		}
	}

	@GetMapping("/users/create")
	public String create(HttpSession session) {
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		return "users/create";
	}

	@PostMapping("/users/create")
	public String createUser(@RequestParam(value="Username", required=true) String Username,
							 @RequestParam(value="Password", required=true) String Password,
							 @RequestParam(value="userType", required=true) int userType, HttpSession session){
		logger.log("createUser(@ModelAttribute User user): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			US.createUser(new User(Username, Password, userType));
			logger.log("Created user", 1);
			logger.log("createUser(@ModelAttribute User user): END");
			return "redirect:/users/";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.log("createUser(@ModelAttribute User user): END");
			return "redirect:/users/error";
		}

	}

	@GetMapping("/users/edit/{userID}")
	public String edit(@PathVariable int userID, Model model, HttpSession session)
	{
		logger.log("edit(@PathVariable int userID, Model model) : START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("user", US.getUser(userID));
			logger.log("edit(@PathVariable int userID, Model model) : END");
			return "users/edit";
		} catch (SQLException e) {
			logger.log("An error occurred " + e.getMessage(), 1);
			logger.log("edit(@PathVariable int userID, Model model) : END");
			return "users/error";
		}
	}

	@PostMapping("/users/edit")
	public String editUser(
			@RequestParam(value="userID", required=true) int userID,
			@RequestParam(value="newUsername", required=true) String newUsername,
			@RequestParam(value="oldPassword", required=true) String oldPassword,
			@RequestParam(value="newPassword", required=true) String newPassword,
			@RequestParam(value="userType", required=true) int userType,
			HttpSession session
	)
	{
		logger.log("editUser(@ModelAttribute User user, @ModelAttribute int userID): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			User user = US.validateUser(US.getUser(userID).getUsername(), oldPassword);

			if(user != null)
			{
				logger.log("User validation success new username: " + newUsername, 1);
				US.editUser(new User(userID, newUsername, newPassword, UserType.getUserType(userType)));
				logger.log("editUser(@ModelAttribute User user, @ModelAttribute int userID): END");
				return "redirect:/users/info/" + Integer.toString(userID);
			}
			else
			{
				logger.log("User validation failed username: " + US.getUser(userID).getUsername(), 1);
				logger.log("editUser(@ModelAttribute User user, @ModelAttribute int userID): END");
				return "redirect:/users/edit/" + Integer.toString(userID);
			}

		} catch (SQLException e) {
			logger.log("Error " + e.getMessage());
			logger.log("editUser(@ModelAttribute User user, @ModelAttribute int userID): END");
			return "redirect:/users/error";
		}

	}

	@GetMapping("/users/delete/{userID}")
	public String delete(@PathVariable int userID, Model model, HttpSession session)
	{
		logger.log("delete(@PathVariable int userID): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			model.addAttribute("user", US.getUser(userID));
			logger.log("delete(@PathVariable int userID): END");
			return "users/delete";
		} catch (SQLException e) {
			logger.log("An error occurred " + e.getMessage(), 1);
			logger.log("delete(@PathVariable int userID): END");
			return "users/error";
		}
	}

	@PostMapping("/users/delete")
	public String deleteUser(@RequestParam(value="id", required=true) int id, HttpSession session)
	{
		logger.log("deleteUser(@ModelAttribute int userID): START");
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		try {
			US.deleteUser(id);
			logger.log("deleteUser(@ModelAttribute int userID): END");
			return "redirect:/users/";
		} catch (SQLException e) {
			logger.log("deleteUser(@ModelAttribute int userID): END");
			return "redirect:/users/error";
		}
	}

	@GetMapping("/users/login")
	public String login() {
		return "users/login";
	}
	// Getmapping <-- /slut -->


	@GetMapping("/users/info/{userID}")
	public String showUser(@PathVariable int userID, Model model, HttpSession session)
	{
		if(userType(session) != UserType.DOCTOR)
		{
			logger.log("User access denied");
			return "users/error";
		}
		logger.log("showUser(@RequestParam(value=\"userID\", required=true) int userID, Model model): START");
		try {
			model.addAttribute("user", US.getUser(userID));
			logger.log("showUser(@RequestParam(value=\"userID\", required=true) int userID, Model model): END");
			return "users/info";
		} catch (SQLException e) {
			logger.log("Error: " + e.getMessage(), 1);
			logger.log("showUser(@RequestParam(value=\"userID\", required=true) int userID, Model model): END");
			return "redirect:/users/error";
		}
	}

	@GetMapping("users/logout")
	public String logout(HttpSession session)
	{
		logger.log("logout(HttpSession session): START");
		session.removeAttribute("user");
		session.removeAttribute("isAdmin");
		logger.log("logout(HttpSession session): END");
		return "redirect:/";
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