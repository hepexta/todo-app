package com.hepexta.todoapp.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FrontendConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**.html").addResourceLocations("file:./static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
     //   registry.addViewController("/").setViewName("redirect:/login");
        super.addViewControllers(registry);
    }
}
