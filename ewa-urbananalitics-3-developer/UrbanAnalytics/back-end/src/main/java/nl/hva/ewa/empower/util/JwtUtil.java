package nl.hva.ewa.empower.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nl.hva.ewa.empower.user.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user);
    }

    public String createToken(Map<String, Object> claims, User subject){
        Date currentTime = new Date(System.currentTimeMillis());
        Date expireTime = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject.getEmail())
                .setIssuedAt(currentTime)
                .setExpiration(expireTime)
                .signWith(this.key).compact();
    }

    public boolean validateToken(String token, User user){
        final String email = extractEmail(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
    }
}
