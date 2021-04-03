package br.com.srconsultoria.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.srconsultoria.cursomc.domain.Cliente;
import br.com.srconsultoria.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento do nome obrigatório")
	@Length(min=5, max=1120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String  nome;
	
	@NotEmpty(message="Preenchimento do E-mail obrigatório ")
	@Email(message="E-mail Inválido")
	private String  email;
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Cliente obj) {
		
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
