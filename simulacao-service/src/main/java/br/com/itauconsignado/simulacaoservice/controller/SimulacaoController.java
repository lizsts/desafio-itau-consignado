package br.com.itauconsignado.simulacaoservice.controller;

import br.com.itauconsignado.simulacaoservice.controller.dto.SimulacaoResponse;
import br.com.itauconsignado.simulacaoservice.model.Simulacao;
import br.com.itauconsignado.simulacaoservice.service.SimulacaoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/simulacao")
@AllArgsConstructor
public class SimulacaoController {

        @Autowired
        private SimulacaoService simulacaoService;

        @PostMapping
        public ResponseEntity<SimulacaoResponse> postSimulacao(@RequestBody Simulacao simulacao) throws Exception {
                return new ResponseEntity<>(simulacaoService.salvaSimulacao(simulacao), HttpStatus.CREATED);
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<Simulacao> getSimulacaoContrato(@PathVariable(value="id") UUID simulacaoId) throws Exception {
                return new ResponseEntity<>(simulacaoService.buscaSimulacao(simulacaoId), HttpStatus.OK);

        }

}
