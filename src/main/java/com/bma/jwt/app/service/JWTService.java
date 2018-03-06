package com.bma.jwt.app.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.bma.jwt.app.vo.JWTPayload;
import com.bma.jwt.app.vo.User;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTService {

	private static final String SECRET_KEY = "secret";
	private static final List<String> issuerList = Arrays.asList(new String[]{"BMA_ISSUER", "BMA_ISSUER_ONE"});
	private static final List<String> audienceList = Arrays.asList(new String[]{"BMA_AUDIENCE", "BMA_AUDIENCE_ONE"});

	public String genTokenWithData(String jsonData) {
		return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
			.setSubject("https://www.bemyaficionado.com/json-web-token")
			.setExpiration(new Date((System.currentTimeMillis()/1000) * 60 * 24))
			.setAudience("BMA_AUDIENCE")
			.setIssuer("BMA_ISSUER")
			.claim("data", jsonData)
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
			.compact();
	}

	public boolean validateJWT(String jwt) {
		Jws<Claims> claims = Jwts.parser()
			.setSigningKey(SECRET_KEY.getBytes())
			.parseClaimsJws(jwt);
		JWTPayload<User> payload = new JWTPayload<User>();
		payload.setAud(claims.getBody().getAudience());
		payload.setExp(claims.getBody().getExpiration());
		payload.setIat(claims.getBody().getIssuedAt());
		payload.setIss(claims.getBody().getIssuer());
		payload.setData(new Gson().fromJson(String.valueOf(claims.getBody().get("data")), User.class));
		
		return (!isExpired(payload)
				&& issuerList.contains(payload.getIss())
				&& audienceList.contains(payload.getAud()));
	}

	private boolean isExpired(JWTPayload<User> payload) {
		return new Date(System.currentTimeMillis()).after(payload.getExp());
	}

}
