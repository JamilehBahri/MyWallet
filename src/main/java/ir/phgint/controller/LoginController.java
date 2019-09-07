package ir.phgint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    public LoginController() {
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

 // get by spring security
//    @RequestMapping(method =  RequestMethod.POST)
//    public String login(@ModelAttribute("userLoginForm") UserProfileDto userProfileDto, HttpServletRequest request) {
//
//          UserProfileDto user= userServices.findByUsername(userProfileDto.getUsername());
//            if(user != null)
//            {
//                if(user.getRole().equals("Admin") && user.getPassword().equals(userProfileDto.getPassword()))
//                    return "redirect:/index";
//                else if(user.getRole().equals("User"))
//                    return "index";
//            }
//            return "login";
//
//    }

}
