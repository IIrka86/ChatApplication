package kov.irok.WebClient;

import kov.irok.WebClient.config.SpringFtlConfig;
import kov.irok.WebClient.config.SwaggerConfig;
import kov.irok.WebClient.consoleServer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{WebClientApplication.class, SpringFtlConfig.class, SwaggerConfig.class}, args);
		//Server server = new Server();
	}

}
