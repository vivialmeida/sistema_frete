package br.com.frete.mapper;

import br.com.frete.builder.ClienteBuilder;
import br.com.frete.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("prod")
public class ClienteMapperIntegrationTest {

    private final ClienteMapper clienteMapper;

    private Cliente cliente;

    @Autowired
    public ClienteMapperIntegrationTest(ClienteMapper clienteMapper) {
        this.clienteMapper = clienteMapper;
    }

    @BeforeEach
    public void start() {
        cliente = ClienteBuilder.cliente01().build();
    }

    @Test
    public void deveInserirUmaCidade() {
        assertDoesNotThrow(() -> clienteMapper.salvaCliente(cliente));
    }

    @Test
    public void salvarComNomeNuloDeveLancarUmaExcetpion() {
        cliente.setNome(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> clienteMapper.salvaCliente(cliente), "O nome deve ser preenchido");
    }

    @Test
    public void salvarComEnderecoNuloDeveLancarUmaException() {
        cliente.setEndereco(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> clienteMapper.salvaCliente(cliente), "O endereco deve ser preenchido");
    }

    @Test
    public void salvarComTelefoneNuloDeveLancarUmaException() {
        cliente.setTelefone(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> clienteMapper.salvaCliente(cliente), "O telefone deve ser preenchido");
    }

    @Test
    public void deveBuscarTodosOsClientes() {

        Cliente cliente01 = ClienteBuilder.cliente01().build();
        Cliente cliente02 = ClienteBuilder.cliente02().build();

        clienteMapper.salvaCliente(cliente01);
        clienteMapper.salvaCliente(cliente02);

        List<Cliente> clientes = clienteMapper.buscaTodosOsClientes();

        List<String> cidadeNomes = clientes
                .stream()
                .map(Cliente::getNome)
                .collect(Collectors.toList());

        assertThat(cidadeNomes).contains("Fulano", "Beltrano");
    }

    @Test
    public void devBuscarClientePor() {

        clienteMapper.salvaCliente(cliente);
        Cliente buscado = clienteMapper.buscaClientePor(cliente.getId());

        assertEquals(cliente, buscado);
    }


}
