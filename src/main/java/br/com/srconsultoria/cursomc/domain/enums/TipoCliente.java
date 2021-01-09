package br.com.srconsultoria.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Jurídica");
	
	private Integer cod;
	private String descricao;
	
	private TipoCliente(Integer cod, String descricao) {
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
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		// Este for varrerá o enum para encontrar ou não o tipo correspondente e retornar
		// o enum  correspondente ao cod
		
		for (TipoCliente s : TipoCliente.values()) {
		  if(cod.equals(s.getCod())) {
			  return s;  
		  }
		}
		
		throw new IllegalArgumentException("Id Inválido: "+cod);
	}
}
