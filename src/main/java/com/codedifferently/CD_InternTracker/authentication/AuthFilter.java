package com.codedifferently.CD_InternTracker.authentication;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    private final TokenUtil tokenUtil;

    public AuthFilter(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authorizationHeader = httpRequest.getHeader("Authorization");

        if (httpRequest.getRequestURI().startsWith("/api/auth")) {
            chain.doFilter(request, response);
            return;
        }

        if (authorizationHeader == null ||   authorizationHeader.length() < 7) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid request format");
            return;
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);
        try {
            if (!tokenUtil.validateToken(token, tokenUtil.extractUsername(token))) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }

            String username = tokenUtil.extractUsername(token);
            boolean isAdmin = tokenUtil.extractIsAdmin(token);



            httpRequest.setAttribute("username", username);
            httpRequest.setAttribute("isAdmin", isAdmin);

            String textRole = "";
            if (isAdmin) { textRole = "ADMIN";}
            else {textRole = "USER";}

            UserDetails userDetails = User
                    .withUsername(username)
                    .password("")
                    .roles(textRole)
                    .build();


            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );


            SecurityContextHolder.getContext().setAuthentication(authentication);


            chain.doFilter(request, response);



        } catch (ExpiredJwtException e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
        } catch (JwtException e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalid");
        }
    }
}
