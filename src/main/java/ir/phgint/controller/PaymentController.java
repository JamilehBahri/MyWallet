package ir.phgint.controller;

import ir.phgint.domain.MerchantProfile;
import ir.phgint.domain.TransactionType;
import ir.phgint.domain.UserProfile;
import ir.phgint.domain.WalletInvoices;
import ir.phgint.domain.dto.MerchantProfileDto;
import ir.phgint.domain.dto.PaymentDto;
import ir.phgint.domain.dto.PaymentJsonResponce;
import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.mapper.ObjectMapper;
import ir.phgint.service.MerchantServices;
import ir.phgint.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class PaymentController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private MerchantServices merchantServices;

    @Autowired
    @Qualifier("UserProfileMapper")
    private ObjectMapper<UserProfile, UserProfileDto> userProfileDtoObjectMapper;

    @Autowired
    @Qualifier("MerchantProfileMapper")
    private ObjectMapper<MerchantProfile, MerchantProfileDto> merchantProfileObjectMapper;



    @RequestMapping(value = "/payment" , method = RequestMethod.GET)
    public String payment(Model model){
        model.addAttribute("paymentInfo" , new PaymentDto());
        return "payment";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST , produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public PaymentJsonResponce confirmPaymentInfo(@ModelAttribute @Valid PaymentDto paymentDto, BindingResult bindingResult ){
        PaymentJsonResponce paymentJsonResponce =new PaymentJsonResponce();
        if (bindingResult.hasErrors()) {
            //Get error message
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
            paymentJsonResponce.setValidated(false);
            paymentJsonResponce.setErrorMessages(errors);
        }
        else {
            MerchantProfileDto merchantProfileDto= merchantServices.findMerchantById(paymentDto.getMerchantcode());
            PaymentDto paymentDto1 = new PaymentDto();
            paymentDto1.setMerchantname(merchantProfileDto.getName());
            paymentDto1.setMerchantfamily(merchantProfileDto.getFamily());
            paymentDto1.setMerchantcode(merchantProfileDto.getMerchantId());
            paymentDto1.setAmount(paymentDto.getAmount());
            paymentDto1.setUsername(paymentDto.getUsername());

            if(merchantProfileDto!=null)
            {
                paymentJsonResponce.setValidated(true);
                paymentJsonResponce.setPaymentDto(paymentDto1);
            }

        }
        return paymentJsonResponce;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.PUT , produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public PaymentJsonResponce putPaymentInfo(@ModelAttribute @Valid PaymentDto paymentDto, BindingResult bindingResult ) throws ParseException {
        PaymentJsonResponce paymentJsonResponce =new PaymentJsonResponce();
        if (bindingResult.hasErrors()) {
            //Get error message
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
            paymentJsonResponce.setValidated(false);
            paymentJsonResponce.setErrorMessages(errors);
        }

        // decrese amount from user balance
        else {
            UserProfileDto userProfileDto = userServices.findByUsername(paymentDto.getUsername());
            if(userProfileDto!=null){
                if(userProfileDto.getBalance()>= paymentDto.getAmount()){
                    userProfileDto.setBalance(userProfileDto.getBalance()-paymentDto.getAmount());
                    userServices.updateUser(userProfileDto);
                }
                else {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("error","user not enough money ");
                            paymentJsonResponce.setErrorMessages(errors);
                }
            }else {
                Map<String, String> errors = new HashMap<>();
                errors.put("error","user not exist ");
                paymentJsonResponce.setErrorMessages(errors);
            }

            // increment amount to merchant account
            MerchantProfileDto merchantProfileDto= merchantServices.findMerchantById(paymentDto.getMerchantcode());
            if(merchantProfileDto!=null)
            {
                List<WalletInvoices> walletInvoicesList = new ArrayList<>();
                WalletInvoices walletInvoices = new WalletInvoices();

                walletInvoices.setAmount(paymentDto.getAmount());
                walletInvoices.setType(TransactionType.TRANSFER);
                walletInvoices.setUserProfile(userProfileDtoObjectMapper.mapRevers(userProfileDto));
                walletInvoices.setMerchantProfile(merchantProfileObjectMapper.mapRevers(merchantProfileDto));

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(userProfileDto.getBirthday());
                walletInvoices.setTimestamp(date);

                walletInvoicesList.add(walletInvoices);
                merchantProfileDto.setWalletInvoices(walletInvoicesList);

                merchantServices.updateMerchant(merchantProfileDto);

            }

        }
        return paymentJsonResponce;
    }


//    @RequestMapping(value = "/confirmPay" , method = RequestMethod.GET , produces = { MediaType.APPLICATION_JSON_VALUE })
//    public String getconfirmPay(@ModelAttribute PaymentDto paymentDto){
////        model.addAttribute("paymentInfo" , new PaymentDto());
//        return "payment";
//
//    }
//
//    @RequestMapping(value = "/confirmPay" , method = RequestMethod.POST , produces = { MediaType.APPLICATION_JSON_VALUE })
//    public String confirmPay(@ModelAttribute PaymentDto paymentDto){
////        model.addAttribute("paymentInfo" , new PaymentDto());
//        return "payment";
//
//    }


}
