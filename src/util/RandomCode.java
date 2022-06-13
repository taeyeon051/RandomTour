package util;

import java.util.Random;

public class RandomCode {
	public static String randomNumber(int length) {
		Random rand = new Random();
		String number = "";
		
		for (int i = 0; i < length; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			if (!number.contains(ran)) {
				number += ran;
			} else {
				i -= 1;
			}
		}
		
		return number;
	}
}
