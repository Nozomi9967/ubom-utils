package com.nozomi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static String JwtGen(Integer id,String username){

        Map<String,Object> claims=new HashMap<>();
        claims.put("id",id);
        claims.put("username",username);

        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"Nozomi")//签名算法
                .setClaims(claims)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))//有效期
                .compact();

        return jwt;
    }

    public static Claims JwtParse(String jwt){
        Claims claim = Jwts.parser()
                .setSigningKey("Nozomi")
                .parseClaimsJws(jwt)
                .getBody();


        return claim;
    }
}
