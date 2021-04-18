package br.com.frete.controller;

import br.com.frete.model.Cidade;
import br.com.frete.service.CidadeService;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> buscaTodos() {
        List<Cidade> cidades = cidadeService.buscaTodasAsCidades();
        return ResponseEntity.ok().body(cidades);
    }

    @PostMapping
    public ResponseEntity<Void> salva(@Valid @RequestBody Cidade cidade) {

        cidadeService.salvaCidade(cidade);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cidade.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }


}
