package br.com.frete.controller;

import br.com.frete.model.dtos.FreteDTO;
import br.com.frete.model.Frete;
import br.com.frete.service.FreteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/fretes")
public class FreteController {

    private final FreteService freteService;

    public FreteController(FreteService freteService) {
        this.freteService = freteService;
    }

    @GetMapping
    public ResponseEntity<List<Frete>> buscaTodos() {
        List<Frete> fretes = freteService.buscaTodosOsFretes();
        return ResponseEntity.ok().body(fretes);
    }

    @PostMapping
    public ResponseEntity<Void> salva(@Valid @RequestBody FreteDTO freteDTO) {

        Frete frete = freteService.fromDTO(freteDTO);

        freteService.salvaFrete(frete);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(frete.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
