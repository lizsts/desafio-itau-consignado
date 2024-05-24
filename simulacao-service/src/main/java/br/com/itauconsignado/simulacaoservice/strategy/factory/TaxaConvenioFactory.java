package br.com.itauconsignado.simulacaoservice.strategy.factory;

import br.com.itauconsignado.simulacaoservice.strategy.TaxaConvenioStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TaxaConvenioFactory {

    private final Map<String, TaxaConvenioStrategy> strategies = new HashMap<>();

    public TaxaConvenioFactory(Set<TaxaConvenioStrategy> strategySet) {
        strategySet.forEach(s -> strategies.put(s.obterTipoConvenio(), s));
    }

    public TaxaConvenioStrategy selecionarStrategy(String convenio) throws Exception {
        TaxaConvenioStrategy strategy = strategies.get(convenio);

        if(strategy == null) {
            throw new Exception("Convênio inválido.");
        }

        return strategy;
    }
}
