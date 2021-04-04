package br.com.srconsultoria.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.srconsultoria.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto,Date instanteDoPagamento) {
	// calcula vencimento do boleto
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPagamento);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
	}

}
