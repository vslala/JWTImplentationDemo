package com.bma.jwt.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.bma.jwt.app.fixture.JWTServiceFixture;

public class JWTServiceTest {
	
	private static final String SPLIT_BY_DOT = "\\.";
	private JWTService jwtService;
	
	@Before
	public void setUp() {
		jwtService = new JWTService();
	}
	
	@Test
	public void itShouldGenerateJWTToken() {
		String jwt = jwtService.genTokenWithData(JWTServiceFixture.buildUserVO().getJSONData());
		System.out.println(jwt);
		assertEquals(3 ,jwt.split(SPLIT_BY_DOT).length);
	}

	@Test
	public void itShouldValidateToken() {
		String jwt = jwtService.genTokenWithData(JWTServiceFixture.buildUserVO().getJSONData());
		boolean isValid = jwtService.validateJWT(jwt);
		assertTrue(isValid);
	}
	
}
