import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Question1 {
	public static SecretKey generateKey(String algorithm) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		SecureRandom secureRandom = new SecureRandom();
		int keyBitSize = 56;

		keyGenerator.init(keyBitSize, secureRandom);
		SecretKey secretKey = keyGenerator.generateKey();

		return secretKey;
	}

	public static byte[] encrypt(byte[] plainText, String algorithm) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm);

		SecretKey secretKey = generateKey(algorithm);

		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] cipherText = cipher.doFinal(plainText);
		return cipherText;
	}

	public static StringBuffer getReadableHash(byte[] byteArray) {
		StringBuffer readableHash = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			readableHash.append(0xFF & byteArray[i]);
		}

		return readableHash;
	}

	public static void main(String[] args) {
		try {
			String plainTextStr = "JCA is a powerful tool";
			byte[] plainText = plainTextStr.getBytes("UTF-8");

			byte[] cipherText = encrypt(plainText, "DES");

			System.out.println(getReadableHash(cipherText));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}