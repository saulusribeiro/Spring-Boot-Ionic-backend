package br.com.srconsultoria.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.srconsultoria.cursomc.domain.ItemPedido;
import br.com.srconsultoria.cursomc.domain.PagamentoComBoleto;
import br.com.srconsultoria.cursomc.domain.Pedido;
import br.com.srconsultoria.cursomc.domain.enums.EstadoPagamento;
import br.com.srconsultoria.cursomc.repositories.ItemPedidoRepository;
import br.com.srconsultoria.cursomc.repositories.PagamentoRepository;
import br.com.srconsultoria.cursomc.repositories.PedidoRepository;
import br.com.srconsultoria.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService    boletoService;  // Injeção do boletoService
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		
		// Utilizando uma função Lambda do Java 8
		// Para retornar o objeto ou a exceção para ser tratada por um Handler
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip :  obj.getItens()) {
			
			ip.setDesconto(0.00);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}
	
	
}
