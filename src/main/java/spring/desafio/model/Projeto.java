package spring.desafio.model;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name ="projetoprod")
@Builder
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    public Boolean isEqual(Projeto projeto)
    {
        return this.getName().equals(projeto.getName());
    }
}
