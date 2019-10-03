import java.security.MessageDigest;
import java.util.Scanner;

public class Question2 {
	public static String getInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String input = scanner.nextLine();

		return input;
	}

	public static byte[] generateHash(byte[] plainText, String algorithm) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		messageDigest.update(plainText);

		return messageDigest.digest();
	}

	public static StringBuffer getReadableHash(byte[] byteArray) {
		StringBuffer readableHash = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			readableHash.append(0xFF & byteArray[i]);
		}

		return readableHash;
	}

	public static void main(String[] args) {
		String algorithm = "SHA-256";

		try {
			byte[] plainText = getInput().getBytes("UTF-8");
			byte[] digest = generateHash(plainText, algorithm);

			StringBuffer readableDigest = getReadableHash(digest);

			System.out.println(readableDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}