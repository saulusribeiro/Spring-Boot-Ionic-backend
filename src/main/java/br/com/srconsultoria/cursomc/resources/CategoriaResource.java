package br.com.srconsultoria.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.srconsultoria.cursomc.domain.Categoria;
import br.com.srconsultoria.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
		
	@RequestMapping(method = RequestMethod.POST)
	
	// o @RequestBody converte o objeto JSON para Categoria
	
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		
		obj = service.insert(obj);
		
		// Retorna a URI com o registro inserido no banco com o Id
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				                                     .path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
}
