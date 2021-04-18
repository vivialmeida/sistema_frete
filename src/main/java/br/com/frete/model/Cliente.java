package br.com.frete.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Builder
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "{id}")
public class Cliente {

    private int id;

    @NotNull(message="O nome deve ser preechido")
    private String nome;

    @NotNull(message="O endereco deve ser preechido")
    private String endereco;

    @NotNull(message="O telefone deve ser preechido")
    private String telefone;

}
