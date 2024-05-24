package br.com.itauconsignado.contratoservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;


@FeignClient(name = "contrato", url = "http://localhost:8083/")
public interface SimulacaoServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/v1/simulacao/{id}")
    SimulacaoDTO getSimulacaoContrato(@PathVariable(value = "id") UUID simulacaoId);

}
