package ir.phgint;

import ir.phgint.domain.TransactionType;
import ir.phgint.domain.WalletInvoices;
import ir.phgint.domain.dto.MerchantProfileDto;
import ir.phgint.domain.repository.MerchantProfileDaoImpl;
import ir.phgint.service.MerchantServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Unit test for simple App.
 */
@TestPropertySource(locations="/testApplication.properties")

@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
@SpringBootTest
//@Rollback(false)
public class MerchantServicesTest
{
    @Autowired
    private MerchantServices merchantServices;

    @Autowired
    private MerchantProfileDaoImpl merchantProfileDao;

    MerchantProfileDto merchantProfileDto = new MerchantProfileDto();
    WalletInvoices walletInvoices1 = new WalletInvoices();
    private List<WalletInvoices> walletAmount1= new ArrayList<>();

    @Before
    public void init() throws Exception {

        merchantProfileDto.setName("Ebi");
        merchantProfileDto.setFamily("bahri");
        merchantProfileDto.setAddress("tehran");
        merchantProfileDto.setEmail("alis@yahoo.com"+ Math.random()); // unique = true
        merchantProfileDto.setMobile("9371653365");
        merchantProfileDto.setPhone("44556677");
        merchantProfileDto.setDebitCardPan("3212444455548896"); // unique = true
        merchantProfileDto.setNationalId("0480453225");
        walletInvoices1.setAmount(1000);
        walletInvoices1.setTimestamp(new Date(13980425));
        walletInvoices1.setType(TransactionType.TRANSFER);
        walletAmount1.add(walletInvoices1);
        merchantProfileDto.setWalletInvoices(walletAmount1);

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = format.parse("1991/11/18");
        merchantProfileDto.setRegistrationTimestamp(date);

    }

    @Test
    public void addMerchantProfileTest()
    {
        MerchantProfileDto merchantProfileDto1 =merchantServices.saveMerchant(merchantProfileDto);
        MerchantProfileDto merchant = merchantServices.findMerchantById(merchantProfileDto1.getMerchantId());
        assertNotNull("save MerchantProfile successfully",merchant);


    }
    @Test
    public void findMerchantProfileTest()
    {
        MerchantProfileDto merchantProfileDto1 = merchantServices.saveMerchant(merchantProfileDto);
        MerchantProfileDto merchant = merchantServices.findMerchantById(merchantProfileDto1.getMerchantId());
        assertEquals("find MerchantProfile successfully",merchant.getMerchantId(),merchantProfileDto.getMerchantId());

    }

    @Test
    public void updateMerchantProfileTest()
    {
        MerchantProfileDto merchantProfileDto1 = merchantServices.saveMerchant(merchantProfileDto);
        merchantProfileDto1.setName("amir");
        merchantServices.updateMerchant(merchantProfileDto1);
        MerchantProfileDto merchant = merchantServices.findMerchantById(merchantProfileDto1.getMerchantId());
        assertEquals("update Merchant successfully",merchantProfileDto1.getName(),merchant.getName());

    }

    @Test
    public void deleteMerchantProfileTest()
    {
        MerchantProfileDto merchantProfileDto1 = merchantServices.saveMerchant(merchantProfileDto);
        merchantServices.deleteMerchant(merchantProfileDto1);
        MerchantProfileDto merchant = merchantServices.findMerchantById(merchantProfileDto1.getMerchantId());
        assertNull("delete Merchant successfully ",merchant);
    }


}
