package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
//        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        Produto p = produtoDAO.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> produtos = produtoDAO.buscarTodos();
        produtos.forEach(prod -> System.out.println(prod.getNome()));

        List<Produto> produtos2 = produtoDAO.buscarPorNome("Samsung");
        produtos2.forEach(prod -> System.out.println(prod.getNome()));

        List<Produto> produtos3 = produtoDAO.buscarPorNomeDaCategoria("Celulares");
        produtos3.forEach(prod -> System.out.println(prod.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Smart TV");
        System.out.println(precoDoProduto);

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal",
                new BigDecimal("1000"), celulares);

        EntityManager em = JPAUtil.getEntityManager();

        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);

        Categoria cat = em.find(Categoria.class, new
                CategoriaId("CELULARES", "xpto"));

        em.getTransaction().commit();

        em.close();
    }
}
