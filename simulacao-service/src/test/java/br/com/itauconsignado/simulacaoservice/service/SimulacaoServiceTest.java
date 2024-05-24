//package br.com.itauconsignado.simulacaoservice.service;
//
//import br.com.itauconsignado.simulacaoservice.client.ClienteService;
//import br.com.itauconsignado.simulacaoservice.client.dto.ClienteDTO;
//import br.com.itauconsignado.simulacaoservice.dto.SimulacaoResponse;
//import br.com.itauconsignado.simulacaoservice.model.Simulacao;
//import br.com.itauconsignado.simulacaoservice.repository.SimulacaoRepository;
//import br.com.itauconsignado.simulacaoservice.strategy.SimulacaoConstants;
//import br.com.itauconsignado.simulacaoservice.strategy.TaxaConvenioStrategy;
//import br.com.itauconsignado.simulacaoservice.strategy.factory.TaxaConvenioFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyBoolean;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class SimulacaoServiceTest {
//
//    @InjectMocks
//    private SimulacaoService simulacaoService;
//
//    @Mock
//    private ClienteService clienteService;
//
//    @Mock
//    private SimulacaoRepository simulacaoRepository;
//
//    @Mock
//    private TaxaConvenioFactory taxaConvenioFactory;
//
//    @Mock
//    private TaxaConvenioStrategy taxaConvenioStrategy;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void salvaSimulacaoComSucesso() throws Exception {
//        // Arrange
//        String cpf = "12345678900";
//        String segmento = "VAREJO";
//        String convenio = "CONVENIO";
//        boolean isCorrentista = true;
//        int quantidadeParcelas = 12;
//        BigDecimal valorSolicitado = new BigDecimal("1000.00");
//        BigDecimal valorTotalPagamento = new BigDecimal("1200.00");
//        BigDecimal taxaAplicada = new BigDecimal("0.02");
//
//        ClienteDTO clienteDTO = new ClienteDTO();
//        clienteDTO.setCpf(cpf);
//        clienteDTO.setSegmento(segmento);
//        clienteDTO.setConvenio(convenio);
//        clienteDTO.setCorrentista(isCorrentista);
//
//        Simulacao simulacao = new Simulacao();
//        simulacao.setCpf(cpf);
//        simulacao.setQuantidadeParcelas(quantidadeParcelas);
//        simulacao.setValorSolicitado(valorSolicitado);
//        simulacao.setDataSimulacao(LocalDate.now());
//
//        var response = new SimulacaoResponse();
//        response.setValorTotalPagamento(valorTotalPagamento);
//        response.setTaxaAplicada(taxaAplicada);
//
//        when(clienteService.buscarClientePorCpf(any(String.class))).thenReturn(clienteDTO);
//        when(taxaConvenioFactory.selecionarStrategy(SimulacaoConstants.CONVENIO_INSS)).thenReturn(taxaConvenioStrategy);
//        when(taxaConvenioStrategy.calcularTaxaConvenio(any(Simulacao.class), anyBoolean())).thenReturn(response);
//
//        // Act
//        SimulacaoResponse resultado = simulacaoService.salvaSimulacao(simulacao);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals(valorTotalPagamento.setScale(2, BigDecimal.ROUND_FLOOR), resultado.getValorTotalPagamento());
//        assertEquals(taxaAplicada.multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP), resultado.getTaxaAplicada());
//        assertEquals(quantidadeParcelas, resultado.getQuantidadeParcelas());
//        assertEquals(valorSolicitado, resultado.getValorSolicitado());
//
//        verify(simulacaoRepository, times(1)).save(any(Simulacao.class));
//    }
//
//    @Test
//    void salvaSimulacaoComExcecaoDeQuantidadeParcelas() {
//        // Arrange
//        String cpf = "12345678900";
//        String segmento = "PERSON";
//        String convenio = "CONVENIO";
//        boolean isCorrentista = true;
//        int quantidadeParcelas = 50;
//        BigDecimal valorSolicitado = new BigDecimal("1000.00");
//
//        ClienteDTO clienteDTO = new ClienteDTO();
//        clienteDTO.setCpf(cpf);
//        clienteDTO.setSegmento(segmento);
//        clienteDTO.setConvenio(convenio);
//        clienteDTO.setCorrentista(isCorrentista);
//
//        Simulacao simulacao = new Simulacao();
//        simulacao.setCpf(cpf);
//        simulacao.setQuantidadeParcelas(quantidadeParcelas);
//        simulacao.setValorSolicitado(valorSolicitado);
//        simulacao.setDataSimulacao(LocalDate.now());
//
//        when(clienteService.buscarClientePorCpf(any(String.class))).thenReturn(clienteDTO);
//
//        // Act & Assert
//        Exception exception = assertThrows(Exception.class, () -> {
//            simulacaoService.salvaSimulacao(simulacao);
//        });
//
//        String expectedMessage = "Quantidade de parcelas não permitida para segmento: " + segmento;
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//        verify(simulacaoRepository, never()).save(any(Simulacao.class));
//    }
//}
//
