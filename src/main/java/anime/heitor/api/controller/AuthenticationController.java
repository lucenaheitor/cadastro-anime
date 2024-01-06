package anime.heitor.api.controller;

import anime.heitor.api.domain.DTO.AuthenticationDTO;
import anime.heitor.api.domain.DTO.DataTokenJWT;
import anime.heitor.api.domain.DTO.RegisterDTO;
import anime.heitor.api.domain.usuarios.Usuario;
import anime.heitor.api.domain.usuarios.UsuarioRepository;
import anime.heitor.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userNamePassword =  new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth =  this.authenticationManager.authenticate(userNamePassword);
        var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());



        return  ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

    @PostMapping("register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login())  != null) return  ResponseEntity.badRequest().build();

            String encryptedPasswod = new BCryptPasswordEncoder().encode(data.password());
            Usuario newUser = new Usuario(data.login(), encryptedPasswod);

            this.repository.save(newUser);

            return  ResponseEntity.ok().build();

    }



}
