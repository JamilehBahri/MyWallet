package ir.phgint.controller;

import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.domain.dto.UserProfileJsonResponce;
import ir.phgint.service.UserServices;
import ir.phgint.service.WalletInvoicesServiceImpl;
import ir.phgint.validation.UserProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserProfileValidator userProfileValidator;
    @Autowired
    private UserServices userServices;
    @Autowired
    private WalletInvoicesServiceImpl walletInvoicesService;

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
    public UserController(MessageSource messageSource, UserServices userServices
                    , UserProfileValidator userProfileValidator) {
        this.messageSource = messageSource;
        this.userServices = userServices;
        this.userProfileValidator = userProfileValidator;
    }

    public UserController() {
    }

    @RequestMapping(method = RequestMethod.GET )
    public String getAllUsers(Model model) {
        model.addAttribute("usr", userServices.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/{username}" ,method = RequestMethod.GET )
    @ResponseBody
    public UserProfileDto getByUsername(@PathVariable("username") String username) {
       UserProfileDto userProfileDto = userServices.findByUsername(username);
        return userProfileDto;
    }

    @RequestMapping(method =  RequestMethod.POST , produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public UserProfileJsonResponce newUserRegistration(@ModelAttribute @Valid UserProfileDto userProfileDto, BindingResult bindingResult , HttpServletRequest request) {

        UserProfileJsonResponce userProfileJsonResponce =new UserProfileJsonResponce();
        if (bindingResult.hasErrors()) {
            //Get error message
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
            userProfileJsonResponce.setValidated(false);
            userProfileJsonResponce.setErrorMessages(errors);
//            return "redirect:/users";
        }
        else {

            UserProfileDto userProfileDto1 = userServices.saveUser(userProfileDto);
            if(userProfileDto1 !=null)
            {
                userProfileJsonResponce.setValidated(true);
                userProfileJsonResponce.setUserProfileDto(userProfileDto1);
            }

//            return "redirect:/index";
        }
        return userProfileJsonResponce;
    }

    @RequestMapping(method =  RequestMethod.PUT , produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public UserProfileJsonResponce updateUser(@ModelAttribute @Valid UserProfileDto userProfileDto, BindingResult bindingResult , HttpServletRequest request) {

        UserProfileJsonResponce userProfileJsonResponce =new UserProfileJsonResponce();
        if (bindingResult.hasErrors()) {

            //Get error message
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );


//            if(bindingResult.getAllErrors())
            userProfileJsonResponce.setValidated(false);
            userProfileJsonResponce.setErrorMessages(errors);
//            return "redirect:/users";
        }
        else {

            UserProfileDto userProfileDto1 = userServices.updateUser(userProfileDto);
            if(userProfileDto1 !=null)
            {
                userProfileJsonResponce.setValidated(true);
                userProfileJsonResponce.setUserProfileDto(userProfileDto1);
            }

//            return "redirect:/index";
        }
        return userProfileJsonResponce;
    }


    @RequestMapping(value = "/{username}" , method =  RequestMethod.DELETE )
    public ResponseEntity deleteUser(@ModelAttribute UserProfileDto userProfileDto,@PathVariable("username") String username) {

            userServices.deleteUser(userProfileDto);
            return ResponseEntity.status(HttpStatus.OK).build();
    }


}
