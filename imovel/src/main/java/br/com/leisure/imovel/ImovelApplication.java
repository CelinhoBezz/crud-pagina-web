package br.com.leisure.imovel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ImovelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImovelApplication.class, args);
	}
	
	@GetMapping("/")
	public String helloWorld () {
		return "Hello World!";
	}

}
