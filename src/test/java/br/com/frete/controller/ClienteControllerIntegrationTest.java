package br.com.frete.controller;

import br.com.frete.model.Cliente;
import br.com.frete.builder.ClienteBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("prod")
public class ClienteControllerIntegrationTest {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public ClienteControllerIntegrationTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    @DisplayName("Deve mostrar todas os clientes")
    public void deveMostrarTodasOsClientes() {

        ResponseEntity<String> resposta =
                testRestTemplate.exchange("/clientes", HttpMethod.GET, null, String.class);

        System.out.println("######## " + resposta.getBody() );

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @DisplayName("Deve salvar um cliente")
    public void deveSalvarUmCliente() {

        Cliente cliente = ClienteBuilder.cliente01().build();

        HttpEntity<Cliente> httpEntity = new HttpEntity<>(cliente);

        ResponseEntity<List<String>> resposta =
                testRestTemplate.exchange("/clientes",
                        HttpMethod.POST, httpEntity,
                        new ParameterizedTypeReference<List<String>>() {
                        });

        assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
    }

}
