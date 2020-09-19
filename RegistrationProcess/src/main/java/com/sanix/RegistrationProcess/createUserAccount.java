package com.sanix.RegistrationProcess;


import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class createUserAccount {

    @Controller
    public class showRegistration {

        @GetMapping("user/registration")
        public String showRegistration(WebRequest request, Model model){
            UserDto userDto=new UserDto();
            model.addAttribute("user", userDto);
            return "registration";
        }
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid UserDto userDto,
             HttpServletRequest request, Errors errors){
        try{
            User registered=userService.registerNewUserAccount(userDto);

        }catch(UserAlreadyExistException uaeEx){//Check the account already exists
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("successRegister", "user", userDto);
    }
}
