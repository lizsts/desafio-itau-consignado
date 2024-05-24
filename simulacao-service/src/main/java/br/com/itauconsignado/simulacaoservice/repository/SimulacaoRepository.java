package br.com.itauconsignado.simulacaoservice.repository;

import br.com.itauconsignado.simulacaoservice.model.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {

    Optional<Simulacao> findBySimulacaoId(UUID simulacaoId);
}
