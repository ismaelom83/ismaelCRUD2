package com.ismaelCRUD;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ismaelCRUD.upload.storage.StorageService;


/**
 * clase principal de nuestra aplicacion. que consiste en un crud para 
 * la gestion de usuarios, create, read, update, delete. 
 * @author Ismael Heras Salvador
 *
 * @version 1.0 
 *
 * @since 04/05/2020
 */
@SpringBootApplication
public class IsmaelCrudApplication {

	/**
	 * clase main de nuestra aplicacion desde aqui es donde arrancara nuestra aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(IsmaelCrudApplication.class, args);
	}

	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
