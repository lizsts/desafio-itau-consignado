package br.com.itauconsignado.simulacaoservice.strategy;

import br.com.itauconsignado.simulacaoservice.controller.dto.SimulacaoResponse;
import br.com.itauconsignado.simulacaoservice.model.Simulacao;

public interface TaxaConvenioStrategy {

    SimulacaoResponse calcularTaxaConvenio(Simulacao dadosSimulacao, boolean clienteCorrentista);
    String obterTipoConvenio();
}
