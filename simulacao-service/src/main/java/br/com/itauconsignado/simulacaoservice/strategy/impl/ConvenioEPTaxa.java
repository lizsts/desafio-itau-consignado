package br.com.itauconsignado.simulacaoservice.strategy.impl;

import br.com.itauconsignado.simulacaoservice.controller.dto.SimulacaoResponse;
import br.com.itauconsignado.simulacaoservice.model.Simulacao;
import br.com.itauconsignado.simulacaoservice.strategy.SimulacaoConstants;
import br.com.itauconsignado.simulacaoservice.strategy.TaxaConvenioStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ConvenioEPTaxa implements TaxaConvenioStrategy {


    private static final BigDecimal DESCONTO_CORRENTISTA = BigDecimal.valueOf(0.95);
    private static final BigDecimal TAXA_CONVENIO_EP = SimulacaoConstants.TAXA_CONVENIO_EP.divide(BigDecimal.valueOf(100), 3, RoundingMode.FLOOR);

    @Override
    public SimulacaoResponse calcularTaxaConvenio(Simulacao dadosSimulacao, boolean clienteCorrentista) {
        BigDecimal taxaAplicada = calcularTaxaAplicada(clienteCorrentista);
        BigDecimal valorPagamentoComTaxas = calcularValorPagamentoComTaxas(dadosSimulacao, taxaAplicada);

        return SimulacaoResponse.builder()
                .taxaAplicada(taxaAplicada)
                .valorTotalPagamento(valorPagamentoComTaxas)
                .build();

    }

    @Override
    public String obterTipoConvenio() {
        return SimulacaoConstants.CONVENIO_EP;
    }

    private BigDecimal calcularTaxaAplicada(boolean clienteCorrentista) {
        return clienteCorrentista ? TAXA_CONVENIO_EP.multiply(DESCONTO_CORRENTISTA)
                .setScale(4, RoundingMode.FLOOR) : TAXA_CONVENIO_EP;
    }

    private BigDecimal calcularValorPagamentoComTaxas(Simulacao dadosSimulacao, BigDecimal taxaAplicada) {
        return dadosSimulacao.getValorSolicitado().multiply(
                (taxaAplicada.multiply(BigDecimal.valueOf(dadosSimulacao.getQuantidadeParcelas())))
                        .add(BigDecimal.valueOf(1))).setScale(4, RoundingMode.FLOOR);
    }

}
