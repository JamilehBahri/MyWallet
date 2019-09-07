package ir.phgint.controller;

import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.service.UserServices;
import ir.phgint.validation.UserProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserProfileValidator userProfileValidator;
    @Autowired
    private UserServices userServices;


    private MessageSource messageSource;


    public UserProfileValidator getUserProfileValidator() {
        return userProfileValidator;
    }

    public void setUserProfileValidator(UserProfileValidator userProfileValidator) {
        this.userProfileValidator = userProfileValidator;
    }

    public UserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }


    @Autowired
    public RegistrationController(MessageSource messageSource, UserServices userServices
                    , UserProfileValidator userProfileValidator) {
        this.messageSource = messageSource;
        this.userServices = userServices;
        this.userProfileValidator = userProfileValidator;
    }

    public RegistrationController() {
    }

    @RequestMapping(method = RequestMethod.GET )
    public String registration(Model model) {
        model.addAttribute("userForm", new UserProfileDto());

        return "registration";
    }

    @RequestMapping(method =  RequestMethod.POST)
    public String newRegistration(@ModelAttribute("userForm") @Valid UserProfileDto userProfileDto,  BindingResult bindingResult ,HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        else {
            userServices.saveUser(userProfileDto);
            return "index";
        }

    }


}
