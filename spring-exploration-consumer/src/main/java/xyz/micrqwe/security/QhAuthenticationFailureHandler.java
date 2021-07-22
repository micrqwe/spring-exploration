package xyz.micrqwe.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class QhAuthenticationFailureHandler implements AuthenticationFailureHandler {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        System.out.println("~~~~~~~~~~~~~ 认证失败：" + request.getParameterNames());
        log.debug("~~~~~~~~~~~~~ 认证失败：username = ${request.getParameter('j_username')}");

        Object account = request.getAttribute("REQUEST_KEY_LOGIN_ACCOUNT");
        Object type = request.getAttribute("REQUEST_KEY_LOGIN_TYPE");
//
        request.getRequestDispatcher("/login/error")
                .forward(request, response);
    }
}
