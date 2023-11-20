package anime.heitor.api.controller
        ;

import anime.heitor.api.anime.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //Abordagem correta com  um DTO pra ter  felixibilidade e devolver aqui q vc quer ao inves de  devolver toda a entidade.
    //Usando o page pra devolver uma pagina configurado com 10 itens por  pagina
//    @GetMapping
//    public Page<DadosListagemAnimes> listar(Pageable paginacao){
//        return repository.findAll(paginacao).map(DadosListagemAnimes::new);
//    }

//Abordagem mais simples pra fim de estudo,  devolvendo toda a entidade pois é simples
    @GetMapping
    public List<Anime> listar(){

        return  repository.findAll();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarAnimesDTO dados){
        var anime =  repository.getReferenceById(dados.id());
        anime.atualizarInforamacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);

    }

}
