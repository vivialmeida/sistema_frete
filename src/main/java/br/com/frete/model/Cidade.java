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
public class Cidade {

    private int id;

    @NotNull(message="O nome deve ser preechido")
    @NotEmpty
    private String nome;

    @NotNull(message="A uf deve ser preechida")
    @NotEmpty
    private String uf;

    @NotNull(message = "A taxa dever ser preechido")
    private BigDecimal taxa;

}
