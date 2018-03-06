package com.bma.jwt.app.fixture;

import com.bma.jwt.app.vo.User;

public class JWTServiceFixture {
	public static User buildUserVO() {
		User user = new User();
		user.setId(1);
		user.setFirstName("Varun");
		user.setLastName("Shrivastava");
		user.setPassword("never_send_password_in_jwt_token");
		user.setRole("admin");
		user.setUsername("vslala");
		return user;
	}
}
