package ir.phgint.controller;

import ir.phgint.domain.dto.WalletInvoicesDto;
import ir.phgint.service.WalletInvoicesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/Invoices")
public class WalletInvoicesController {

    @Autowired
    WalletInvoicesServiceImpl walletInvoicesService;

    @RequestMapping(value = "/users/{username}" ,method = RequestMethod.GET )
    @ResponseBody
    public List<WalletInvoicesDto> getUserInvoices(@PathVariable("username") String username) {
        List<WalletInvoicesDto> walletInvoicesDto = walletInvoicesService.getUserInvoices(username);

        return walletInvoicesDto;

    }

    @RequestMapping(value = "/merchants/{id}" ,method = RequestMethod.GET )
    @ResponseBody
    public List<WalletInvoicesDto> getMerchantInvoices(@PathVariable("id") Long id) {
        List<WalletInvoicesDto> walletInvoicesDto = walletInvoicesService.getMerchantInvoices(id);
        return walletInvoicesDto;

    }
//
    @RequestMapping(value = "/latest" ,method = RequestMethod.GET )
    @ResponseBody
    public List<WalletInvoicesDto> getTwentiesLast(Model model){

        List<WalletInvoicesDto> walletInvoicesDto = walletInvoicesService.getTenLastInvoices();
        model.addAttribute("latestTransaction", walletInvoicesDto);

        return walletInvoicesDto;
    }


    @RequestMapping(value = "/balance" ,method = RequestMethod.GET )
    @ResponseBody
    public Double getBalanceWallet() {
       Double WalletBalance = walletInvoicesService.calculateWalletBalance();
        return WalletBalance;

//        return null;
//        WalletBalanceDto walletBalanceDto = new WalletBalanceDto();
//        walletBalanceDto.setUsersWalletBalance(
//                walletInvoicesService.calculateUsersWalletBalance().orElse(new HashMap<>()));
//
//        walletBalanceDto.setWalletBalance(walletInvoicesService.calculateWalletBalance().orElse(0.0));
//        return Response.ok(walletBalanceDto).build();
    }

}
