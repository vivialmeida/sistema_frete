package br.com.frete.service;

import br.com.frete.model.Frete;
import br.com.frete.model.dtos.FreteDTO;

import java.util.List;

public interface FreteService {

    void salvaFrete(Frete frete);

    List<Frete> buscaTodosOsFretes();

    Frete fromDTO(FreteDTO freteDTO);

    Frete buscaFreteDeMaiorValor();

    List<Frete> buscaFreteDoCliente(Integer clienteId);

}
