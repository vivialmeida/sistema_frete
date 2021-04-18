package br.com.frete.service;

import br.com.frete.mapper.CidadeMapper;
import br.com.frete.model.Cidade;
import br.com.frete.service.impl.CidadeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CidadeServiceTests {

    @Mock
    private CidadeMapper cidadeMapper;

    @InjectMocks
    private CidadeServiceImpl cidadeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve buscar a cidade com o maior numero de fretes")
    public void deveBuscarACidadeComOMaiorNumeroDeFretes() {

        Cidade cidade = cidadeService.buscaACidadeComOMaiorNumeroDeFretes();

        when(cidadeService.buscaACidadeComOMaiorNumeroDeFretes()).thenReturn(cidade);

        verify(cidadeMapper, times(1)).buscaACidadeComOMaiorNumeroDeFretes();

    }


}
