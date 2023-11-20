package anime.heitor.api.anime;



public record DadosListagemAnimes(Long id, String nome, String  email,String anime, String autor, Genero genero) {

    public  DadosListagemAnimes(Anime anime){
        this(anime.getId(), anime.getNome(), anime.getEmail(), anime.getAnime(), anime.getAutor(), anime.getGenero());
    }

}