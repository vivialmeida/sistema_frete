package br.com.frete.mapper;

import br.com.frete.model.Frete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FreteMapper {

    void salvaFrete(@Param("frete") Frete frete);

    List<Frete> buscaTodosOsFretes();

    Frete buscaFreteDeMaiorValor();

    List<Frete> buscaFreteDoCliente(Integer clienteId);
}
