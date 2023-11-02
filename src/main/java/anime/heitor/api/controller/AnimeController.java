package anime.heitor.api.controller
        ;

import anime.heitor.api.anime.Anime;
import anime.heitor.api.anime.AnimeRepository;
import anime.heitor.api.anime.CadastrarAnimesFvoritos;
import anime.heitor.api.anime.Genero;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("animes")
public class AnimeController {

    @Autowired
    private AnimeRepository  repository;

    @PostMapping
    @Transactional
    public void  CadastroAnimes(@RequestBody @Valid CadastrarAnimesFvoritos dados){
        repository.save(new Anime(dados));
    }


}
