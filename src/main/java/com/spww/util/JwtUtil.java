package com.spww.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.spww.entity.User;
import com.spww.exception.BusinessException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.spww.constant.BusinessContant.SYSTEM_ERROR;

/**
 * jwt工具类
 */
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";
    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 7 * 24 * 60 * 60 * 1000L;//单位为秒（过期时间为7天）

    private static byte[] signingSecretBytes = DatatypeConverter.parseBase64Binary(SECRET); //转换成Base64编码s

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(User user) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("id", user.getUserid())//userId
                .withClaim("name", user.getName())//name
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET)); //SECRET加密
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            logger.error("token解码异常:" + e.getMessage());
            //解码异常则抛出异常
            throw new BusinessException(SYSTEM_ERROR,"token解码异常");
        }
        return jwt.getClaims();
    }

    /**
     * 校验token并解析token
     */
    public static boolean verifyToken(String token, String name) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withClaim("name",name).build();
            jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            logger.error("token解码异常:" + e.getMessage());
            //解码异常则抛出异常
            throw new BusinessException(SYSTEM_ERROR,"token解码异常");
        }
    }

    public static Boolean verify(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(signingSecretBytes)
                    .parseClaimsJws(token).getBody();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     *解析token获取用户名，无须解密
     * @param token
     * @return 用户名username
     */
    public static String getUsername(String token){
        try {
            DecodedJWT decodeJWT = JWT.decode(token);
            return decodeJWT.getClaim("name").asString();
        }catch(JWTDecodeException e){
            throw new BusinessException(SYSTEM_ERROR,"token错误");
        }
    }
}
