package br.com.srconsultoria.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.srconsultoria.cursomc.domain.Categoria;
import br.com.srconsultoria.cursomc.domain.Cidade;
import br.com.srconsultoria.cursomc.domain.Cliente;
import br.com.srconsultoria.cursomc.domain.Endereco;
import br.com.srconsultoria.cursomc.domain.Estado;
import br.com.srconsultoria.cursomc.domain.ItemPedido;
import br.com.srconsultoria.cursomc.domain.Pagamento;
import br.com.srconsultoria.cursomc.domain.PagamentoComBoleto;
import br.com.srconsultoria.cursomc.domain.PagamentoComCartao;
import br.com.srconsultoria.cursomc.domain.Pedido;
import br.com.srconsultoria.cursomc.domain.Produto;
import br.com.srconsultoria.cursomc.domain.enums.EstadoPagamento;
import br.com.srconsultoria.cursomc.domain.enums.TipoCliente;
import br.com.srconsultoria.cursomc.repositories.CategoriaRepository;
import br.com.srconsultoria.cursomc.repositories.CidadeRepository;
import br.com.srconsultoria.cursomc.repositories.ClienteRepository;
import br.com.srconsultoria.cursomc.repositories.EnderecoRepository;
import br.com.srconsultoria.cursomc.repositories.EstadoRepository;
import br.com.srconsultoria.cursomc.repositories.ItemPedidoRepository;
import br.com.srconsultoria.cursomc.repositories.PagamentoRepository;
import br.com.srconsultoria.cursomc.repositories.PedidoRepository;
import br.com.srconsultoria.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"ESCRITORIO");
		Categoria cat2 = new Categoria(null,"INFORMATICA");
		Categoria cat3 = new Categoria(null,"CAMA, MESA E BANHO");
		Categoria cat4 = new Categoria(null,"MODA PRAIA");
		Categoria cat5 = new Categoria(null,"ELETRODOMÉSTICOS");
		Categoria cat6 = new Categoria(null,"DECORAÇÃO");
		Categoria cat7 = new Categoria(null,"PERFUMARIA");
		
		
		Produto p1 = new Produto(null,"COMPUTADOR",2000.00);
		Produto p2 = new Produto(null,"IMPRESSORA",900.00);
		Produto p3 = new Produto(null,"MOUSE",80.00);
		Produto p4 = new Produto(null,"MESA DE ESCRITORIO",300.00);
		Produto p5 = new Produto(null,"TOALHA",50.00);
		Produto p6 = new Produto(null,"COLCHA",200.00);
		Produto p7 = new Produto(null,"TV true color",1200.00);
		Produto p8 = new Produto(null,"ROÇADEIRA",800.00);
		Produto p9 = new Produto(null,"ABAJOUR",180.00);
		Produto p10 = new Produto(null,"PENDENTE",180.00);
		Produto p11 = new Produto(null,"SHAMPOO",90.00);
		
		
		
		
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		
		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat3));
		p3.getCategorias().addAll(Arrays.asList(cat3));
		p3.getCategorias().addAll(Arrays.asList(cat4));
		p3.getCategorias().addAll(Arrays.asList(cat5));
		p3.getCategorias().addAll(Arrays.asList(cat6));
		p3.getCategorias().addAll(Arrays.asList(cat7));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
			
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		Cidade c1 =   new Cidade(null,"Uberlandia",est1);
		Cidade c2 =   new Cidade(null,"São Paulo",est2);
		Cidade c3 =   new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","mariasilva@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 303","Jardim","52011080", cli1, c1);
		Endereco e2 = new Endereco(null,"Av Matos","105","Sala 800","Centro","3877012", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2)); 
		
		// Criando uma máscara de formatação para instaciar uma data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, e2);
			
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1.00, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2.00, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1.00, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));		
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
