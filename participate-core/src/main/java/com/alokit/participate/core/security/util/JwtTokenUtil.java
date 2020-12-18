package com.alokit.participate.core.security.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import com.alokit.participate.core.util.DateUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class); 
	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_CREATED = "created";
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	@Value("${jwt.tokenHead}")
	private String tokenHead;

	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}
	
	public String refreshHeadToken(String oldToken) {
		if("".equals(oldToken)) {
			return null;
		}
		
		String token = oldToken.substring(tokenHead.length());
		
		if("".equals(token)) {
			return null;
		}
		
		Claims claims = getClaimsFromToken(token);
		if(claims == null) {
			return null;
		}
		
		if(isTokenExpired(token)) {
			return null;
		}
		
		if(tokenRefreshJustBefore(token, 30 * 60)) {
			return token;
		} else {
			claims.put(CLAIM_KEY_CREATED, new Date());
			return generateToken(claims);
		}
	}

	private boolean tokenRefreshJustBefore(String token, int time) {
		Claims claims = getClaimsFromToken(token);
		Date createDate = claims.get(CLAIM_KEY_CREATED, Date.class);
		Date refreshDate = new Date();
		
		if(refreshDate.after(createDate) && refreshDate.before(DateUtil.offsetSecond(createDate, time))) {
			return true;
		}
		
		return false;
	}

	private boolean isTokenExpired(String token) {
		Date expiredDate = getExpiredDateFromToken(token);
		return expiredDate.before(new Date());
	}

	private Date getExpiredDateFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return claims.getExpiration();
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			LOGGER.info("Cannot get claims from the token" , token);
		}
		
		return claims;
	}
}
