package org.anirudha.watersupplier.tokenfilter;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    boolean validateToken(String token);

    Authentication getAuthentication(String token);
}
