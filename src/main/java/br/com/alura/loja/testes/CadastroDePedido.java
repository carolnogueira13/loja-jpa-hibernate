package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        criaPedido();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);

        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("Valor total dos pedidos: " + totalVendido);

        List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendas();
        relatorio.forEach(System.out::println);

        produtoDAO.buscaPorParametrosComCriteria(null, new BigDecimal("800"), null);

        em.close();

    }

    private static void criaPedido() {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        Produto produto = produtoDAO.buscarPorId(8L);
        Produto produto2 = produtoDAO.buscarPorId(5L);

        Cliente cliente = new Cliente("Rodrigo", "12345678998");
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        pedido.adicionarItem(new ItemPedido(5, pedido, produto2));

        PedidoDAO pedidoDAO = new PedidoDAO(em);
        em.getTransaction().begin();
        clienteDAO.cadastrar(cliente);
        pedidoDAO.cadastrar(pedido);
        em.getTransaction().commit();

        em.close();
    }
}
