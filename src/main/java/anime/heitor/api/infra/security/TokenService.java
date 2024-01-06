package anime.heitor.api.infra.security;

import anime.heitor.api.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public  String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("API Anime")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw  new RuntimeException("Error to generate tonken", exception);
        }
    }

   public String  getSubject(String tokenJWT){
        try {
            Algorithm algorithm  =  Algorithm.HMAC256(secret);
            return  JWT.require(algorithm)
                    .withIssuer("API Anime")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch(JWTVerificationException exception){
            exception.printStackTrace();
            throw  new RuntimeException("token invalido ou expirado!");
        }
   }

    private Instant dataExpiracao() {
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }

}
