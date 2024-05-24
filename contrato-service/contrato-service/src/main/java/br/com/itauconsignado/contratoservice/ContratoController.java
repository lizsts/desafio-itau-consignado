package br.com.itauconsignado.contratoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/contrato")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postContratoSimulacao(@PathVariable(value = "id") UUID simulacaoId) throws Exception {
        contratoService.salvaContrato(simulacaoId);

    }

    @GetMapping
    @RequestMapping("lista-contratos")
    public ResponseEntity<List<Contrato>> buscarListaContratos() {
        return new ResponseEntity<>(contratoService.buscaListaComTodosContratos(), HttpStatus.OK);
    }
}
