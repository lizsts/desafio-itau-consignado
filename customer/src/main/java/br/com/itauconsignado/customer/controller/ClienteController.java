package br.com.itauconsignado.customer.controller;

import br.com.itauconsignado.customer.model.Cliente;
import br.com.itauconsignado.customer.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody @Valid Cliente cliente) {
        clienteService.save(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Cliente> buscarClientePorCpf(@RequestParam("CPF") String cpf) {
        return new ResponseEntity<>(clienteService.buscarClientePorCpf(cpf), HttpStatus.OK);

    }

    @GetMapping
    @RequestMapping("lista-clientes")
    public ResponseEntity<List<Cliente>> buscarListaClientes() {
        return new ResponseEntity<>(clienteService.buscaListaComTodosClientes(), HttpStatus.OK);
    }
}
