package br.com.srconsultoria.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.srconsultoria.cursomc.domain.Categoria;
import br.com.srconsultoria.cursomc.dto.CategoriaDTO;
import br.com.srconsultoria.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
		
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @Validated @RequestBody CategoriaDTO objDTO){
		
		// converte um DTO em um objeto Categoria
		Categoria obj = service.fromDTO(objDTO);

		obj = service.insert(obj);
		
		// Retorna a URI com o registro inserido no banco com o Id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				                                     .path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	
	public<Void> ResponseEntity update( @PathVariable Integer id, @Validated @RequestBody CategoriaDTO objDTO) {
		
		Categoria obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		// o método atualiza e retorna sem conteudo
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delte(@PathVariable Integer id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
		
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<Categoria> lista = service.findAll();
		
		// Msssete : Percorrer a lista utilizando o recurso do JAVA 8 Stream, e converte uma lista para outra lista
		
		List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new  CategoriaDTO(obj)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(listaDTO);
	}

	
	// Para listar paginado com parâmetros Exemplo :
	// http://localhost:8080/categorias/page?linesPerPage=3&direction=DESC
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue ="0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue ="24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue ="nome") String orderBy,
			@RequestParam(value = "direction", defaultValue ="ASC") String direction ) {
		
		Page<Categoria> lista = service.findPage(page, linesPerPage, orderBy, direction);
		
		// Msssete : Percorrer a lista utilizando o recurso do JAVA 8 Stream, e converte uma lista para outra lista
		
		Page<CategoriaDTO> listaDTO = lista.map(obj -> new  CategoriaDTO(obj));
				
		return ResponseEntity.ok().body(listaDTO);
	}

}
