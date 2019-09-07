package ir.phgint.controller;

import ir.phgint.domain.dto.IncCreditDto;
import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.service.UserServices;
import ir.phgint.service.WalletInvoicesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class IncrementCreditController {

    @Autowired
    private  UserServices userServices;
    @Autowired
    private WalletInvoicesServiceImpl walletInvoicesService;

    @RequestMapping(value = "/incrementcredit", method = RequestMethod.GET )
    public String incrementCredit (Model model) {
        model.addAttribute("inccredit", new IncCreditDto());
        return "inccredit";
    }

    @RequestMapping(value = "/incrementcredit" , method = RequestMethod.POST)
    public ModelAndView incrementCredit(@ModelAttribute("inccredit") @Valid IncCreditDto incCreditDto,
                                        BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("incrementcredit");
        }
        else {
            UserProfileDto userProfileDto = userServices.findByUsername(incCreditDto.getUsername());
            IncCreditDto incCreditConfirmDto = new IncCreditDto();
            incCreditConfirmDto.setUsername(incCreditDto.getUsername());
            incCreditConfirmDto.setAmount(incCreditDto.getAmount());
            incCreditConfirmDto.setName(userProfileDto.getName());
            incCreditConfirmDto.setFamily(userProfileDto.getFamily());
            redirect.addFlashAttribute("redirect",incCreditConfirmDto);
            ModelAndView modelAndView = new ModelAndView("redirect:confirmcredit");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/confirmcredit", method = RequestMethod.GET )
    public String confirmincrementcredit (Model model) {
        model.addAttribute("confirmationInfo",model.asMap().get("redirect"));
        return "confirmcredit";
    }
//@RequestMapping(value = "/confirmincrementcredit", method = RequestMethod.GET )
//public ModelAndView confirmincrementcredit (Model model,RedirectAttributes redirect) {
//    model.addAttribute("IncCreditConfirmDto",model.asMap().get("redirect"));
//
//    ModelAndView modelAndView = new ModelAndView("confirmincrementcredit");
//    redirect.addFlashAttribute("redirect",model.asMap().get("redirect"));
//    return modelAndView;
//}

    @RequestMapping(value = "/confirmcredit", method = RequestMethod.POST)
    public ModelAndView incrementCreditConfirmPost(Model model ,@ModelAttribute("confirmationInfo") IncCreditDto incCreditDto){

        IncCreditDto confirmInfo = (IncCreditDto) model.asMap().getOrDefault("confirmationInfo" ,  new IncCreditDto());
        walletInvoicesService.saveIncrementCredit(confirmInfo.getUsername(),confirmInfo.getAmount());

//        UserProfileDto userProfileDto = userServices.findByUsername(incCreditConfirmDto.getUsername());
//        userProfileDto.setBalance(userProfileDto.getBalance()+incCreditConfirmDto.getAmount());
//        userServices.saveUser(userProfileDto);
        ModelAndView modelAndView = new ModelAndView("redirect:successcredit");
        modelAndView.addObject(model);
        return modelAndView;
    }

    @RequestMapping(value = "/successcredit", method = RequestMethod.GET )
    public String incrementCreditConfirmsuccess (Model model) {

        return "successcredit";

    }
//    @RequestMapping(value = "/successcredit", method = RequestMethod.POST )
//    public String incrementCreditConfirmsuccessPost (Model model) {
//
//        return "redirect:successcredit";
//
//    }





}
