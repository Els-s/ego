package org.javaboy.egosso;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;

/**
 * 用户登录过滤器
 * <p>
 * 这个过滤器主要作用就是在用户登录成功时，给用户返回一个 JWT 生成的 token
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    protected LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        this.setAuthenticationManager(authenticationManager);
    }

    /**
     * 这个方法中提取出来用户的登录信息进行登录
     *
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(token);
    }

    /**
     * 登录成功的回调
     *
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();//获取当前登录成功的用户角色
        String username = (String) authResult.getName();//获取当前登录成功的用户名
        StringBuffer sb = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            sb.append(authority.getAuthority())
                    .append(",");
        }
        String jwt = Jwts.builder()
                .claim("authorities", sb.toString())
                .signWith(SignatureAlgorithm.HS512, "123.javaboy!")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .setSubject(username)
                .compact();//生成 JWT 字符串
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(jwt);
        out.close();
        out.flush();
    }

    /**
     * 登录失败的回调
     *
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write("fail");
        out.close();
        out.flush();
    }
}
