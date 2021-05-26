package spring.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.desafio.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
