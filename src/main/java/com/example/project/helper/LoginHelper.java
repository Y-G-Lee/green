package com.example.project.helper;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LoginHelper {
	
	private String generateTemporaryPassword(int[] intArray, String[] lowerArray, String[] strArray,
			String[] bigArray) {
		SecureRandom random = new SecureRandom();
		List<String> passwordChars = new ArrayList<>();

		int passwordLength = random.nextInt(5) + 8; // 0~4 + 8 = 8~12

		passwordChars.add(bigArray[random.nextInt(bigArray.length)]);
		passwordChars.add(lowerArray[random.nextInt(lowerArray.length)]);
		passwordChars.add(String.valueOf(intArray[random.nextInt(intArray.length)]));
		passwordChars.add(strArray[random.nextInt(strArray.length)]);

		List<String> allChars = new ArrayList<>();
		Collections.addAll(allChars, bigArray);
		Collections.addAll(allChars, lowerArray);
		for (int i : intArray) {
			allChars.add(String.valueOf(i));
		}
		Collections.addAll(allChars, strArray);

		while (passwordChars.size() < passwordLength) {
			passwordChars.add(allChars.get(random.nextInt(allChars.size())));
		}

		Collections.shuffle(passwordChars);

		StringBuilder password = new StringBuilder();
		for (String charStr : passwordChars) {
			password.append(charStr);
		}

		return password.toString();
	}
	
	public String makePassword() {
		int[] intArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		String[] lowerArray = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z" };
		String[] strArray = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "{", "}" };
		String[] BigArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		
		return generateTemporaryPassword(intArray, lowerArray, strArray, BigArray);
	}

}
