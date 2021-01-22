package br.com.srconsultoria.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import br.com.srconsultoria.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	
	public PagamentoComBoleto() {
		
	}

    // Como se trata de uma subclasse o construtor com atributos será gerado a partir
	// da Super Classe
	
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento   = dataPagamento;
		this.dataVencimento  = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
	
	

}
