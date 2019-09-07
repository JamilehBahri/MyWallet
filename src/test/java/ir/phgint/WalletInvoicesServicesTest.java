package ir.phgint;

import ir.phgint.domain.dto.WalletInvoicesDto;
import ir.phgint.service.WalletInvoicesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@TestPropertySource(locations="/testApplication.properties")
//@ContextConfiguration(locations = {"classpath:resources/testApplication.properties"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WalletInvoicesServicesTest {

    @Autowired
    private WalletInvoicesServiceImpl walletInvoicesService;

    WalletInvoicesDto walletInvoicesDto= new WalletInvoicesDto();

    @Before
    public void init() throws Exception {


//        walletInvoicesDto.setAmount(100);
//        walletInvoicesDto.setType("TRANSFER");
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = format.parse("1398-05-24 10:15:36");
//        walletInvoicesDto.setTimestamp(date.toString());
//
////        walletInvoicesDto.setId(walletInvoices.getId());
//        walletInvoicesDto.setUserFullName("sina");
//        walletInvoicesDto.setUsername("stadayyon");
//        walletInvoicesDto.setUserMobile("09361653365");
//
//        walletInvoicesDto.setMerchantDebitCardPan(null);
//        walletInvoicesDto.setMerchantFullName(null);
////        walletInvoicesDto.setMerchantId(null);
//        walletInvoicesDto.setMerchantMobile(null);



    }

    @Test
    public void getUserInvoicesServicesTest()
    {
        try {
            List<WalletInvoicesDto> userWalletInvoices = new ArrayList<>();
            List<WalletInvoicesDto> walletInvoicesDtoList = walletInvoicesService.getUserInvoices ("stadayyon");

            for (int i =0 ; i< walletInvoicesDtoList.size() ; i++){
                userWalletInvoices.add(walletInvoicesDtoList.get(i));

            }

            System.out.println(walletInvoicesDtoList);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void getlatestTransactionServicesTest()
    {
        try {
            List<WalletInvoicesDto> userWalletInvoices = new ArrayList<>();
            List<WalletInvoicesDto> walletInvoicesDtoList = walletInvoicesService.getTenLastInvoices();

            for (int i =0 ; i< walletInvoicesDtoList.size() ; i++){
                userWalletInvoices.add(walletInvoicesDtoList.get(i));

            }

            System.out.println(walletInvoicesDtoList);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void calculateWalletBalanceServicesTest()
    {
        try {
            Double balance = walletInvoicesService.calculateWalletBalance();


            System.out.println(balance);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
