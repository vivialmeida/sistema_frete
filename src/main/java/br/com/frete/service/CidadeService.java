package br.com.frete.service;

import br.com.frete.model.Cidade;

import java.util.List;

public interface CidadeService {

    void salvaCidade(Cidade cidade);

    List<Cidade> buscaTodasAsCidades();

    Cidade buscaCidadePor(Integer id);

    List<Cidade> buscaPor(String nome);

    Cidade buscaACidadeComOMaiorNumeroDeFretes();
}
