package br.com.srconsultoria.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.srconsultoria.cursomc.domain.Cliente;
import br.com.srconsultoria.cursomc.dto.ClienteDTO;
import br.com.srconsultoria.cursomc.repositories.ClienteRepository;
import br.com.srconsultoria.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(ClienteUpdate ann) {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		// Para pegar os atributos que vem na requisição, vamos utilizar a estrutura Map para retirar da URI o id do Cliente
		// Funciona como na leitura de atributos do JSON
		
		@SuppressWarnings("unused")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));
        
		List<FieldMessage> list = new ArrayList<>();
// inclua os testes aqui, inserindo erros na lista

		Cliente aux = repo.findByEmail(objDto.getEmail());
		
// Vai estourar erro quando tentar atualizar o email, com um email pertencente a outro cliente		

		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente associado a outro Cliente"));
		}

		for (FieldMessage e : list) {
			// Os comandos abaixo transporta as mensagens e campos personalizados para
			// validação através do framework
			// Não se preocupar em decorar os comandos
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}