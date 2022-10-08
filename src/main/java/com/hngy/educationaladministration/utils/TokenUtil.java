package com.hngy.educationaladministration.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hngy.educationaladministration.Exception.LoginTimeoutException;
import com.hngy.educationaladministration.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUtil {

    /**
     * token 过期时间 1 小时, 单位: 毫秒.
     */
    private static final long TOKEN_EXPIRED_TIME =24 * 60 * 60 * 1000 ;
//    private static final long TOKEN_EXPIRED_TIME = 6 * 1000 ;

    /**
     * jwt 加密解密密钥
     */
    private static final String JWT_SECRET = "uio1231jsdkjf=";

    /**
     * 创建token
     * @param user
     * @return
     */
    public static String createToken(User user){

        String token  = null;
        try {
            token = JWT.create()
//                    .withIssuer("zz")
                    //将 user 信息 存放到 Token 之中
                    .withClaim("user", JSONObject.toJSONString(user))
                    //设置过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_EXPIRED_TIME))
                    //签名采用 HMAC256 算法 私钥为JWT_SECRET
                    .sign(Algorithm.HMAC256(JWT_SECRET));
            System.out.println(token);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    /**
     * 验证token
     * @param request
     * @return
     */
    public static User verifyToken(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if(cookies==null)
            return null;
        String token = null;
        for(Cookie cookie : cookies){
//            System.out.println("cookie:"+cookie.getName());
//            System.out.println("value:"+cookie.getValue());
//            System.out.println("path:"+cookie.getPath());
//            System.out.println();
            String path = cookie.getPath();
            String name = cookie.getName();
            String value = cookie.getValue();
            if(
                    name.equals("token") &&
                            value!=null &&
                            value.length()!=0
            )
            {
                token = cookie.getValue();
                break;
            }
        }
        if(token == null){
            token = request.getHeader("Authorization");
        }

        if(token == null){
            return null;
        }

        try {
            //选用 HMAC256 算法 ，私钥为 JWT_SECRET
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET))
//                            .withIssuer("zz")
                    .build(); //Reusable verifier instance
            //验证Token的合法性
            DecodedJWT jwt = verifier.verify(token);
            User user = JSON.parseObject(jwt.getClaim("user").asString(),User.class);
            return user;
        } catch ( TokenExpiredException e){
//            e.printStackTrace();
            throw new LoginTimeoutException("登录超时");
        } catch (JWTVerificationException exception ){
//                    exception.printStackTrace();
        }

        return null;
    }
}
