package br.com.itauconsignado.simulacaoservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private boolean correntista;
    private String segmento;
    private String convenio;
}
