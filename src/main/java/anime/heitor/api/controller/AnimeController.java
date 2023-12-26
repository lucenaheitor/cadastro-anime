package anime.heitor.api.controller
        ;

import anime.heitor.api.domain.DTO.AtualizarAnimesDTO;
import anime.heitor.api.domain.DTO.CadastrarAnimesDTO;


import anime.heitor.api.domain.anime.Anime;
import anime.heitor.api.domain.anime.AnimeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("animes")
public class AnimeController {

    @Autowired
    private AnimeRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAnime(@RequestBody @Valid CadastrarAnimesDTO dados, UriComponentsBuilder uriBuilder) {
        Anime anime = repository.save(new Anime(dados));

        URI uri = uriBuilder.path("/animes/{id}").buildAndExpand(anime.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Anime>> listar() {
        List<Anime> animes = repository.findAll();
        return ResponseEntity.ok(animes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> listarAnimes(@PathVariable Long id) {
        Optional<Anime> animeOptional = repository.findById(id);
        return animeOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Anime> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarAnimesDTO dados) {
        Optional<Anime> animeOptional = repository.findById(id);

        if (animeOptional.isPresent()) {
            Anime anime = animeOptional.get();
            anime.atualizarInformacoes(dados);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
