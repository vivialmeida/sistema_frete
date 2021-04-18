package br.com.frete.service;

import br.com.frete.model.Cliente;
import br.com.frete.model.Frete;

import java.util.List;

public interface ClienteService {
    
    List<Cliente> buscaTodosOsClientes();

    Cliente buscaClientePor(Integer id);

    void salvaCliente(Cliente cliente);

    List<Cliente> buscaClientesPorTelefone(String telefone);

    List<Frete> buscaTodosOsFretesDoCliente(Integer clienteId);
}
