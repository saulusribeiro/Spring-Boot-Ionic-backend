package br.com.srconsultoria.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.srconsultoria.cursomc.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	// Injetando a dependencia do DBService
	@Autowired
	private DBService dbService;

	public boolean instantiateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		return true;
	}
}
