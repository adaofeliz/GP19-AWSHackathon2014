package org.gp19.analysis;

import org.gp19.analysis.service.filter.SimpleCORSFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;


public class AppInitializer implements WebApplicationInitializer {

    private static final String CONFIG_LOCATION = "org.gp19.analysis.config";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        System.out.println("Initializing Application for " + servletContext.getServerInfo());

        // Create ApplicationContext
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setConfigLocation(CONFIG_LOCATION);

        // Add the servlet mapping manually and make it initialize automatically
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        // ServletRegistration
        ServletRegistration.Dynamic servlet = servletContext.addServlet("mvc-dispatcher", dispatcherServlet);
        servlet.addMapping("/");
        servlet.setAsyncSupported(true);
        servlet.setLoadOnStartup(1);

        // FilterRegistration
        FilterRegistration.Dynamic filterReg = servletContext.addFilter("simpleCORSFilter", SimpleCORSFilter.class);
        filterReg.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}