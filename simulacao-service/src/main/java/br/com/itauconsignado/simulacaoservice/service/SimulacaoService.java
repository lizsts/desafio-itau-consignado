package br.com.itauconsignado.simulacaoservice.service;

import br.com.itauconsignado.simulacaoservice.client.ClienteServiceClient;
import br.com.itauconsignado.simulacaoservice.client.dto.ClienteDTO;
import br.com.itauconsignado.simulacaoservice.controller.dto.SimulacaoResponse;
import br.com.itauconsignado.simulacaoservice.handler.exceptions.SegmentoInvalidoException;
import br.com.itauconsignado.simulacaoservice.model.Simulacao;
import br.com.itauconsignado.simulacaoservice.repository.SimulacaoRepository;
import br.com.itauconsignado.simulacaoservice.strategy.factory.TaxaConvenioFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimulacaoService {

    @Autowired
    private ClienteServiceClient clienteServiceClient;

    @Autowired
    private SimulacaoRepository simulacaoRepository;

    private final TaxaConvenioFactory taxaConvenioFactory;

    private ClienteDTO buscaClienteSimulacao(String cpf) {
        return clienteServiceClient.buscarClientePorCpf(cpf);
    }

    public SimulacaoResponse salvaSimulacao(Simulacao dadosSimulacao) throws Exception {
        ClienteDTO clienteSimulacao = buscaClienteSimulacao(dadosSimulacao.getCpf());

        var response = taxaConvenioFactory.selecionarStrategy(clienteSimulacao.getConvenio().toUpperCase())
                .calcularTaxaConvenio(dadosSimulacao, clienteSimulacao.isCorrentista());


        boolean quantidadeParcelasPermitido = verificadorNumeroParcelasPermitido(clienteSimulacao.getSegmento(),
                dadosSimulacao.getQuantidadeParcelas());

        if (!quantidadeParcelasPermitido) {
            throw new Exception("Quantidade de parcelas não permitida para segmento");
        }

        BigDecimal valorParcelaComTaxas = response.getValorTotalPagamento()
                .divide(BigDecimal.valueOf(dadosSimulacao.getQuantidadeParcelas()), 2, RoundingMode.HALF_UP);

        BigDecimal taxaAplicadaFormatada = response.getTaxaAplicada().multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);

        UUID simulacaoId = UUID.randomUUID();
        var simulacaoResponse = SimulacaoResponse.builder()
                .simulacaoId(simulacaoId)
                .taxaAplicada(taxaAplicadaFormatada)
                .valorTotalPagamento(response.getValorTotalPagamento().setScale(2, RoundingMode.FLOOR))
                .quantidadeParcelas(dadosSimulacao.getQuantidadeParcelas())
                .valorSolicitado(dadosSimulacao.getValorSolicitado())
                .valorParcela(valorParcelaComTaxas)
                .quantidadeParcelas(dadosSimulacao.getQuantidadeParcelas()).build();

        Simulacao simulacaoEntity = Simulacao.builder()
                .simulacaoId(simulacaoId)
                .cpf(clienteSimulacao.getCpf())
                .quantidadeParcelas(dadosSimulacao.getQuantidadeParcelas())
                .dataSimulacao(dadosSimulacao.getDataSimulacao())
                .convenio(clienteSimulacao.getConvenio())
                .valorParcela(simulacaoResponse.getValorParcela())
                .taxaAplicada(simulacaoResponse.getTaxaAplicada())
                .valorSolicitado(simulacaoResponse.getValorSolicitado())
                .valorTotalPagamento(simulacaoResponse.getValorTotalPagamento())
                .build();

        simulacaoRepository.save(simulacaoEntity);


        return simulacaoResponse;

    }

    private boolean verificadorNumeroParcelasPermitido(String segmento, Integer numeroParcelas) throws SegmentoInvalidoException {

        int maximoParcelas;
        if (segmento == null) {
            maximoParcelas = 12;
        } else {
            switch (segmento.toUpperCase()) {
                case "VAREJO" -> maximoParcelas = 24;
                case "UNICLASS" -> maximoParcelas = 36;
                case "PERSON" -> maximoParcelas = 48;
                default -> throw new SegmentoInvalidoException("Segmento inválido!");
            }
        }

        return numeroParcelas <= maximoParcelas;
    }

    public Simulacao buscaSimulacao(UUID simulacaoId) {
        return Optional.of(simulacaoRepository.findBySimulacaoId(simulacaoId)).get()
                .orElseThrow();
    }

}
