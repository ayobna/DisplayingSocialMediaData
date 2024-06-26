package com.tsfn.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	private String devUrl = "http://localhost:9090";
	
	
	@Bean
	public OpenAPI myOpenAPI()
	{
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Server URL for development Env");
		
		Contact contact = new Contact();
		contact.setEmail("Kabhad82@gmail.com");
		contact.setName("Daoud");
		contact.setUrl("http://www.Kabhad2.com");
		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
		
		Info info=  new Info().title("coupons Managmenet API").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage coupons.").termsOfService("https://www.Kabhad82.com/terms")
				.license(mitLicense);
		
		return new OpenAPI().info(info).servers(List.of(devServer));
	}
	
}
