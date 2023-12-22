package br.com.alura.loja.vo;

import java.time.LocalDate;
/*
Classe do tipo Vo - Value Object , apenas um objeto de valor
 */
public record RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida,
                                  LocalDate dataUltimaVenda) {
}
