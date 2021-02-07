package br.com.srconsultoria.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import br.com.srconsultoria.cursomc.domain.Pedido;
import br.com.srconsultoria.cursomc.repositories.PedidoRepository;
import br.com.srconsultoria.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		
		// Utilizando uma função Lambda do Java 8
		// Para retornar o objeto ou a exceção para ser tratada por um Handler
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
}
