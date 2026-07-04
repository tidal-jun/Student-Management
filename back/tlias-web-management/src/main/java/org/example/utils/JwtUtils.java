package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 签名密钥（与你测试类中保持一致）
    private static final String SIGNING_KEY = "aXRoZWltYQ==";
    // 令牌过期时间：12小时 (12 * 60 * 60 * 1000 毫秒)
    private static final Long EXPIRATION_TIME = 12 * 60 * 60 * 1000L;

    /**
     * 生成JWT令牌
     * @param claims 自定义的负载数据（如用户id、用户名等）
     * @return 生成的JWT字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY) // 指定加密算法和密钥
                .addClaims(claims) // 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .compact(); // 生成并压缩成JWT字符串
    }

    /**
     * 解析JWT令牌
     * @param jwt 需要解析的JWT字符串
     * @return 解析出的自定义数据（Claims）
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY) // 指定解密密钥
                .parseClaimsJws(jwt) // 解析并校验令牌
                .getBody(); // 获取负载数据
    }
}