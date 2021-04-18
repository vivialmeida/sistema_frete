package br.com.frete.mapper;

import br.com.frete.model.Cliente;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ClienteMapper {

    void salvaCliente(@Param("cliente") Cliente cliente);

    List<Cliente> buscaTodosOsClientes();

    Cliente buscaClientePor(@Param("id") Integer id);

    List<Cliente> buscaClientesPorTelefone(String telefone);
}
