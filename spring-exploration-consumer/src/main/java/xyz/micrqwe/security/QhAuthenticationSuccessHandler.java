package xyz.micrqwe.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 */
public class QhAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    final Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        super.onAuthenticationSuccess(request, response, authentication);

//        super.onAuthenticationSuccess(request, response, authentication)
        System.out.println("~~~~~~~~~~~~~ 认证成功：username = ${request.getParameter('j_username')}");
        log.debug("~~~~~~~~~~~~~ 认证成功：username = ${request.getParameter('j_username')}");

        Object account = request.getAttribute("REQUEST_KEY_LOGIN_ACCOUNT");
        Object type = request.getAttribute("REQUEST_KEY_LOGIN_TYPE");

        HttpSession session = request.getSession();
        session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, authentication);
//        clearAuthenticationAttributes(request);
        request.getRequestDispatcher("/login/success")
                .forward(request, response);
    }
}
