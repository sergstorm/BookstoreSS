package CourseProject.Bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import CourseProject.Bookstore.object.SignUpForm;
import CourseProject.Bookstore.object.User;
import CourseProject.Bookstore.object.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(value="signup")
	public String addUser(Model model){
		model.addAttribute("signUpForm", new SignUpForm());
		return "signup";
	}
	
	 /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
	
	@RequestMapping(value="saveuser",method = RequestMethod.POST)
	public String save (@Valid @ModelAttribute("signUpForm") SignUpForm signupForm, BindingResult binding){
		if (!binding.hasErrors()) {
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				String pwd=signupForm.getPassword();
				BCryptPasswordEncoder bc= new BCryptPasswordEncoder();
				String hashPwd= bc.encode(pwd);
				
				User newUser= new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				if (repository.findByUsername(signupForm.getUsername())==null) {
					repository.save(newUser);
				}
				else {
					binding.rejectValue("username","err.username", "Username already existed");
					return "signup";
				}
			}
			else {
				binding.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
				return "signup";
			}
		}
		else {
			return "signup";
		}
		return "redirect:/login";
	}
}
