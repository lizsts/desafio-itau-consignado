package br.com.itauconsignado.contratoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ContratoService {
    @Autowired
    private SimulacaoServiceClient simulacaoServiceClient;

    @Autowired
    private ContratoRepository contratoRepository;

    public void salvaContrato(UUID simulacaoId) throws Exception {

        if (simulacaoServiceClient.getSimulacaoContrato(simulacaoId) == null) {
            throw new Exception("Simulação não encontrada.");
        }
        contratoRepository.save(Contrato.builder()
                .contratoId(UUID.randomUUID())
                .simulacaoId(simulacaoId)
                .dataContrato(LocalDate.now()).build());

    }

    public List<Contrato> buscaListaComTodosContratos() {
        return contratoRepository.findAll();
    }


}
