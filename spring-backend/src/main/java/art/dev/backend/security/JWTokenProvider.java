package art.dev.backend.security;

import art.dev.backend.domains.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static art.dev.backend.security.SecurityConstants.EXPIRATION_TIME;


@Component
public class JWTokenProvider {


    public String secret;

    public String generateToken(Authentication authentication) {


        User user = (User) authentication.getPrincipal();
        Date dateNow = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(dateNow.getTime() + EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(dateNow)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT Token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String id = claims.get("id").toString();

        return Long.parseLong(id);
    }


    @PostConstruct
    private void init() {
        Dotenv dotenv = Dotenv.load();
        secret = dotenv.get("JWT_SECRET");
    }
}
