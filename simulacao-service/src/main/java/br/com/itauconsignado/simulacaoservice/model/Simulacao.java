package br.com.itauconsignado.simulacaoservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

import static org.springframework.data.util.CastUtils.cast;

@Table(name = "SIMULACOES")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Simulacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private UUID simulacaoId;
    @NotBlank
    private String cpf;
    private String convenio;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataSimulacao = LocalDate.now();
    @NotNull
    private BigDecimal valorSolicitado;
    private BigDecimal taxaAplicada;
    @Column(length = 2)
    private Integer quantidadeParcelas;
    private BigDecimal valorParcela;
    private BigDecimal valorTotalPagamento;

}
