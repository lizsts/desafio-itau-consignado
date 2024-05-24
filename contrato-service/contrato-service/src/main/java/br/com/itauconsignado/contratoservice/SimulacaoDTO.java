package br.com.itauconsignado.contratoservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulacaoDTO {

    private UUID simulacaoId;
    private BigDecimal valorSolicitado;
    private BigDecimal taxaAplicada;
    private Integer quantidadeParcelas;
    private BigDecimal valorParcela;
    private BigDecimal valorTotalPagamento;
}
