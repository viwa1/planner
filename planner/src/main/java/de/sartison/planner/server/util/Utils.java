package de.sartison.planner.server.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public final class Utils {

	private Utils() {

	}

	public static final String sha256(String string) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));
		String hashString = new String(Hex.encodeHex(hashBytes));

		return hashString;
	}
}
