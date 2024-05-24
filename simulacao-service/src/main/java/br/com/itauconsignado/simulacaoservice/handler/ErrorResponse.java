package br.com.itauconsignado.simulacaoservice.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Builder
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime timestamp;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String error;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String path;
}
