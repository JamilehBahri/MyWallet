package ir.phgint;

import ir.phgint.controller.UserController;
import ir.phgint.domain.*;
import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.service.UserServices;
import ir.phgint.validation.DuplecateUsernameValidation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestPropertySource(locations="/testApplication.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private UserServices userServicesMock;

    @Autowired
    UserController userController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    @Qualifier("duplicateusername")
    private DuplecateUsernameValidation duplecateUsernameValidation;

    @Before
    public void setUp() {

        Mockito.reset(userServicesMock);
        duplecateUsernameValidation.setUserServicesImpl(userServicesMock);
        userController.setUserServices(userServicesMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveUserProfileServicesTest() throws Exception
    {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/registration.jsp"))
                .andExpect(model().attribute("userForm", hasProperty("name", nullValue())))
                .andExpect(model().attribute("userForm", hasProperty("address", nullValue())));

        verifyZeroInteractions(userServicesMock);
    }


    @Test
    public void testSubmitUser_SuccessValidation()
            throws Exception {

        this.mockMvc.perform(
                post("/registration")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(buildUrlEncodedFormEntity(
                                "name", "jamileh",
                                "family", "bahri",
                                "username" , "jbahri" ,
                                "gender" ,Gender.FEMALE.toString()
                                ,"role" , "User" ,
                                "email" ,"jamileh@yahoo.com" ,
                                "password" , "123" ,"passwordConfirm" ,"123" ,
                                "phone" , "33444563" ,
                                "mobile" ,"9125566332" ,
                                "nationalId" ,"0480111111" ,
                                "address" , "tehran" ,
                                "birthday", "13701118"))
                        .sessionAttr("userForm", new UserProfileDto())


        )       .andExpect( view().name("usersHome") )
                .andExpect( status().isOk() )
                .andDo(print());

    }

    @Test
    public void testUpdateUser_SuccessValidation()
            throws Exception {

//        this.mockMvc.perform(
//                post("/registration")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .content(buildUrlEncodedFormEntity(
//                                "name", "jamileh",
//                                "family", "bahri",
//                                "username" , "jbahri" ,
//                                "gender" ,Gender.FEMALE.toString()
//                                ,"role" , "User" ,
//                                "email" ,"jamileh@yahoo.com" ,
//                                "password" , "123" ,"passwordConfirm" ,"123" ,
//                                "phone" , "33444563" ,
//                                "mobile" ,"9125566332" ,
//                                "nationalId" ,"0480111111" ,
//                                "address" , "tehran" ,
//                                "birthday", "13701118"))
//                        .sessionAttr("userForm", new UserProfileDto())
//
//
//        )       .andExpect( view().name("usersHome") )
//                .andExpect( status().isOk() )
//                .andDo(print());

    }


    private String buildUrlEncodedFormEntity(String... params) {
        if( (params.length % 2) > 0 ) {
            throw new IllegalArgumentException("Need to give an even number of parameters");
        }
        StringBuilder result = new StringBuilder();
        for (int i=0; i<params.length; i+=2) {
            if( i > 0 ) {
                result.append('&');
            }
            try {
                result.
                        append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
                        append('=').
                        append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
            }
            catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return result.toString();
    }


    @Test
    public void add_NewTodoEntry_ShouldAddTodoEntryAndRenderViewTodoEntryView() throws Exception {

        UserProfile userProfile = new UserProfile();

        Role role1 = new Role();
        WalletInvoices walletInvoices1 = new WalletInvoices();
        List<WalletInvoices> walletAmount1= new ArrayList<>();


        userProfile.setName("sina");
        userProfile.setFamily("taddayon");
        userProfile.setUsername("stadayyon");
        userProfile.setPassword("123");
        userProfile.setMobile("9121653365");
        userProfile.setPhone("22334455");
        userProfile.setEmail("stadayyon@gmail.com");
        userProfile.setAddress("narmak");
        userProfile.setBirthday(new Date(13621118));
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

        //comment this line, because change saveUser return type in service layer
//        when(userServicesMock.saveUser(isA(UserProfileDto.class))).thenReturn(userProfile);
//        String expectedRedirectViewPath = TestUtil.createRedirectViewPath(TodoController.REQUEST_MAPPING_TODO_VIEW);


                mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "jamileh")
                .param("family", "bahri")
                .sessionAttr("user", new UserProfileDto())
        )
                .andExpect(status().isMovedTemporarily())
//                .andExpect(view().name(expectedRedirectViewPath))
//                .andExpect(redirectedUrl("/todo/1"))
//                .andExpect(model().attribute(userController.get, is(ID.toString())))
//                .andExpect(flash().attribute(TodoController.FLASH_MESSAGE_KEY_FEEDBACK, is("Todo entry: title was added."))
 ;

        ArgumentCaptor<UserProfileDto> formObjectArgument = ArgumentCaptor.forClass(UserProfileDto.class);
        verify(userServicesMock, times(1)).saveUser(formObjectArgument.capture());
        verifyNoMoreInteractions(userServicesMock);

        UserProfileDto formObject = formObjectArgument.getValue();

        assertThat(formObject.getName(), is("jamileh"));
        assertNull(formObject.getId());
        assertThat(formObject.getFamily(), is("bahri"));
    }

}
