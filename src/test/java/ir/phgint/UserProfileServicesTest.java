package ir.phgint;

import ir.phgint.domain.Gender;
import ir.phgint.domain.Role;
import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.domain.repository.UserProfileDao;
import ir.phgint.service.UserServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@TestPropertySource(locations="/testApplication.properties")
//@ContextConfiguration(locations = {"classpath:resources/testApplication.properties"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserProfileServicesTest {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserProfileDao userProfileDao;


    UserProfileDto userProfileDto = new UserProfileDto();
    Role role = new Role();


    @Before
    public void init() throws Exception {
        userProfileDto.setName("sina");
        userProfileDto.setFamily("tadayon");
        userProfileDto.setUsername("st");
        userProfileDto.setPassword("123");
        userProfileDto.setAddress("teh");
        userProfileDto.setBirthday("1362-11-18");
        userProfileDto.setGender(Gender.MALE);
        userProfileDto.setBalance(1000);
        userProfileDto.setEmail("stadayon@gmail.com");
        userProfileDto.setMobile("9121653365");
        userProfileDto.setNationalId("0480453225");
        userProfileDto.setPhone("33445566");
        role.setName("User");
        userProfileDto.setRole(role.getName());

    }

    @Test
    public void saveUserProfileServicesTest()
    {
        try {
            userServices.saveUser(userProfileDto);
            UserProfileDto user= userServices.findByUsername("st");
            assertEquals("save user successfully",user.getUsername(),userProfileDto.getUsername());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void findUserProfileByUsernameServicesTest()
    {
        try {
            userServices.saveUser(userProfileDto);
            UserProfileDto user= userServices.findByUsername("st");
            assertEquals("find user successfully",user.getUsername(),userProfileDto.getUsername());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void updateUserProfileServicesTest()
    {
        try {
            userServices.saveUser(userProfileDto);
            userProfileDto.setName("jamileh");
            UserProfileDto user= userServices.updateUser(userProfileDto);
            assertEquals("update user successfully",user.getName(),userProfileDto.getName());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void deleteUserProfileServicesTest()
    {
        try {
            userServices.saveUser(userProfileDto);
            userServices.deleteUser(userProfileDto);
            UserProfileDto user= userServices.findByUsername("st");
            assertNull("delete user successfully",user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
