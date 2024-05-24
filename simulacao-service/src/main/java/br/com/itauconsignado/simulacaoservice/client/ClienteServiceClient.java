package br.com.itauconsignado.simulacaoservice.client;

import br.com.itauconsignado.simulacaoservice.client.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cliente", url = "http://localhost:8081/")
public interface ClienteServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/v1/cliente")
    ClienteDTO buscarClientePorCpf(@RequestParam("CPF") String cpf);

}
