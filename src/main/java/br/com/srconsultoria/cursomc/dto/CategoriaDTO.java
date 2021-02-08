package br.com.srconsultoria.cursomc.dto;

import java.io.Serializable;

import br.com.srconsultoria.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String  nome;
	
	
	// Construtor vazio
	
	public CategoriaDTO() {
		
	}
	
	// Construtor a partir  de uma classe de dominio
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	
	}
	
	// getters and setters
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	


}
