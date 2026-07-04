package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 生成JWT令牌  Jwts.builder()
     */

    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"aXRoZWltYQ==")    //指定加密算法，密钥(itheima的base64编码)
                .addClaims(dataMap) //添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))  //设置过期时间 (3600毫秒*1000 == 一个小时)
                .compact() ;    //生成令牌
        System.out.println(jwt);
    }

    /**
     * 解析JWT令牌  Jwts.parser()
     */
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc4MDg0ODg0N30.zcBAQ9C0js8TJCCGzNh34fKoX_JWb7PgHk6OkzMv2qI";
        Claims claims = Jwts.parser().setSigningKey("aXRoZWltYQ==") //指定密钥
                .parseClaimsJws(token)  //解析令牌
                .getBody(); //获取自定义数据
        System.out.println( claims);
    }
}
