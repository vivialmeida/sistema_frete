package br.com.frete.controller;

import br.com.frete.model.Cidade;
import br.com.frete.builder.CidadeBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("prod")
public class CidadeControllerIntegrationTest {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public CidadeControllerIntegrationTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    @DisplayName("Deve mostrar todas as cidades")
    public void deveMostrarTodasAsCidades() {

        ResponseEntity<String> resposta =
                testRestTemplate.exchange("/cidades", HttpMethod.GET, null, String.class);

        System.out.println("######## " + resposta.getBody() );

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @DisplayName("Deve salvar uma cidade")
    public void deveSalvarUmaCidade() {

        Cidade cidade = CidadeBuilder.cidade01().build();

        HttpEntity<Cidade> httpEntity = new HttpEntity<>(cidade);

        ResponseEntity<List<String>> resposta =
                testRestTemplate.exchange("/cidades",
                        HttpMethod.POST, httpEntity,
                        new ParameterizedTypeReference<List<String>>() {
                        });

        assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
    }


}
