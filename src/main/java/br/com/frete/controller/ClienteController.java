package br.com.frete.controller;

import br.com.frete.model.Cliente;
import br.com.frete.model.Frete;
import br.com.frete.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscaTodos() {
        List<Cliente> clientes = clienteService.buscaTodosOsClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping(path = "/{id}/fretes")
    public ResponseEntity<List<Frete>> buscaTodosDoCliente(@PathVariable Integer id) {
        List<Frete> fretesDoCliente = clienteService.buscaTodosOsFretesDoCliente(id);
        return ResponseEntity.ok().body(fretesDoCliente);
    }

    @PostMapping
    public ResponseEntity<Void> salva(@Valid @RequestBody Cliente cliente) {

        clienteService.salvaCliente(cliente);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

}
