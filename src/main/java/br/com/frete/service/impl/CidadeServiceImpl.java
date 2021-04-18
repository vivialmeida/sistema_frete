package br.com.frete.service.impl;

import br.com.frete.mapper.CidadeMapper;
import br.com.frete.model.Cidade;
import br.com.frete.service.CidadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class CidadeServiceImpl implements CidadeService {

    private final CidadeMapper cidadeMapper;

    public CidadeServiceImpl(CidadeMapper cidadeMapper) {
        this.cidadeMapper = cidadeMapper;
    }

    @Override
    public void salvaCidade(@NotNull Cidade cidade) {
        cidadeMapper.salvaCidade(cidade);
    }

    @Override
    public List<Cidade> buscaTodasAsCidades() {
        return cidadeMapper.buscaTodasAsCidades();
    }

    @Override
    public Cidade buscaCidadePor(Integer id) {
        return cidadeMapper.buscaCidadePor(id);
    }

    @Override
    public List<Cidade> buscaPor(String nome) {
        return cidadeMapper.buscaPor(nome);
    }

    @Override
    public Cidade buscaACidadeComOMaiorNumeroDeFretes() {
        return cidadeMapper.buscaACidadeComOMaiorNumeroDeFretes();
    }


}
