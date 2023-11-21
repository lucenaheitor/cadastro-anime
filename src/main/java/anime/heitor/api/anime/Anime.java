package anime.heitor.api.anime;

import anime.heitor.api.DTO.AtualizarAnimesDTO;
import anime.heitor.api.DTO.CadastrarAnimesDTO;
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


    public Anime(CadastrarAnimesDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.anime = dados.anime();
        this.autor = dados.autor();
        this.genero = dados.genero();


    }


    public void atualizarInformacoes(AtualizarAnimesDTO dados) {
        if (dados.anime() != null){
            this.anime = dados.anime();
        }
        if (dados.autor() != null){
            this.autor = dados.autor();
        }
        if (dados.genero() != null){
            this.genero = dados.genero();
        }
    }

    public static record DadosListagemAnimes(Long id, String nome, String  email, String anime, String autor, Genero genero) {

        public  DadosListagemAnimes(Anime anime){
            this(anime.getId(), anime.getNome(), anime.getEmail(), anime.getAnime(), anime.getAutor(), anime.getGenero());
        }

    }
}
