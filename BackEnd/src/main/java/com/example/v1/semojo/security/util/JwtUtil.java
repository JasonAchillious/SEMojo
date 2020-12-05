package com.example.v1.semojo.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static final String JWT_SECERT = "zzx@11711621";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static SecretKey generalKey(){
        try {
            byte[] encodedKey=JWT_SECERT.getBytes("UTF-8");
            SecretKey key = new SecretKeySpec(encodedKey,0, encodedKey.length,"AES");
            return key;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 签发JWT，创建token的方法
     * @param id  jwt的唯一标识，主要用来做一次性token。
     * @param iss  jwt签发者
     * @param subject  jwt所面向的用户。一般使用用户的登录名
     * @param ttlMillis  有效期，单位毫秒
     * @return  token 是为一个用户的有效登录周期准备的一个token 。用户推出或超时，token失效
     */
    public static String createJWT(String id, String iss, String subject,long ttlMillis, String roles){
        try {
            SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
            long nowMilllis = System.currentTimeMillis();
            Date now = new Date(nowMilllis);
            SecretKey secretKey=generalKey();
            JwtBuilder builder = Jwts.builder()
                    .claim("authorities", roles)
                    .setId(id)
                    .setIssuer(iss)
                    .setSubject(subject)
                    .setIssuedAt(now)   //token生成时间
                    .signWith(signatureAlgorithm, secretKey);//设置密匙和算法
            if (ttlMillis>0){
                long expMillis = nowMilllis + ttlMillis;
                Date expDate = new Date(expMillis);
                builder.setExpiration(expDate);
            }
            return builder.compact();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解析JWT字符串
     * @param jwt  就是生成的toekn
     * @return
     */
    public static Claims parseJWT(String jwt){
        SecretKey secretKey=generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 验证jwt
     * @param jwtStr
     * @return
     */
    public static String validateJWT(String jwtStr){
        Claims claims=null;
        try{
            claims=parseJWT(jwtStr);
            //成功
        }catch (ExpiredJwtException e){
            //token过期
            e.printStackTrace();
        }catch (SignatureException e){
            //签名错误
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String generalSubject(Object subject){
        try {
            return MAPPER.writeValueAsString(subject);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用 ——— 生成token
     */
    public String login(String username, String iss, String roles){
        //生成
        return JwtUtil.createJWT(UUID.randomUUID().toString(),
                iss,
                JwtUtil.generalSubject(username),2*60*60*1000, roles);
    }


    /**
     * 获取接口放在header中的token
     * @param request
     */
    public void  test(HttpServletRequest request){
        String token=request.getHeader("Authorization");//生成的token
        String result=JwtUtil.validateJWT(token);//验证
    }

}
