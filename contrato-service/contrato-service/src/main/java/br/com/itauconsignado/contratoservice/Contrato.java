package br.com.itauconsignado.contratoservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "CONTRATOS")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private UUID contratoId;
    private UUID simulacaoId;
    private LocalDate dataContrato;
}
