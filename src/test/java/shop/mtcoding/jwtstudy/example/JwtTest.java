package shop.mtcoding.jwtstudy.example;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

// Json web Token
public class JwtTest {

    // ABC(메타코딩) -> 1313AB
    // ABC(시크) -> 5335KD 암호가 다르게 나온다

    // 1313AB 토큰
    @Test
    public void createJwt_test() {
        // given

        // when
        String jwt = JWT.create().withSubject("토큰제목")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)).withClaim("id", 1)
                .withClaim("role", "guest").sign(Algorithm.HMAC512("메타코딩"));
        System.out.println(jwt);
        // then
    }

    @Test
    public void verifyJwt_test() {

        // given
        String jwt = JWT.create().withSubject("토큰제목")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)).withClaim("id", 1)
                .withClaim("role", "guest").sign(Algorithm.HMAC512("메타코딩"));
        System.out.println(jwt);

        // when
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("메타코딩")).build().verify(jwt);
            int id = decodedJWT.getClaim("id").asInt();
            String role = decodedJWT.getClaim("role").asString();
            System.out.println(id);
            System.out.println(role);
        } catch (SignatureVerificationException sve) {
            System.out.println("토큰 검증 실패" + sve.getMessage());
        } catch (TokenExpiredException tee) {
            System.out.println("토큰 실패" + tee.getMessage());
        }

    }
    // then

}
