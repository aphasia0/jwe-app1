package com.example.app1;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.KeyAlgorithm;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

@RestController
public class MyRestController {
    @CrossOrigin(origins = "*")
    @GetMapping("/api-endpoint")
    public Message api() {
        KeyPair pair = null;
        try {
            pair = RSAPEMReader.getKeyPair("private_key.pem", "public_key.pem");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(pair.getPublic());
        // Choose the key algorithm used encrypt the payload key:
        KeyAlgorithm<PublicKey, PrivateKey> alg = Jwts.KEY.RSA_OAEP_256;
        // Choose the Encryption Algorithm to encrypt the payload:
        AeadAlgorithm enc = Jwts.ENC.A256GCM;

        String jwe = Jwts.builder()
                .subject("Joe").
                encryptWith(pair.getPublic(), alg, enc).compact();
        System.out.println(jwe);
        return new Message(jwe);

    }
}
