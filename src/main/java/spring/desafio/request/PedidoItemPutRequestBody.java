package spring.desafio.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PedidoItemPutRequestBody {
    private int pid;
    private int qtd;

}
