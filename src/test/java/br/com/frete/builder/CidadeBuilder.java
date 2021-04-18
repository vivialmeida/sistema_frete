package br.com.frete.builder;

import br.com.frete.model.Cidade;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class CidadeBuilder {

    private Cidade cidade;

    public CidadeBuilder(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cidade build() {
        return cidade;
    }

    public static CidadeBuilder cidade01() {

        Cidade cidade = Cidade.builder()
                .id(1)
                .nome("São Luis")
                .uf("MA")
                .taxa(BigDecimal.valueOf(2.5))
                .build();

        return montaCidadeBuilder(cidade);
    }

    public static CidadeBuilder cidade02() {

        Cidade cidade = Cidade.builder()
                .id(2)
                .nome("Rosário")
                .uf("MA")
                .taxa(BigDecimal.valueOf(3.5))
                .build();

        return montaCidadeBuilder(cidade);

    }

    private static CidadeBuilder montaCidadeBuilder(Cidade cidade) {
        return CidadeBuilder.builder()
                .cidade(cidade)
                .build();
    }





}
