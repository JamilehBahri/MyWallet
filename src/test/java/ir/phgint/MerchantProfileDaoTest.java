package ir.phgint;

import ir.phgint.domain.MerchantProfile;
import ir.phgint.domain.TransactionType;
import ir.phgint.domain.WalletInvoices;
import ir.phgint.domain.repository.MerchantProfileDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for simple App.
 */
@TestPropertySource(locations="/testApplication.properties")

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
//@Rollback(false)
public class MerchantProfileDaoTest
{

    @Autowired
    private MerchantProfileDaoImpl merchantProfileDaoImpl;


    MerchantProfile merchantProfile = new MerchantProfile();
    WalletInvoices walletInvoices1 = new WalletInvoices();
    private List<WalletInvoices> walletAmount1= new ArrayList<>();


    @Before
    public void init() throws Exception {

        merchantProfile.setName("ali");
        merchantProfile.setFamily("bahri");
        merchantProfile.setAddress("tehran");
        merchantProfile.setEmail("ali@yahoo.com");
        merchantProfile.setMobile("9371653365");
        merchantProfile.setPhone("44556677");
        merchantProfile.setDebitCardPan("1212444455558888");
        merchantProfile.setNationalId("0480453225");
        walletInvoices1.setAmount(1000);
        walletInvoices1.setTimestamp(new Date(13980425));
        walletInvoices1.setType(TransactionType.TRANSFER);
        walletAmount1.add(walletInvoices1);
        merchantProfile.setWalletInvoices(walletAmount1);


    }

    @Test
    public void addMerchantProfileTest()
    {
        merchantProfileDaoImpl.save(merchantProfile);
        MerchantProfile merchant = merchantProfileDaoImpl.findOne(merchantProfile.getMerchantId());
        assertEquals("save MerchantProfile successfully",merchant.getMerchantId(),merchantProfile.getMerchantId());

    }
    @Test
    public void findMerchantProfileTest()
    {
        MerchantProfile merchant = merchantProfileDaoImpl.findOne(merchantProfile.getMerchantId());
        assertEquals("find MerchantProfile successfully",merchant.getMerchantId(),merchantProfile.getMerchantId());

    }

    @Test
    public void updateMerchantProfileTest()
    {
        merchantProfileDaoImpl.save(merchantProfile);
        merchantProfile.setName("amir");
        MerchantProfile merchant = merchantProfileDaoImpl.save(merchantProfile);
        assertNotEquals("update user successfully",merchantProfile.getName(),merchant.getName());

    }

    @Test
    public void deleteMerchantProfileTest()
    {
        merchantProfileDaoImpl.save(merchantProfile);
        merchantProfileDaoImpl.delete(merchantProfile);
        MerchantProfile merchant = merchantProfileDaoImpl.findOne(merchantProfile.getMerchantId());
        assertNull("",merchant);
    }


}
