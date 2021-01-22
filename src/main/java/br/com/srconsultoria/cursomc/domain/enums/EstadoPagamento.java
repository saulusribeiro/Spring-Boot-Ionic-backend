package br.com.srconsultoria.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private Integer cod;
	private String descricao;
	
	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
		
	}
	
	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	// Método estático para retornar o enum após varredura
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		// Este for varrerá o enum para encontrar ou não o tipo correspondente e retornar
		// o enum  correspondente ao cod
		
		for (EstadoPagamento s : EstadoPagamento.values()) {
		  if(cod.equals(s.getCod())) {
			  return s;  
		  }
		}
		
		throw new IllegalArgumentException("Id Inválido: "+cod);
	}

}
