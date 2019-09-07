package ir.phgint.service;

import ir.phgint.domain.MerchantProfile;
import ir.phgint.domain.TransactionType;
import ir.phgint.domain.UserProfile;
import ir.phgint.domain.WalletInvoices;
import ir.phgint.domain.dto.WalletInvoicesDto;
import ir.phgint.domain.repository.MerchantProfileDaoImpl;
import ir.phgint.domain.repository.UserProfileDao;
import ir.phgint.domain.repository.WalletInvoicesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WalletInvoicesServiceImpl {

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private MerchantProfileDaoImpl merchantProfileDao;

    @Autowired
    private WalletInvoicesDao walletInvoicesDao;


    public void saveIncrementCredit(String username , double amount){
        UserProfile userProfile= userProfileDao.findUserByUsername(username);
        WalletInvoicesDto walletInvoicesDto = new WalletInvoicesDto();
        walletInvoicesDto.setUsername(username);
        walletInvoicesDto.setUserMobile(userProfile.getMobile());
        walletInvoicesDto.setUserId(userProfile.getId());
        walletInvoicesDto.setAmount(amount);
        walletInvoicesDto.setType("TRANSFER");
        //timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        walletInvoicesDto.setTimestamp(dateFormat.format(date));
        walletInvoicesDao.save(convertWalletInvoices(walletInvoicesDto));

    }


    public List<WalletInvoicesDto> getMerchantInvoices(long merchantId) {
        List<WalletInvoicesDto> userWalletInvoicesDtos = new ArrayList<>();

        MerchantProfile merchantProfile = merchantProfileDao.findOne(merchantId);

        for (WalletInvoices invoices : merchantProfile.getWalletInvoices()){
            try {
                userWalletInvoicesDtos.add(convertWalletInvoicesDto(invoices));

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return userWalletInvoicesDtos;
    }

    public List<WalletInvoicesDto> getUserInvoices(String username) {
        List<WalletInvoicesDto> userWalletInvoicesDtos = new ArrayList<>();

        UserProfile userProfile= userProfileDao.findUserByUsername(username);

        for (WalletInvoices invoices : userProfile.getWalletInvoices()){
            try {
                userWalletInvoicesDtos.add(convertWalletInvoicesDto(invoices));

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return userWalletInvoicesDtos;
    }

    public List<WalletInvoicesDto> getTenLastInvoices() {
        List<WalletInvoicesDto> userWalletInvoicesDtos = new ArrayList<>();
        List<WalletInvoices> userWalletInvoices = new ArrayList<>();

         userWalletInvoices = walletInvoicesDao.findTop10ByOrderByTimestampDesc();

        for (WalletInvoices invoices : userWalletInvoices){
            try {
                userWalletInvoicesDtos.add(convertWalletInvoicesDto(invoices));

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return userWalletInvoicesDtos;
    }

    public Map<String, Double> calculateUsersWalletBalance() {
       List<WalletInvoices> walletInvoicesList;
       Map<String,Double> map = new HashMap<>();
            walletInvoicesList = walletInvoicesDao.findAll();
            return map;

//        return Optional.of(walletInvoicesList.stream().collect(Collectors.groupingBy(
//                walletInvoices -> walletInvoices.getUserProfile().getUsername(),
//                Collectors.reducing(0.0, walletInvoices ->
//                                walletInvoices.getType() == TransactionType.TRANSFER ?
//                                        walletInvoices.getAmount() : -walletInvoices.getAmount()
//                        , Double::sum))));

    }

    public Double calculateWalletBalance() {
//        List<WalletInvoicesDto> WalletInvoicesDtos = new ArrayList<>();
        List<WalletInvoices> walletInvoices = new ArrayList<>();
        Double balance =0d;

        walletInvoices = walletInvoicesDao.findAll();

        for (WalletInvoices invoices : walletInvoices){
            try {
                if(invoices.getType()==TransactionType.TRANSFER)
                    balance+= invoices.getAmount();
//                    WalletInvoicesDtos.add(convertWalletInvoicesDto(invoices));

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return balance;
    }


//    @Override
//    public List<WalletInvoicesDto> find10TopUserTransactionByOrederTimestamp(String username) {
//        try {
//            List<WalletInvoicesDto> userWalletInvoicesDtos = new ArrayList<>();
//
//
//            List<WalletInvoices> walletInvoices = walletInvoicesDao.find10TopUserTransactionByOrederTimestamp(username);
//            for (WalletInvoices invoices : walletInvoices){
//                userWalletInvoicesDtos.add(convertWalletInvoicesDto(invoices));
//            }
//            return userWalletInvoicesDtos;
//
//        }catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<WalletInvoicesDto> find10TopMerchantTransactionByOrederTimestamp(Long id) {
//        try {
//            List<WalletInvoicesDto> userWalletInvoicesDtos = new ArrayList<>();
//
//            List<WalletInvoices> walletInvoices = walletInvoicesDao.find10TopMerchantTransactionByOrederTimestamp(id);
//            for (WalletInvoices invoices : walletInvoices){
//                userWalletInvoicesDtos.add(convertWalletInvoicesDto(invoices));
//            }
//            return userWalletInvoicesDtos;
//
//        }catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//


    public WalletInvoices convertWalletInvoices (WalletInvoicesDto walletInvoicesDto) {
        try {
            WalletInvoices walletInvoices= new WalletInvoices();
            if (walletInvoicesDto!= null) {

                UserProfile userProfile = new UserProfile();
                userProfile = userProfileDao.findUserByUsername(walletInvoicesDto.getUsername());
//                userProfile.setId(UUID.fromString(String.valueOf(walletInvoicesDto.getId())));
//                userProfile.setId(walletInvoicesDto.getUserId());
//                userProfile.setName(walletInvoicesDto.getUserFullName());
//                userProfile.setUsername(walletInvoicesDto.getUsername());
//                userProfile.setMobile(walletInvoicesDto.getUserMobile());

                MerchantProfile merchantProfile = new MerchantProfile();
                merchantProfile = merchantProfileDao.findOne(walletInvoicesDto.getMerchantId());
//                merchantProfile.setMerchantId(walletInvoicesDto.getMerchantId());
//                merchantProfile.setMobile(walletInvoicesDto.getMerchantMobile());
//                merchantProfile.setDebitCardPan(walletInvoicesDto.getMerchantDebitCardPan());
//                merchantProfile.setName(walletInvoicesDto.getMerchantFullName());


                walletInvoices.setUserProfile(userProfile);
                walletInvoices.setMerchantProfile(merchantProfile);
                walletInvoices.setAmount(walletInvoicesDto.getAmount());
                if(walletInvoicesDto.getType().equals("TRANSFER"))
                    walletInvoices.setType(TransactionType.TRANSFER);
                if(walletInvoicesDto.getType().equals("PURCHASE"))
                    walletInvoices.setType(TransactionType.PURCHASE);

                //timestamp
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                dateformat.format(date);
                walletInvoices.setTimestamp(date);

               return walletInvoices;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public WalletInvoicesDto convertWalletInvoicesDto(WalletInvoices walletInvoices) throws Exception {
        WalletInvoicesDto walletInvoicesDto= new WalletInvoicesDto();
        if (walletInvoicesDto!= null) {

            walletInvoicesDto.setAmount(walletInvoices.getAmount());
            if(walletInvoices.getType() == TransactionType.TRANSFER)
                walletInvoicesDto.setType("TRANSFER");
            if(walletInvoices.getType() == TransactionType.PURCHASE)
                walletInvoicesDto.setType("PURCHASE");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(walletInvoices.getTimestamp().toString());
            walletInvoicesDto.setTimestamp(date.toString());

            if(walletInvoices.getUserProfile()!=null) {
                walletInvoicesDto.setId(walletInvoices.getId());
                walletInvoicesDto.setUserFullName(walletInvoices.getUserProfile().getName());
                walletInvoicesDto.setUsername(walletInvoices.getUserProfile().getUsername());
                walletInvoicesDto.setUserMobile(walletInvoices.getUserProfile().getMobile());
            }
            if (walletInvoices.getMerchantProfile()!=null) {
                walletInvoicesDto.setMerchantDebitCardPan(walletInvoices.getMerchantProfile().getDebitCardPan());
                walletInvoicesDto.setMerchantFullName(walletInvoices.getMerchantProfile().getName());
                walletInvoicesDto.setMerchantId(walletInvoices.getMerchantProfile().getMerchantId());
                walletInvoicesDto.setMerchantMobile(walletInvoices.getMerchantProfile().getMobile());
            }


            return walletInvoicesDto;
        }
        return null;
    }

}
