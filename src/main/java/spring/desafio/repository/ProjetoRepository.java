package spring.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.desafio.model.Projeto;

public interface ProjetoRepository  extends JpaRepository<Projeto,Integer> {
}
