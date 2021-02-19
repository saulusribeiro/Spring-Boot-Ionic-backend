package br.com.srconsultoria.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.srconsultoria.cursomc.domain.Categoria;
import br.com.srconsultoria.cursomc.dto.CategoriaDTO;
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
		Categoria newObj = find(obj.getId());
		
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
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos associados");
		}
	  }
	public List<Categoria> findAll(){
		
		return repo.findAll();
	}
	// vamos utilizar a classe Page do SpringData para paginar a listagem de categorias
	
   public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ) {
	   
	   PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
			   orderBy);
	   return repo.findAll(pageRequest);
	   
	   
   }
   
   // Metodo auxiliar para instanciar uma Categoria a partir de um objeto DTO
   
   public Categoria fromDTO(CategoriaDTO objDTO) {
	   return new Categoria(objDTO.getId(),objDTO.getNome());
   }
		
   private void updateData(Categoria newObj, Categoria obj) {
	   
	   newObj.setNome(obj.getNome());
	  
   }
		
}
