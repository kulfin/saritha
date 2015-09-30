package fi.hut.soberit.agilefant.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WrappingMD5PasswordEncoder implements PasswordEncoder {

	private PasswordEncoder encoder;
	
	public void setPasswordEncoder(PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		return this.encoder.encode(DigestUtils.md5Hex(rawPassword.toString()));
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return this.encoder.matches(DigestUtils.md5Hex(rawPassword.toString()), encodedPassword);
	}
}
