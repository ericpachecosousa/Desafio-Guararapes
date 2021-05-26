package spring.desafio.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;
import spring.desafio.model.PedidoItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class PedidoDto {
    private int id;
    private String name;
    private List<PedidoItem> itempedido;

}
