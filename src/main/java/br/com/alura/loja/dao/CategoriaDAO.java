package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class CategoriaDAO {

    private EntityManager em;

    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }
    /*
    O que o merge faz é nada mais do que buscar a entidade no banco
    de dados para para voltar para um estado Managed, gerenciada pela JPA
     */
    public void atualizar(Categoria categoria){
        this.em.merge(categoria);
    }

    public void remover(Categoria categoria){
        Categoria categoria2 = em.merge(categoria);
        this.em.remove(categoria2);
        // Metodo contains (Object o) -> Check if the instance is a managed entity instance belonging to the current persistence context.
        // Outra forma de fazer direto, antes de remover ele verifica se a entidade está managed, se não estiver ele
        // faz o merge para passar o estado para managed
        // this.em.remove(em.contains(categoria) ? categoria : em.merge(categoria));
    }




}
