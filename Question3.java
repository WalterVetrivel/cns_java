import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;

public class Question3 {
	public static String getInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String input = scanner.nextLine();

		return input;
	}

	public static StringBuffer getReadableHash(byte[] byteArray) {
		StringBuffer readableHash = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			readableHash.append(0xFF & byteArray[i]);
		}

		return readableHash;
	}

	public static KeyPair generateKeyPair(String keyPairAlgorithm) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyPairAlgorithm);
		keyPairGenerator.initialize(2048);

		return keyPairGenerator.genKeyPair();
	}

	public static byte[] generateDigitalSignature(Signature signature, String signatureAlgorithm, PrivateKey privateKey,
			byte[] data) throws Exception {
		signature.initSign(privateKey);

		signature.update(data);

		return signature.sign();
	}

	public static boolean verifySignature(Signature signature, PublicKey publicKey, byte[] data, byte[] signatureBytes)
			throws Exception {
		signature.initVerify(publicKey);
		signature.update(data);

		return signature.verify(signatureBytes);
	}

	public static void main(String[] args) {
		try {

			String plainTextStr = getInput();
			byte[] plainText = plainTextStr.getBytes("UTF-8");

			String signatureAlgorithm = "SHA256withRSA";
			String keyPairAlgorithm = "RSA";

			KeyPair keyPair = generateKeyPair(keyPairAlgorithm);

			Signature signature = Signature.getInstance(signatureAlgorithm);
			byte[] signatureBytes = generateDigitalSignature(signature, signatureAlgorithm, keyPair.getPrivate(),
					plainText);

			System.out.println("Digital Signature: " + getReadableHash(signatureBytes));

			if (verifySignature(signature, keyPair.getPublic(), plainText, signatureBytes)) {
				System.out.println("Signature is valid");
			} else {
				System.out.println("Signature is invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}