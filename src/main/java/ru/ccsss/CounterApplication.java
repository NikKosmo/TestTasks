package ru.ccsss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ccsss.dao.CounterDAO;
import ru.ccsss.servlet.CounterServlet;

@SpringBootApplication
public class CounterApplication extends SpringBootServletInitializer {

    @Autowired
    private CounterDAO counterDAO;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CounterApplication.class);
	}

	@Bean
	public ServletRegistrationBean counterServlet() {
        ServletRegistrationBean servlet = new ServletRegistrationBean();
        servlet.addUrlMappings("/counter");
        servlet.setServlet(new CounterServlet(counterDAO));
        return servlet;
	}

	public static void main(String[] args) {
		SpringApplication.run(CounterApplication.class, args);
	}

}
