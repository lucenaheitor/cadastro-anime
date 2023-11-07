package anime.heitor.api.anime;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "animes")
@Entity(name = "Anime")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String nome;
    private String  email;
    private String anime;
    private  String autor;

    @Enumerated(EnumType.STRING)
    private Genero genero;


    public Anime(CadastrarAnimesFvoritos dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.anime = dados.anime();
        this.autor = dados.autor();
        this.genero = dados.genero();


    }
}
