package br.com.srconsultoria.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.srconsultoria.cursomc.domain.Cliente;
import br.com.srconsultoria.cursomc.dto.ClienteDTO;
import br.com.srconsultoria.cursomc.repositories.ClienteRepository;
import br.com.srconsultoria.cursomc.services.exceptions.DataIntegrityException;
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
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		
		// atualize os dados de newObj com base no argumento obj
		updateData(newObj, obj);
		// o mesmo metodo save usado no insert, quando o id é diferente de nulo ele atualiza o objeto
		return repo.save(obj);
		
	}
	
	public void delete(Integer id) {
	     find(id);
	     
	     // se o id não existir o find dispara uma exceção antes
	     
	     try {
	    	 repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	  }
	public List<Cliente> findAll(){
		
		return repo.findAll();
	}
	// vamos utilizar a classe Page do SpringData para paginar a listagem de categorias
	
   public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ) {
	   
	   PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
			   orderBy);
	   return repo.findAll(pageRequest);
	   
	   
   }
   
   private void updateData(Cliente newObj, Cliente obj) {
	   
	   newObj.setNome(obj.getNome());
	   newObj.setEmail(obj.getEmail());
   }
   
   // Metodo auxiliar para instanciar uma Cliente a partir de um objeto DTO
   
   public Cliente fromDTO(ClienteDTO objDTO) {
	   
	   // o cpf e o tipo do cliente sao nulos pois nao existem no DTO
	   
	   return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(),null, null);
   }
   
 	

}
