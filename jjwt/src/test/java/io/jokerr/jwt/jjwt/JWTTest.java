package io.jokerr.jwt.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JWTTest {

    @Test
    void test() {
        /*
        create the token
         */
        String token = Jwts.builder()
                .setSubject("jokerr")
                .signWith(SignatureAlgorithm.HS256, "secret".getBytes())
                .compact();

        System.out.println("stormpath/jjwt: " + token);

        /*
        verify the token
         */
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey("secret".getBytes())
                .parseClaimsJws(token);

        /*
        assert
         */
        assertEquals("jokerr", claims.getBody().getSubject());
    }
}
