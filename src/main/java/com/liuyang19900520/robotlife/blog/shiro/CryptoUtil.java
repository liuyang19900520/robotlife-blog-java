package com.liuyang19900520.robotlife.blog.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.DefaultCompressionCodecResolver;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Set;

/**
 * Created by liuyang on 2018/3/17
 */

public class CryptoUtil {

    private static String APP_KEY = "123456";
    private static final String SECRET_KEY_JWT = "*(-=4eklfasdfarerf41585fdasf";

    public static final String ACCESS_TOKEN_TYPE = "1";
    public static final String REFRESH_TOKEN_TYPE = "2";


    // HMAC 加密算法名称
    public static final String HMAC_MD5 = "HmacMD5";// 128位
    public static final String HMAC_SHA1 = "HmacSHA1";// 126
    public static final String HMAC_SHA256 = "HmacSHA256";// 256
    public static final String HMAC_SHA512 = "HmacSHA512";// 512

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static CompressionCodecResolver CODECRESOLVER = new DefaultCompressionCodecResolver();

    /**
     * JWT签发令牌
     *
     * @param subject     用户ID
     * @param roles       访问主张-角色
     * @param permissions 访问主张-权限
     * @return json web token
     */
    public static String issueJwt(String id, String subject, String roles
            , String permissions, Date issuedAt, String type) {

        // 秘钥
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY_JWT);
        JwtBuilder jwt = Jwts.builder();
        jwt.setId(id);
        // 用户名
        jwt.setSubject(subject);
        // 签发者
        jwt.setIssuer("token-server");
        // 签发时间
        jwt.setIssuedAt(issuedAt);

        // 有效时间
        if (ACCESS_TOKEN_TYPE.equals(type)) {
            Date expiration = new Date(issuedAt.getTime() + 5 * 1000L);
            jwt.setExpiration(expiration);
        }

        if (REFRESH_TOKEN_TYPE.equals(type)) {
            Date expiration = new Date(issuedAt.getTime() + 7 * 24 * 60 * 60 * 1000L);
            jwt.setExpiration(expiration);
        }


        // 访问主张-角色
        if (null != roles && !"".equals(roles)) {
            jwt.claim("roles", roles);
        }
        // 访问主张-权限
        if (null != permissions && !"".equals(permissions)) {
            jwt.claim("perms", permissions);
        }
        jwt.compressWith(CompressionCodecs.DEFLATE);
        jwt.signWith(SignatureAlgorithm.HS512, secretKeyBytes);
        return jwt.compact();
    }

    public static String verifyToken(String token) {
        Jwt parse = Jwts.parser()
                .setSigningKey(SECRET_KEY_JWT).parse(token);

        Claims claims = (Claims) parse.getBody();
        String verifyToken = issueJwt(claims.getId(), claims.getSubject(), String.valueOf(claims.get("roles")), String.valueOf(claims.get("perms")), claims.getIssuedAt(), ACCESS_TOKEN_TYPE);

        return verifyToken;
    }

    public static String verifyFreshToken(String token) {
        Jwt parse = Jwts.parser()
                .setSigningKey(SECRET_KEY_JWT).parse(token);

        Claims claims = (Claims) parse.getBody();
        String verifyToken = issueJwt(claims.getId(), claims.getSubject(), String.valueOf(claims.get("roles")), String.valueOf(claims.get("perms")), claims.getIssuedAt(), REFRESH_TOKEN_TYPE);

        return verifyToken;
    }


    public static Claims parserToken(String token) {

        return (Claims) Jwts.parser().setSigningKey(SECRET_KEY_JWT).parse(token).getBody();

    }

    public static Set<String> getPerms(String token) {
        Claims claims = parserToken(token);

        String permsStr = (String) claims.get("perms");

        Set<String> perms = split(permsStr);

        return perms;

    }

    public static Set<String> getRoles(String token) {
        Claims claims = parserToken(token);

        String permsStr = (String) claims.get("roles");

        Set<String> perms = split(permsStr);

        return perms;

    }


    /**
     * 生成HMAC摘要
     *
     * @param plaintext 明文
     * @return 摘要
     */
    public static String hmacDigest(String plaintext) {
        try {
            Mac mac = Mac.getInstance(HMAC_MD5);
            byte[] secretByte = APP_KEY.getBytes();
            byte[] dataBytes = plaintext.getBytes();
            SecretKey secret = new SecretKeySpec(secretByte, HMAC_MD5);
            mac.init(secret);
            byte[] doFinal = mac.doFinal(dataBytes);
            return byte2HexStr(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 字节数组转字符串
     *
     * @param buffer 字节数组
     * @return 字符串
     */
    public static String byte2HexStr(byte[] buffer) {
//        StringBuilder hs = new StringBuilder();
//        String stmp;
//        for (int n = 0; bytes != null && n < bytes.length; n++) {
//            stmp = Integer.toHexString(bytes[n] & 0XFF);
//            if (stmp.length() == 1) {
//                hs.append('0');
//            }
//            hs.append(stmp);
//        }
//        return hs.toString().toUpperCase();
        String reslut = new String("");
        int length = buffer.length;
        int index = 0;
        for (index = 0; (index + 3) < length; index += 3) {
            byte[] temp = {buffer[index], buffer[index + 1], buffer[index + 2]};
            reslut += ConvertByte3ToString(temp);
        }
        if (length % 3 == 1) {
            byte[] temp = {buffer[length - 1]};
            reslut += ConvertByteToString(temp);
        } else if (length % 3 == 2) {
            byte[] temp = {buffer[length - 2], buffer[length - 1]};
            reslut += ConvertByte2ToString(temp);
        }
        return reslut;
    }

    private static char[] CharCode = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    private static String ConvertByte3ToString(byte[] bytes) {
        String temp = new String("");
        temp += CharCode[((bytes[0]) >>> 2) & 0x3f];
        temp += CharCode[((bytes[0] & 0x03) << 4) | ((bytes[1] >>> 4) & 0x0f)];
        temp += CharCode[((bytes[1] & 0x0f) << 2) | ((bytes[2] >>> 6) & 0x03)];
        temp += CharCode[bytes[2] & 0x3f];
        return temp;
    }

    private static String ConvertByte2ToString(byte[] bytes) {
        String temp = new String("");
        temp += CharCode[((bytes[0]) >>> 2) & 0x3f];
        temp += CharCode[((bytes[0] & 0x03) << 4) | ((bytes[1] >>> 4) & 0x0f)];
        temp += CharCode[((bytes[1] & 0x0f) << 2)];
        temp += '=';
        return temp;
    }

    private static String ConvertByteToString(byte[] bytes) {
        String temp = new String("");
        temp += CharCode[((bytes[0]) >>> 2) & 0x3f];
        temp += CharCode[((bytes[0] & 0x03) << 4)];
        temp += "==";
        return temp;
    }


    /**
     * 分割字符串进SET
     */
    public static Set<String> split(String str) {
        return split(str, ",");
    }

    /**
     * 分割字符串进SET
     */
    public static Set<String> split(String str, String separator) {


        Set<String> set = Sets.newLinkedHashSet();
        if (Strings.isNullOrEmpty(str)) {
            return set;
        }
        for (String s : str.split(separator)) {
            set.add(s);
        }
        return set;
    }


}
