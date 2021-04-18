package br.com.frete.service.impl;

import br.com.frete.mapper.ClienteMapper;
import br.com.frete.model.Cliente;
import br.com.frete.model.Frete;
import br.com.frete.service.ClienteService;
import br.com.frete.service.FreteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteMapper clienteMapper;
    private final FreteService freteService;

    public ClienteServiceImpl(ClienteMapper clienteMapper, @Lazy FreteService freteService) {
        this.clienteMapper = clienteMapper;
        this.freteService = freteService;
    }

    @Override
    public List<Cliente> buscaTodosOsClientes() {
        return clienteMapper.buscaTodosOsClientes();
    }

    @Override
    public Cliente buscaClientePor(Integer id) {
        return clienteMapper.buscaClientePor(id);
    }

    @Override
    public void salvaCliente(@NotNull Cliente cliente) {
        clienteMapper.salvaCliente(cliente);
    }

    @Override
    public List<Cliente> buscaClientesPorTelefone(String telefone) {
        return clienteMapper.buscaClientesPorTelefone(telefone);
    }

    @Override
    public List<Frete> buscaTodosOsFretesDoCliente(Integer clienteId) {
        return freteService.buscaFreteDoCliente(clienteId);
    }
}
