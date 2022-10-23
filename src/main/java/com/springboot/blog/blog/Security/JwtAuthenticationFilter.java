package com.springboot.blog.blog.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get token
        String requestToken = request.getHeader("Authorization");

        String username = null;
        String token = null;

        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try{
                username = jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e){
                System.out.println("Unable to get jwt token");
            } catch (ExpiredJwtException expiredJwtException){
                System.out.println("Token has expired");
            } catch (MalformedJwtException malformedJwtException){
                System.out.println("Invalid Jwt Token");
            }
        } else {
            System.out.println("token does not start with Bearer");
        }

        //once we get the token, we can validate...
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtTokenHelper.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Invalid JWT Token");
            }

        } else {
            System.out.println("user name is null or context is not null");
        }

        filterChain.doFilter(request,response);

    }
}
