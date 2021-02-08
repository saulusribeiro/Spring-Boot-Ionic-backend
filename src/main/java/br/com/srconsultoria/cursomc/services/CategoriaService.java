package br.com.srconsultoria.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.srconsultoria.cursomc.domain.Categoria;
import br.com.srconsultoria.cursomc.repositories.CategoriaRepository;
import br.com.srconsultoria.cursomc.services.exceptions.DataIntegrityException;
import br.com.srconsultoria.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		
		// Utilizando uma função Lambda do Java 8
		// Para retornar o objeto ou a exceção para ser tratada por um Handler
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
	public Categoria insert(Categoria obj) {
		// O obj.setId(null) vai garantir que a operação será de inserção e não de update
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		// o mesmo metodo save usado no insert, quando o id é diferente de nulo ele atualiza o objeto
		return repo.save(obj);
		
	}
	
	public void delete(Integer id) {
	     find(id);
	     
	     // se o id não existir o find dispara uma exceção antes
	     
	     try {
	    	 repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos associados");
		}
	  }
	public List<Categoria> findAll(){
		
		return repo.findAll();
	}
	
	
		
		
		
}
