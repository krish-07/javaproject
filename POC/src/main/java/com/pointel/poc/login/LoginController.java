package com.pointel.poc.login;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pointel.poc.db.entity.Login;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping(value = { "Login", "/" })
	public ModelAndView login() {
		return new ModelAndView("Login");
	}

	@PostMapping("/ValidateUser")
	public String validateUser(@RequestParam("identityName") String identityName,
			@RequestParam("watchWord") String watchWord, HttpSession session) {
		Login login = loginService.validateUser(identityName, watchWord);
		if (login.getComment().equals("555")) {
			session.setAttribute(LoginConstants.POCUSERID.toString(), login.getUserId());
			session.setAttribute(LoginConstants.POCUSERROLE.toString(), login.getRole());
			//session.setAttribute("POCUSERID", login.getUserId());
		}
		return login.getComment();
	}

	@GetMapping("/Logout")
	public void logOut(HttpSession session, HttpServletResponse response) {

		try {
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("Login");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
