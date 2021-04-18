package br.com.frete.model.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreteDTO {

    private int id;

    @NotNull(message="O descricao deve ser preechido")
    private String descricao;

    @NotNull(message="O peso deve ser preechido")
    private BigDecimal peso;

    @NotNull(message="O cliente deve ser preechido")
    private Integer clienteId;

    @NotNull(message="A cidade deve ser preechido")
    private Integer cidadeId;

}
