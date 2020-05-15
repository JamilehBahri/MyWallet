package ir.phgint;

import ir.phgint.domain.*;
import ir.phgint.domain.repository.UserProfileDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for simple App.
 */
@TestPropertySource(locations="/testApplication.properties")
//@ContextConfiguration(locations = {"classpath:resources/testApplication.properties"})

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
@Rollback(false)
public class UserProfileDaoTest
{
    @Autowired
    private UserProfileDaoImpl userProfileDao;

    UserProfile userProfile = new UserProfile();
    Role role1 = new Role();
    WalletInvoices walletInvoices1 = new WalletInvoices();
    private List<WalletInvoices> walletAmount1= new ArrayList<>();

    //admin info
    UserProfile adminProfile = new UserProfile();

    @Before
    public void init() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //   String dateString = format.format( new Date()   );
        Date date = format.parse("1362-11-18");

        userProfile.setName("sina");
        userProfile.setFamily("taddayon");
        userProfile.setUsername("admin");
        userProfile.setPassword("123");
        userProfile.setMobile("9121653365");
        userProfile.setPhone("22334455");
        userProfile.setEmail("stadayyon@gmail.com");
        userProfile.setAddress("narmak");
        userProfile.setBirthday(date);
        userProfile.setGender(Gender.MALE);
        userProfile.setNationalId("0480453225");
        role1.setName("User");
        userProfile.setRole(role1);
        walletInvoices1.setAmount(1000);
        walletInvoices1.setTimestamp(new Date(13980425));
        walletInvoices1.setType(TransactionType.TRANSFER);
        walletInvoices1.setUserProfile(userProfile);
        walletAmount1.add(walletInvoices1);
        userProfile.setWalletInvoices(walletAmount1);

        adminProfile.setName("jamileh");
        adminProfile.setFamily("bahri");
        adminProfile.setUsername("admin");
        adminProfile.setPassword("123");
        adminProfile.setMobile("9121653365");
        adminProfile.setPhone("22334455");
        adminProfile.setEmail("bahri@gmail.com");
        adminProfile.setAddress("rey");
        adminProfile.setBirthday(date);
        adminProfile.setGender(Gender.FEMALE);
        adminProfile.setNationalId("0480453225");
        role1.setName("Admin");
        adminProfile.setRole(role1);
        walletInvoices1.setAmount(1000);
        walletInvoices1.setTimestamp(new Date(13980425));
        walletInvoices1.setType(TransactionType.TRANSFER);
        walletInvoices1.setUserProfile(adminProfile);
        walletAmount1.add(walletInvoices1);
        adminProfile.setWalletInvoices(walletAmount1);



    }
    @Test
    public void addAdminProfileTest()
    {
        try {
            userProfileDao.save(adminProfile);
            UserProfile admin = userProfileDao.findUserById(adminProfile.getId());
            assertEquals("save user successfully",admin.getId(),adminProfile.getId());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void addUserProfileTest()
    {
        try {
            userProfileDao.save(userProfile);
            UserProfile user = userProfileDao.findUserById(userProfile.getId());
            assertEquals("save user successfully",user.getId(),userProfile.getId());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void findUserProfileTest()
    {
        userProfileDao.save(userProfile);
        UserProfile user = userProfileDao.findUserByUsername(userProfile.getUsername());
        assertEquals("find user successfully",user.getUsername(),userProfile.getUsername());

    }

    @Test
    public void updateUserProfileTest()
    {
        userProfileDao.save(userProfile);
        userProfile.setName("ali");
        UserProfile user =  userProfileDao.save(userProfile); //update
        assertEquals("update user successfully",userProfile.getId(),user.getId());
    }

    @Test
    public void deleteUserProfileTest()
    {
        userProfileDao.save(userProfile);
        UserProfile user = userProfileDao.findUserById(userProfile.getId());
        userProfileDao.delete(user);
        UserProfile user2 = userProfileDao.findUserById(userProfile.getId());
        assertNull("delete user successfully",user2);
    }



}
