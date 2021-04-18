package br.com.frete.builder;

import br.com.frete.model.Cidade;
import br.com.frete.model.Cliente;
import br.com.frete.model.Frete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreteBuilder {

    private Frete frete;

    public Frete build() {
        return this.frete;
    }

    public static FreteBuilder frete01() {

        Cidade cidade = CidadeBuilder.cidade01().build();
        Cliente cliente = ClienteBuilder.cliente01().build();

        Frete frete = Frete.builder()
                .id(1)
                .cliente(cliente)
                .cidade(cidade)
                .descricao("Frete para São Luis")
                .valor(null)
                .peso(BigDecimal.valueOf(55.2))
                .build();

        return montaFreteBuilder(frete);
    }

    public static FreteBuilder frete02() {

        Cidade cidade = CidadeBuilder.cidade02().build();
        Cliente cliente = ClienteBuilder.cliente02().build();

        Frete frete = Frete.builder()
                .id(2)
                .cliente(cliente)
                .cidade(cidade)
                .descricao("Frete para São Paulo")
                .valor(null)
                .peso(BigDecimal.valueOf(256.2))
                .build();

        return montaFreteBuilder(frete);
    }

    private static FreteBuilder montaFreteBuilder(Frete frete) {
        return FreteBuilder.builder()
                .frete(frete)
                .build();
    }

}
