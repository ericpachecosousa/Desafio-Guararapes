package spring.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.desafio.model.PedidoItem;

public interface PedidoItemRepository extends JpaRepository<PedidoItem,Integer> {
}
