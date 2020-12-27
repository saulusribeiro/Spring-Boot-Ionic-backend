package br.com.srconsultoria.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.srconsultoria.cursomc.domain.Categoria;
import br.com.srconsultoria.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"ARMARINHO");
		Categoria cat2 = new Categoria(null,"CAMA E MESA");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}

}
