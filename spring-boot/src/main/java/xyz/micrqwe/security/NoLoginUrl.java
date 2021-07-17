package xyz.micrqwe.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shaowenxing on 2018/1/8.
 */
public class NoLoginUrl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        if (authException != null) {
            request.getRequestDispatcher("/login/noLogin")
                    .forward(request, response);
        }
    }
}
