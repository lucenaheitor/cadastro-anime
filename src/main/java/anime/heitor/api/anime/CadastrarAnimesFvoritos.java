package anime.heitor.api.anime;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CadastrarAnimesFvoritos(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String anime,
        @NotBlank
        String autor,
        @NotNull
        Genero genero) {


}
