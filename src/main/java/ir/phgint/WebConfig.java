//package ir.phgint;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//@EnableWebMvc
//@Configuration
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//   @Override
//   public void addViewControllers(ViewControllerRegistry registry) {
//      registry.addViewController("/").setViewName("index");
//   }
//
//   @Bean
//   public ViewResolver viewResolver() {
//      InternalResourceViewResolver bean = new InternalResourceViewResolver();
//
//      bean.setViewClass(JstlView.class);
//      bean.setPrefix("/WEB-INF/jsp/");
//      bean.setSuffix(".jsp");
//
//      return bean;
//   }
//
//   @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/webjars/**", "/static/**")
//                .addResourceLocations("/webjars/", "/resources/static/");
//
//
//    }
//
////   @Override
////   public void addResourceHandlers(ResourceHandlerRegistry registry) {
////      registry.addResourceHandler("/static/**")
////              .addResourceLocations("/resources/", "/webjars/");
//////              .resourceChain(true)
//////              .addResolver(new WebJarsResourceResolver());
////
////   }
//}