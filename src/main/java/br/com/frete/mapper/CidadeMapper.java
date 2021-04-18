package br.com.frete.mapper;

import br.com.frete.model.Cidade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CidadeMapper {

  void salvaCidade(@Param("cidade") Cidade cidade);

    List<Cidade> buscaTodasAsCidades();

    List<Cidade> buscaPor(@Param("nome") String nome);

    Cidade buscaCidadePor(@Param("id") Integer id);

    Cidade buscaACidadeComOMaiorNumeroDeFretes();
}
