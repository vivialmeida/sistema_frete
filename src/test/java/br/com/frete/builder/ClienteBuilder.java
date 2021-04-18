package br.com.frete.builder;

import br.com.frete.model.Cliente;
import lombok.Builder;

@Builder
public class ClienteBuilder {

    private Cliente cliente;


    public ClienteBuilder(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente build() {
        return cliente;
    }

    public static ClienteBuilder cliente01() {

        Cliente cliente = Cliente.builder()
                .id(1)
                .nome("Fulano")
                .endereco("Rua 10")
                .telefone("9899999999")
                .build();

        return montaClienteBuilder(cliente);
    }

    public static ClienteBuilder cliente02() {

        Cliente cliente = Cliente.builder()
                .id(2)
                .nome("Beltrano")
                .endereco("Rua 15")
                .telefone("9899999999")
                .build();

        return montaClienteBuilder(cliente);
    }

    public static ClienteBuilder cliente03() {

        Cliente cliente = Cliente.builder()
                .id(3)
                .nome("Ciclano")
                .endereco("Rua 16")
                .telefone("9899999999")
                .build();

        return montaClienteBuilder(cliente);
    }

    private static ClienteBuilder montaClienteBuilder(Cliente cliente) {
        return ClienteBuilder.builder()
                .cliente(cliente)
                .build();
    }


}
