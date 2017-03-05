package io.jokerr.jwt.jose4j;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jose4jTest {

    @Test
    void test() throws JoseException, InvalidJwtException, MalformedClaimException {
        /*
        create the token
         */
        JwtClaims claims = new JwtClaims();
        claims.setSubject("jokerr");

        Key key = new HmacKey(Arrays.copyOf("secret".getBytes(),32));

        JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
//        jws.setHeader("typ","JWT");
        jws.setPayload(claims.toJson());
        jws.setKey(key);
        String token = jws.getCompactSerialization();

        System.out.println("atlatian/jose4j: " + token);

        /*
        verify the token
         */
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKey(key)
                .build();

        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);

        /*
        assert
         */
        assertEquals("jokerr", jwtClaims.getSubject());
    }
}
