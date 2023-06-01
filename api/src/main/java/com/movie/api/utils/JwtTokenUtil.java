package com.movie.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public final class JwtTokenUtil {
    // 定义常量TOKEN_HEADER，用于设置HTTP请求头中Authorization字段的名称
    public final static String TOKEN_HEADER = "Authorization";

    // 定义常量REMEMBER_EXPIRATION_TIME，表示“记住我”选项的token过期时间为一星期
    public final static long REMEMBER_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    // 定义常量EXPIRATION_TIME，表示普通token的过期时间为一天
    public final static long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // 定义常量APP_SECRET，用于生成token的加密密钥
    private static final String APP_SECRET = "movie.api";

    // 定义常量ROLE_CLAIMS，用于角色权限声明
    private static final String ROLE_CLAIMS = "roles";

    /**
     * 生成Token
     */
    public static String createToken(String username, List roles, long expiration) {
        // 创建map对象，用于存储token中的payload信息
        Map map = new HashMap<>();
        // 向map对象中添加角色信息，键为ROLE_CLAIMS，值为角色列表
        map.put(ROLE_CLAIMS, roles);
        // 使用Jwts.builder()创建JWT token的构建器对象
        return Jwts.builder()
                // 设置JWT token的主题，即用户名
                .setSubject(username)
                // 设置JWT token的payload信息，即角色列表
                .setClaims(map)
                // 设置JWT token的签发时间，为当前时间
                .setIssuedAt(new Date())
                // 设置JWT token的过期时间，为当前时间加上过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                // 使用HS256算法和加密密钥APP_SECRET对JWT token进行签名
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                // 生成JWT token字符串
                .compact();
    }

    /**
     * 获取token body
     */
    private static Claims getTokenClaims(String token) {
        Claims claims;
        try {
            // 使用Jwts.parser()创建JWT token解析器对象，设置解密密钥为APP_SECRET
            // 调用parseClaimsJws方法解析token字符串，并获取解析结果中的payload信息
            claims = Jwts.parser()
                    .setSigningKey(APP_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // 如果解析过程中发生过期异常，则获取异常中的payload信息
            claims = e.getClaims();
        }
        // 返回解析结果中的payload信息
        return claims;
    }

    /**
     * 从Token中获取username
     */
    public static String getUsername(String token) {
        return getTokenClaims(token).getSubject();
    }

    /**
     * 从Token中获取用户角色
     */
    public static List<String> getTokenRoles(String token) {
        List<String> roles = new ArrayList<>();
        Object object = getTokenClaims(token).get(ROLE_CLAIMS);
        if (object instanceof ArrayList<?>) {
            for (Object o : (List<?>) object) {
                roles.add((String) o);
            }
        }
        return roles;
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token) {
        return getTokenClaims(token).getExpiration().before(new Date());
    }

}
