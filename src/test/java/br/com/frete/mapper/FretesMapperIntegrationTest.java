package br.com.frete.mapper;

import br.com.frete.builder.FreteBuilder;
import br.com.frete.model.Cidade;
import br.com.frete.model.Cliente;
import br.com.frete.model.Frete;
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
public class FretesMapperIntegrationTest {

    private final FreteMapper freteMapper;
    private final ClienteMapper clienteMapper;
    private final CidadeMapper cidadeMapper;

    private Frete frete;

    @Autowired
    public FretesMapperIntegrationTest(FreteMapper freteMapper, ClienteMapper clienteMapper, CidadeMapper cidadeMapper) {
        this.freteMapper = freteMapper;
        this.clienteMapper = clienteMapper;
        this.cidadeMapper = cidadeMapper;
    }

    @BeforeEach
    public void start() {
        frete = FreteBuilder.frete01().build();
    }

    @Test
    public void deveInserirUmaCidade() {
        assertDoesNotThrow(() -> freteMapper.salvaFrete(frete));
    }

    @Test
    public void salvarComDescricaoNuloDeveLancarUmaExcetpion() {
        frete.setDescricao(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> freteMapper.salvaFrete(frete), "A descricao deve ser preenchida");
    }

    @Test
    public void salvarComPesoNuloDeveLancarUmaException() {
        frete.setPeso(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> freteMapper.salvaFrete(frete), "O peso deve ser preenchido");
    }

    @Test
    public void salvarComCidadeNuloDeveLancarUmaException() {
        frete.setCidade(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> freteMapper.salvaFrete(frete), "A cidade deve ser preenchida");
    }

    @Test
    public void salvarComClienteNuloDeveLancarUmaException() {
        frete.setCliente(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> freteMapper.salvaFrete(frete), "O cliente deve ser preenchido");
    }

    @Test
    public void deveBuscarTodasOsFretes() {

        Frete frete01 = FreteBuilder.frete01().build();
        Cidade cidade01 = frete01.getCidade();
        Cliente cliente01 = frete01.getCliente();

        Frete frete02 = FreteBuilder.frete02().build();
        Cidade cidade02 = frete02.getCidade();
        Cliente cliente02 = frete02.getCliente();

        cidadeMapper.salvaCidade(cidade01);
        clienteMapper.salvaCliente(cliente01);
        freteMapper.salvaFrete(frete01);

        cidadeMapper.salvaCidade(cidade02);
        clienteMapper.salvaCliente(cliente02);
        freteMapper.salvaFrete(frete02);

        List<Frete> fretes = freteMapper.buscaTodosOsFretes();

        List<String> cidadeNomes = fretes
                .stream()
                .map(Frete::getDescricao)
                .collect(Collectors.toList());

        assertThat(cidadeNomes).contains("Frete para São Luis", "Frete para São Paulo");
    }




}
