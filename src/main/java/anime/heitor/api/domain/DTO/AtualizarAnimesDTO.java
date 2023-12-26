package anime.heitor.api.domain.DTO;

import anime.heitor.api.domain.anime.Genero;
import jakarta.validation.constraints.NotNull;

public record AtualizarAnimesDTO(
        @NotNull
        Long id,
        String anime,
        String autor,
        Genero genero) {
}
