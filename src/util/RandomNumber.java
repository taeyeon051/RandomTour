package util;

import java.util.Random;

public class RandomNumber {
	public static String certifyNumber() {
		Random rand = new Random();
		String number = "";
		
		for (int i = 0; i < 6; i++) {
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
