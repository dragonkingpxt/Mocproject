package vn.com.fpt.mockproject.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import vn.com.fpt.mockproject.dao.UserDAO;
import vn.com.fpt.mockproject.form.UploadForm;
import vn.com.fpt.mockproject.model.User;
import vn.com.fpt.mockproject.validator.UserValidator;

@Controller
public class HomeController {
	@Autowired
	UserDAO userDAO;
	@Autowired
	UserValidator validator;
	@InitBinder
	   public void initBinder(WebDataBinder dataBinder) {
	       Object target = dataBinder.getTarget();
	       if (target == null) {
	           return;
	       }
	       System.out.println("Target=" + target);
	 
	       if (target.getClass() == User.class) {
	  
	           dataBinder.setValidator(validator);
	       }
	   } 
	@RequestMapping(value = "/login")
	public String showLogin(Model model,Principal principal) {
		if(principal!=null){return "redirect:/data";}
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLogin(Model model,Principal principal, @ModelAttribute("user") User user) {
		System.out.println(userDAO.getSize());
		if (user.getId().equals("") || user.getId().indexOf(" ") != -1) {
			System.out.println("user name:" + user.getId());
			model.addAttribute("user_error", "Username must not empty or has space");
			return showLogin(model,principal);
		} else if (user.getPassword().equals("") || user.getPassword().indexOf(" ") != -1) {
			System.out.println("not pass");
			model.addAttribute("pass_error", "Password must not empty or has space");
			return showLogin(model,principal);
		}else{
		User getuser = userDAO.getUser(user.getId());
		
		if (getuser == null) {
			System.out.println("not user");
			model.addAttribute("message", "User ko ton tai");
			return showLogin(model,principal);
		}else
		if (!getuser.getPassword().equals(user.getPassword())) {
			System.out.println("mk kho dung");
			System.out.println(getuser.getPassword());
			model.addAttribute("message", "Password is incorrect");
			return showLogin(model,principal);
		}
		System.out.println("true");
		return "redirect:/data";
		}
	}
}
