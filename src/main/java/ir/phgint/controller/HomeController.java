package ir.phgint.controller;

import ir.phgint.domain.dto.WalletInvoicesDto;
import ir.phgint.service.UserServices;
import ir.phgint.service.WalletInvoicesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserServices userServices;

    @Autowired
    WalletInvoicesServiceImpl walletInvoicesService;

    public HomeController() {
    }

    @RequestMapping(value = "/" , method = RequestMethod.GET )
    public String dashboard (Model model) {
        model.addAttribute("usr", userServices.getAllUsers());
        List<WalletInvoicesDto> walletInvoicesDto = walletInvoicesService.getTenLastInvoices();
        model.addAttribute("latestTransaction", walletInvoicesDto);
        Double balance = walletInvoicesService.calculateWalletBalance();
        model.addAttribute("balance", balance);

        return "index";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }
}
