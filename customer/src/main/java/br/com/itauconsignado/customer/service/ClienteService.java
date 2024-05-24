package br.com.itauconsignado.customer.service;

import br.com.itauconsignado.customer.model.Cliente;
import br.com.itauconsignado.customer.repository.ClienteRepository;
import br.com.itauconsignado.customer.service.util.CPFUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CPFUtil validadorCpf;

    public void save(Cliente cliente) {

        if (!validadorCpf.validaMascaraCPF(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF está inválido! Digite um CPF válido.");
        }

        clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorCpf(String cpf) {

        if (!validadorCpf.validaMascaraCPF(cpf)) {
            throw new IllegalArgumentException("CPF está inválido! Digite um CPF válido.");
        }
        var optionalCliente = Optional.of(clienteRepository.getByCpf(cpf));

        //tratar com exceção personalizada
        return optionalCliente.get().orElseThrow(NullPointerException::new);
    }

    public List<Cliente> buscaListaComTodosClientes() {
        return clienteRepository.findAll();
    }
}
