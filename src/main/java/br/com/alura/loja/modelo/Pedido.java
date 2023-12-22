package br.com.alura.loja.modelo;

import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    /*
    Nesse caso, como é um relacionamento bidirencional, deve-se colocar esse mappedBy para indicar
    que o relacionamento já foi mapeado no atributo pedido da classe ItemPedido
    e o JPA não criar uma tabela a mais para mapear esse relacionamento
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(){}

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item){
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
