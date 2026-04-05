package com.student.management.JwtToken;
import com.student.management.Service.CustomUserDetailsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

    @Component
    public class JwtFilter extends OncePerRequestFilter {

        @Autowired
        JwtUtil jwtUtil;

        @Autowired
        CustomUserDetailsService userDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain)
                throws ServletException, IOException {

            String path = request.getServletPath();

            // Skips login & register
            if (path.equals("/login") || path.equals("/register")) {
                chain.doFilter(request, response);
                return;
            }

            String header = request.getHeader("Authorization");

            String token = null;
            String username = null;

            if(header != null && header.startsWith("Bearer ")){

                token = header.substring(7);

                username = jwtUtil.extractUsername(token);
            }

            if(username != null &&
                    SecurityContextHolder.getContext().getAuthentication()==null){

                var userDetails =
                        userDetailsService.loadUserByUsername(username);

                if(jwtUtil.validateToken(token,username)){

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request));

                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);
                }
            }

            chain.doFilter(request,response);
        }
    }
