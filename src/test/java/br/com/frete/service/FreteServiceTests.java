package br.com.frete.service;

import br.com.frete.mapper.FreteMapper;
import br.com.frete.model.Frete;
import br.com.frete.service.exceptions.FreteNaoRealizadoException;
import br.com.frete.service.impl.FreteServiceImpl;
import br.com.frete.builder.FreteBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FreteServiceTests {

    @Mock
    private FreteMapper freteMapper;

    @Mock
    private ClienteService clienteService;

    @Mock
    private CidadeService cidadeService;

    @InjectMocks
    private FreteServiceImpl freteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Espera um FreteNaoRealizadoException")
    public void deveLancarUmaExceptionCasoNaoExistaCliente() {

        Frete frete = FreteBuilder.frete01().build();
        String mensagem = "Deve lançar um FreteNaoRealizadoException";
        int clienteId = frete.getCliente().getId();

        when(clienteService.buscaClientePor(clienteId)).thenReturn(null);

        Assertions.assertThrows(FreteNaoRealizadoException.class, () -> freteService.salvaFrete(frete), mensagem);
    }

    @Test
    @DisplayName("Espera um FreteNaoRealizadoException")
    public void deveLancarUmExceptionCasoNaoExistaCidade() {

        Frete frete = FreteBuilder.frete01().build();
        String mensagem = "Deve lançar um FreteNaoRealizadoException";

        int cidadeId = frete.getCidade().getId();
        int clienteId = frete.getCliente().getId();

        when(cidadeService.buscaCidadePor(cidadeId)).thenReturn(frete.getCidade());
        when(clienteService.buscaClientePor(clienteId)).thenReturn(null);

        assertThrows(FreteNaoRealizadoException.class, () -> freteService.salvaFrete(frete), mensagem);
    }

    @Test
    @DisplayName("Fazer o calculo do valor do frete ao salvar")
    public void fazerOCalculoDoFreteAoSalvarFrete() {

        Frete frete = FreteBuilder.frete01().build();

        int cidadeId = frete.getCidade().getId();
        int clienteId = frete.getCliente().getId();

        when(cidadeService.buscaCidadePor(cidadeId)).thenReturn(frete.getCidade());
        when(clienteService.buscaClientePor(clienteId)).thenReturn(frete.getCliente());

        ReflectionTestUtils.setField(freteService, "valorFixoFrete", BigDecimal.valueOf(10));

        freteService.salvaFrete(frete);

        verify(freteMapper, times(1)).salvaFrete(frete);
        assertEquals(frete.getValor(), BigDecimal.valueOf(554.5));
    }

    @Test
    @DisplayName("Deve buscar o frete de maior valor")
    public void deveBuscarFreteDeMaiorValor() {

        Frete frete = FreteBuilder.frete01().build();

        freteService.buscaFreteDeMaiorValor();

        verify(freteMapper, times(1)).buscaFreteDeMaiorValor();
    }



}
