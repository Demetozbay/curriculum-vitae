package com.demet.curriculumvitae.configuration.jwt;

import com.demet.curriculumvitae.configuration.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "roles";
    @Autowired
    private JwtProperties jwtProperties;

    private SecretKey secretKey;

    @PostConstruct
    protected void init() {
        String secret = Base64.getEncoder()
                              .encodeToString(jwtProperties.getSecret()
                                                           .getBytes());
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
        Claims claims = Jwts.claims()
                            .setSubject(username);
        claims.put(AUTHORITIES_KEY, authorities.stream()
                                               .map(GrantedAuthority::getAuthority)
                                               .collect(Collectors.joining(",")));

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getExpiration());
        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(secretKey, SignatureAlgorithm.HS256)
                   .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get(AUTHORITIES_KEY)
                                                                         .toString());
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                                     .setSigningKey(secretKey)
                                     .build()
                                     .parseClaimsJws(token);
            return !claims.getBody()
                          .getExpiration()
                          .before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }
}
