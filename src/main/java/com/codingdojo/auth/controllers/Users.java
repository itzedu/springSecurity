package com.codingdojo.auth.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.auth.models.User;
import com.codingdojo.auth.services.UserService;

@Controller
public class Users {
    private UserService userService;
    
    public Users(UserService userService) {
    	this.userService = userService;
    }
	
	@RequestMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user") User user) {
		return "registrationPage.jsp";
	}
	
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "registration.jsp";
        }
        userService.saveWithUserRole(user);
//        securityService.autoLogin(user.getUsername(), user.getPasswordConfirmation());
//        session.setAttribute("user", currentUser.getId());
        return "redirect:/login";
    }
    	
	@RequestMapping("/login")
	public String login() {
		return "loginPage.jsp";
	}

	
	@RequestMapping(value = {"/", "/home"})
	public String home() {
		return "homePage.jsp";
	}
	
	@PostMapping("/logout")
	public String logout() {
//		HttpServletRequest.logout();
		return "redirect:/login";
	}
}
