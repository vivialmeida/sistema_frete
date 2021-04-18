package br.com.frete.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "{id}")
public class Frete {

    private int id;

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    private BigDecimal peso;

    private BigDecimal valor;

    @NotNull
    private Cliente cliente;

    @NotNull
    private Cidade cidade;


    public BigDecimal pegaTaxaDaCidade() {
        return cidade.getTaxa();
    }


}
