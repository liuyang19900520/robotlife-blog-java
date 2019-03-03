package com.liuyang19900520.robotlife.blog.shiro.filter;


import com.liuyang19900520.robotlife.blog.common.pojo.Messages;
import com.liuyang19900520.robotlife.blog.common.pojo.ResultVo;
import com.liuyang19900520.robotlife.blog.shiro.CryptoUtil;
import com.liuyang19900520.robotlife.blog.shiro.token.JwtToken;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Created by liuyang on 2018/3/18
 *
 * @author liuya
 */
@Slf4j
public class JwtAuthFilter extends StatelessFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated()) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        if (isJwtSubmission(request)) {
            AuthenticationToken token = createJwtToken(request, response);
            Subject subject = getSubject(request, response);
            try {
                subject.login(token);
                if (subject.isAuthenticated()) {
                    return true;
                }
            } catch (Exception e) {
                if (e.getCause().toString().contains("ExpiredJwtException")) {
                    String fresh = ((JwtToken) token).getRefreshToken();
                    String verifyToken = CryptoUtil.verifyFreshToken(fresh);

                    if (fresh.equals(verifyToken)) {
                        Claims claims = CryptoUtil.parserToken(fresh);
                        String userName = claims.getSubject();
                        Set<String> roles = CryptoUtil.getRoles(fresh);
                        Set<String> permissions = CryptoUtil.getPerms(fresh);

                        Date expiration = claims.getExpiration();
                        if (expiration.getTime() < System.currentTimeMillis()) {
                            throw new AuthenticationException("jwt过期");
                        } else {
                            //生成accessToken

                            StringBuffer strRoles = new StringBuffer();
                            StringBuffer strPerms = new StringBuffer();

                            roles.stream().filter(role -> !role.equals("") && role != null)
                                    .forEachOrdered(s -> strRoles.append(s).append(","));


                            permissions.stream().filter(permission -> !permission.equals("") && permission != null)
                                    .forEachOrdered(s -> strPerms.append(s).append(","));

                            String permsJwt = "";
                            String rolesJwt = "";

                            if (!strRoles.toString().equals("") && strRoles != null) {
                                rolesJwt = strRoles.substring(0, strRoles.length() - 1);
                            }
                            if (!strPerms.toString().equals("") && strPerms != null) {
                                permsJwt = strPerms.substring(0, strPerms.length() - 1);
                            }

                            String jwt = CryptoUtil.issueJwt(UUID.randomUUID().toString(), userName,
                                    rolesJwt, permsJwt, new Date(), CryptoUtil.ACCESS_TOKEN_TYPE);

                            String newFresh = CryptoUtil.issueJwt(UUID.randomUUID().toString(), userName,
                                    rolesJwt, permsJwt, new Date(), CryptoUtil.REFRESH_TOKEN_TYPE);

                            ((HttpServletResponse) response).setHeader("token", jwt);
                            ((HttpServletResponse) response).setHeader("refreshToken", newFresh);

                            ((JwtToken) token).setJwt(jwt);
                            subject.login(token);
                            if (subject.isAuthenticated()) {
                                return true;
                            }
                        }
                    } else {
                        throw new AuthenticationException("jwt无效");
                    }

                }

                log.error(e.getMessage(), e);
                ResultVo.error(Messages.UNAUTHORIZATION
                        , e.getMessage());
            }
        }
        return false;
    }

}
