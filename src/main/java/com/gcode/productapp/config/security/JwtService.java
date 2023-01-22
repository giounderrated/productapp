package com.gcode.productapp.config.security;

import com.gcode.productapp.users.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService {

	private final static String SECRET_KEY = "28472B4B6250655368566D5971337436773979244226452948404D635166546A";

	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		Claims claims;
		try {
			claims = Jwts.parserBuilder()
					.setSigningKey(getSignInKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			claims = null;
		}
		return claims;
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	public String generateToken(User userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public String generateToken(Map<String, Object> extraClaims, User user) {
//		Map<String,String> user_data = new HashMap<>();
//		user_data.put("username",userDetails.getUsername());
//		user_data.put("userType",userDetails.getAuthorities().toArray()[0].toString());
//		extraClaims.put("user",user_data);
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(user.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(getExpirationDate())
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String email = extractEmail(token);
		return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Date getExpirationDate() {
		return new Date(System.currentTimeMillis() + 1000 * 60 * 60*10);
	}
}
