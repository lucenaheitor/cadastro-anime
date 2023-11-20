package anime.heitor.api.anime;

import jakarta.validation.constraints.NotNull;

public record AtualizarAnimesDTO(
        @NotNull
        Long id,
        String anime,
        String autor,
        Genero genero) {
}
