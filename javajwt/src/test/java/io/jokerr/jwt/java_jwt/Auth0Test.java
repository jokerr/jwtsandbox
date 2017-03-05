package io.jokerr.jwt.java_jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Auth0Test {

    @Test
    void test() throws JWTCreationException, UnsupportedEncodingException {
        /*
        create the token
         */
        String token = JWT.create()
                .withSubject("jokerr")
                .sign(Algorithm.HMAC256("secret"));

        System.out.println("auth0/java-jwt: " + token);

        /*
        verify the token
         */
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret")).build();
        DecodedJWT jwt = verifier.verify(token);

        /*
        assert
         */
        assertEquals("jokerr", jwt.getClaim("sub").asString());
    }

    @Disabled
    @Test
    void test2() throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret")).build();
        DecodedJWT jwt = verifier.verify("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2tlcnIifQ.ywkhSQEYntZ9V05L_jPjOxebHaswZWkrqmsuXZIqjPs");
        assertEquals("jokerr", jwt.getClaim("sub").asString());
    }
}
