package anime.heitor.api.DTO;


import anime.heitor.api.anime.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CadastrarAnimesDTO(
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
