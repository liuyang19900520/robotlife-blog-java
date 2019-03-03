package com.liuyang19900520.robotlife.blog.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by liuyang on 2018/3/18
 *
 * @author liuya
 */
@Data
public class JwtToken implements AuthenticationToken {

    private String jwt;// json web token
    private String host;// 客户端IP
    private String refreshToken;// 客户端IP


    public JwtToken(String jwt, String host, String refreshToken) {
        this.jwt = jwt;
        this.host = host;
        this.refreshToken = refreshToken;
    }

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }

}
