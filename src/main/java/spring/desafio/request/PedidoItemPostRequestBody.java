package spring.desafio.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PedidoItemPostRequestBody {
    private int qtd;
    private int pedido_id;
    private int projeto_id;

}
