package br.com.frete.mapper;

import br.com.frete.model.Cidade;
import br.com.frete.builder.CidadeBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("prod")
public class CidadesMapperIntegrationTest {

    private final CidadeMapper cidadeMapper;

    private Cidade cidade;

    @Autowired
    public CidadesMapperIntegrationTest(CidadeMapper cidadeMapper) {
        this.cidadeMapper = cidadeMapper;
    }

    @BeforeEach
    public void start() {
        cidade = CidadeBuilder.cidade02().build();
    }

    @Test
    public void deveInserirUmaCidade() {
        assertDoesNotThrow(() -> cidadeMapper.salvaCidade(cidade));
    }

    @Test
    public void salvarComNomeNuloDeveLancarUmaExcetpion() {
        cidade.setNome(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> cidadeMapper.salvaCidade(cidade), "O nome deve ser preenchido");
    }

    @Test
    public void salvarComUfNuloDeveLancarUmaException() {
        cidade.setUf(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> cidadeMapper.salvaCidade(cidade), "A UF deve ser preenchida");
    }

    @Test
    public void salvarComTaxaNuloDeveLancarUmaException() {
        cidade.setTaxa(null);
        assertThrows(DataIntegrityViolationException.class,
                () -> cidadeMapper.salvaCidade(cidade), "A taxa deve ser preenchida");
    }

    @Test
    public void deveBuscarTodasAsCidades() {

        Cidade cidade01 = CidadeBuilder.cidade01().build();
        Cidade cidade02 = CidadeBuilder.cidade02().build();

        cidadeMapper.salvaCidade(cidade01);
        cidadeMapper.salvaCidade(cidade02);

        List<Cidade> cidades = cidadeMapper.buscaTodasAsCidades();

        List<String> cidadeNomes = cidades
                .stream()
                .map(Cidade::getNome)
                .collect(Collectors.toList());

        assertThat(cidadeNomes).contains("São Luis", "Rosário");
    }

    @Test
    public void deveBuscarCidadePor() {

        cidadeMapper.salvaCidade(cidade);
        Cidade buscada = cidadeMapper.buscaCidadePor(cidade.getId());

        assertEquals(cidade, buscada);
    }


}
