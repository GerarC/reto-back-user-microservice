package co.com.pragma.backend_challenge.user.infrastructure.configuration.security.filter;

import co.com.pragma.backend_challenge.user.domain.api.security.TokenServicePort;
import co.com.pragma.backend_challenge.user.infrastructure.util.constant.ConfigurationConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final TokenServicePort tokenServicePort;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(ConfigurationConstants.AUTHORIZATION_HEADER);

        if (authorizationHeader == null || !authorizationHeader.startsWith(ConfigurationConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.substring(ConfigurationConstants.TOKEN_PREFIX.length());
        String username = tokenServicePort.getUsername(token);

        setContextAuthentication(request, token, username);

        filterChain.doFilter(request, response);

    }

    private void setContextAuthentication(HttpServletRequest request, String token, String username){
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(tokenServicePort.validateToken(token, username)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }
}
