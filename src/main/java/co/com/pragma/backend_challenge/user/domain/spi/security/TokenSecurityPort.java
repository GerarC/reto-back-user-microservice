package co.com.pragma.backend_challenge.user.domain.spi.security;

import co.com.pragma.backend_challenge.user.domain.model.User;

public interface TokenSecurityPort {
    /**
     * Creates a new token for the given user using their UUID, and Role
     * @param user given user
     * @return returns a new token
     */
    String createToken(User user);

    /**
     * @param token a token already generated
     * @param username id of the user
     * @return true if is a valid token false in the other case
     */
    boolean validateToken(String token, String username);

    /**
     * Get id of the user from Token
     * @param token a valid token
     * @return The UUID of the user
     */
    String getUsername(String token);
}
