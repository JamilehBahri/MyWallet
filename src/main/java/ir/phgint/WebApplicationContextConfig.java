package ir.phgint;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.concurrent.TimeUnit;

@Configuration
//@EnableWebMvc
public class WebApplicationContextConfig {

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean(name = "mapperFactoryBean")
    public MapperFactory factoryBean(){
        return new DefaultMapperFactory.Builder().build();
    }

//    @Bean
//    public UserProfileValidator userRegistrationValidation () {
//        Set<Validator> springValidators = new HashSet<>();
////        springValidators.add(new UserRegistrationValidation());
//
//        UserProfileValidator userProfileValidator = new UserProfileValidator();
//        userProfileValidator.setSpringValidators(springValidators);
//
//        return userProfileValidator;
//    }



    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds((int) TimeUnit.HOURS.toSeconds(1));
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }


}
