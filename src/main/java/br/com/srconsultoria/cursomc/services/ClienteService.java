package br.com.srconsultoria.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.srconsultoria.cursomc.domain.Cliente;
import br.com.srconsultoria.cursomc.repositories.ClienteRepository;
import br.com.srconsultoria.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);
		
		// Utilizando uma função Lambda do Java 8
		// Para retornar o objeto ou a exceção para ser tratada por um Handler
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
}
