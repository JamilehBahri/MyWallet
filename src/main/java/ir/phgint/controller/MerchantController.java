package ir.phgint.controller;

import ir.phgint.domain.dto.MerchantProfileDto;
import ir.phgint.domain.dto.MerchantProfileJsonResponce;
import ir.phgint.service.MerchantServices;
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
@RequestMapping(value = "/merchants")
public class MerchantController {

    @Autowired
    private MerchantServices merchantServices;

    private MessageSource messageSource;


    @Autowired
    public MerchantController(MessageSource messageSource, MerchantServices merchantServices
                    ) {
        this.messageSource = messageSource;
        this.merchantServices = merchantServices;
        //this.userProfileValidator = userProfileValidator;
    }

    public MerchantController() {
    }


    @RequestMapping(method = RequestMethod.GET )
    public String getAllmerchants(Model model) {
        model.addAttribute("merchant",merchantServices.getAllMerchants());

        return "merchants";
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET )
    @ResponseBody
    public MerchantProfileDto getById(@PathVariable("id") Long id) {
        MerchantProfileDto merchantProfileDto = merchantServices.findMerchantById(id);
        return merchantProfileDto;
    }

    @RequestMapping(method =  RequestMethod.POST , produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MerchantProfileJsonResponce newMerchants(@ModelAttribute @Valid MerchantProfileDto merchantProfileDto,  BindingResult bindingResult ,HttpServletRequest request) {

        MerchantProfileJsonResponce merchantProfileJsonResponce =new MerchantProfileJsonResponce();
        if (bindingResult.hasErrors()) {
            //Get error message
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
            merchantProfileJsonResponce.setValidated(false);
            merchantProfileJsonResponce.setErrorMessages(errors);
        }
        else {

            MerchantProfileDto merchantProfileDto1 = merchantServices.saveMerchant(merchantProfileDto);
            if(merchantProfileDto1 !=null)
            {
                merchantProfileJsonResponce.setValidated(true);
                merchantProfileJsonResponce.setUserProfileDto(merchantProfileDto1);
            }
        }
        return merchantProfileJsonResponce;
    }

    @RequestMapping(method =  RequestMethod.PUT , produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MerchantProfileJsonResponce updateMerchant(@ModelAttribute @Valid MerchantProfileDto merchantProfileDto, BindingResult bindingResult , HttpServletRequest request) {

        MerchantProfileJsonResponce merchantProfileJsonResponce =new MerchantProfileJsonResponce();
        if (bindingResult.hasErrors()) {
            //Get error message
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
            merchantProfileJsonResponce.setValidated(false);
            merchantProfileJsonResponce.setErrorMessages(errors);
        }
        else {
            MerchantProfileDto merchantProfileDto1= merchantServices.updateMerchant(merchantProfileDto);
            if(merchantProfileDto1 !=null)
            {
                merchantProfileJsonResponce.setValidated(true);
                merchantProfileJsonResponce.setUserProfileDto(merchantProfileDto1);
            }
        }
        return merchantProfileJsonResponce;
    }

    @RequestMapping(value = "/{id}" , method =  RequestMethod.DELETE )
    public ResponseEntity deleteUser(@ModelAttribute MerchantProfileDto merchantProfileDto, @PathVariable("id") Long id) {

        merchantProfileDto.setMerchantId(id);
        merchantServices.deleteMerchant(merchantProfileDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
