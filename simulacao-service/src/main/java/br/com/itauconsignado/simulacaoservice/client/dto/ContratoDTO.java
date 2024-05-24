package br.com.itauconsignado.simulacaoservice.client.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDTO {

    private UUID simulacaoId;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataSimulacao = LocalDate.now();

}
