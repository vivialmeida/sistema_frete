package br.com.frete.service;

import br.com.frete.mapper.ClienteMapper;
import br.com.frete.model.Cliente;
import br.com.frete.service.impl.ClienteServiceImpl;
import br.com.frete.builder.ClienteBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClienteServiceTests {

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve buscar clientes por telefone")
    public void deveBuscarClientesPorTelefone() {

        Cliente cliente01 = ClienteBuilder.cliente01().build();
        Cliente cliente02 = ClienteBuilder.cliente02().build();
        String telefone = "9899999999";

        List<Cliente> clientes = Arrays.asList(cliente01, cliente02);
        when(clienteMapper.buscaClientesPorTelefone(telefone)).thenReturn(clientes);

        clienteService.buscaClientesPorTelefone(telefone);

        verify(clienteMapper, times(1)).buscaClientesPorTelefone(telefone);
    }
}
