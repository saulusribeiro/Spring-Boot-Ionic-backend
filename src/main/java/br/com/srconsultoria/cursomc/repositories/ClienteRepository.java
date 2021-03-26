package br.com.srconsultoria.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.srconsultoria.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	// O Spring Data implementa a busca apenas com o comando abaixo
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);

}
