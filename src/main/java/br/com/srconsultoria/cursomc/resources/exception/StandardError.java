package br.com.srconsultoria.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable{
	// Registra o instante e causa do erro
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer status; // status http
	private String  msg;
	private Long    timestamp;
	
	// Método Construtor
	
	public StandardError(Integer status, String msg, Long timestamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timestamp = timestamp;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
