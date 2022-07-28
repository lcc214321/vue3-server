package com.luocc.vue3.server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class TokenUtils {

    // 发行人 签发者
    private final static String ISSUER = "vue3-server";

    // 签名秘钥
    private final static String SECRET = "aSE!d3#B#jWMtLxq";


    // 失效时间 默认2小时
    private static final long EXPIRE_TIME_PREFIX = 7200L;

    /**
     * 创建
     */
    public static String createJwt(String username, String jwtId, String issuer, String subject, String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME_PREFIX * 1000);
            Algorithm algorithm = Algorithm.HMAC256(password);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withJWTId(jwtId)
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return "";
        }
    }

    private static DecodedJWT decodeToken(String token) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    public static Date getExpiresAt(String token) {
        try {
            DecodedJWT jwt = decodeToken(token);
            return jwt.getExpiresAt();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isExpired(String token) {
        Date expiration = getExpiresAt(token);
        if (Objects.isNull(expiration)) {
            return true;
        }
        return expiration.before(new Date());
    }

    public static void main(String[] args) {

        String token = "xxx";

        String username = TokenUtils.getUsername(token);

        log.info("username is {}", username);
    }

}
