package br.com.srconsultoria.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.srconsultoria.cursomc.domain.Cliente;
import br.com.srconsultoria.cursomc.domain.enums.TipoCliente;
import br.com.srconsultoria.cursomc.dto.ClienteNewDTO;
import br.com.srconsultoria.cursomc.repositories.ClienteRepository;
import br.com.srconsultoria.cursomc.resources.exception.FieldMessage;
import br.com.srconsultoria.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
// inclua os testes aqui, inserindo erros na lista

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfcnpj())) {
			list.add(new FieldMessage("cpfcnpj", "CPF Inválido"));
		}
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfcnpj())) {
			list.add(new FieldMessage("cpfcnpj", "CNPJ Inválido"));
		}

		Cliente aux = repo.findByEmail(objDto.getEmail());

		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
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