package anime.heitor.api.DTO;

import anime.heitor.api.anime.Genero;
import jakarta.validation.constraints.NotNull;

public record AtualizarAnimesDTO(
        @NotNull
        Long id,
        String anime,
        String autor,
        Genero genero) {
}
