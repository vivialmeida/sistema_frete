package br.com.frete.service.impl;

import br.com.frete.mapper.FreteMapper;
import br.com.frete.model.Cidade;
import br.com.frete.model.Cliente;
import br.com.frete.model.Frete;
import br.com.frete.model.dtos.FreteDTO;
import br.com.frete.service.CidadeService;
import br.com.frete.service.ClienteService;
import br.com.frete.service.FreteService;
import br.com.frete.service.exceptions.FreteNaoRealizadoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FreteServiceImpl implements FreteService {

    private final FreteMapper freteMapper;
    private final CidadeService cidadeService;
    private final ClienteService clienteService;

    @Value("${valor-fixo-frete}")
    private BigDecimal valorFixoFrete;

    public FreteServiceImpl(FreteMapper freteMapper, CidadeService cidadeService, ClienteService clienteService) {
        this.freteMapper = freteMapper;
        this.cidadeService = cidadeService;
        this.clienteService = clienteService;
    }

    @Override
    public void salvaFrete(@NotNull Frete frete) {
        this.montaFrete(frete);
        this.validaFrete(frete);
        this.calculaFrete(frete);
        freteMapper.salvaFrete(frete);
    }

    @Override
    public List<Frete> buscaTodosOsFretes() {
        return freteMapper.buscaTodosOsFretes();
    }

    @Override
    public Frete buscaFreteDeMaiorValor() {
        return freteMapper.buscaFreteDeMaiorValor();
    }

    @Override
    public List<Frete> buscaFreteDoCliente(Integer clienteId) {
        return freteMapper.buscaFreteDoCliente(clienteId);
    }

    @Override
    public Frete fromDTO(FreteDTO freteDTO) {

        Frete frete = Frete.builder()
                .id(freteDTO.getId())
                .descricao(freteDTO.getDescricao())
                .peso(freteDTO.getPeso())
                .build();

        Cliente cliente = Cliente.builder()
                .id(freteDTO.getClienteId())
                .build();

        frete.setCliente(cliente);

        Cidade cidade = Cidade.builder()
                .id(freteDTO.getCidadeId())
                .build();

        frete.setCidade(cidade);

        return frete;
    }

    private void montaFrete(Frete frete) {

        int cidadeId = frete.getCidade().getId();
        int clienteId = frete.getCliente().getId();

        Cidade cidade = cidadeService.buscaCidadePor(cidadeId);
        Cliente cliente = clienteService.buscaClientePor(clienteId);

        frete.setCidade(cidade);
        frete.setCliente(cliente);
    }

    private void validaFrete(Frete frete) {

        Cidade cidade = frete.getCidade();
        Cliente cliente = frete.getCliente();

        if (Objects.isNull(cidade))
            throw new FreteNaoRealizadoException("Código de cidade não cadastrado.");

        if (Objects.isNull(cliente))
            throw new FreteNaoRealizadoException("Código de cliente não cadastrado.");

    }

    private void calculaFrete(Frete frete) {

        BigDecimal valorDoFrete = BigDecimal.ZERO;
        BigDecimal pesoDoFrete = frete.getPeso();
        BigDecimal taxaDaCidade = frete.pegaTaxaDaCidade();

        valorDoFrete = valorDoFrete
                .add(pesoDoFrete)
                .multiply(valorFixoFrete)
                .add(taxaDaCidade);

        frete.setValor(valorDoFrete);
    }
}
