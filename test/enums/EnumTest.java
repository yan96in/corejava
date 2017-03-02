package enums;

import java.util.Scanner;

public class EnumTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a size: (SMALL,MEDIUM,LARGE,EXTRA_LARGE)");
		String input = in.next().toUpperCase();
		Size size = Enum.valueOf(Size.class, input);
		System.out.println("size=" + size);
		System.out.println("abbreviation=" + size.getAbbrevation());
		if (size == Size.EXTRA_LARGE)
			System.out.println("Good job--you paid attention to the _.");
	}
}

enum Size {
	SMALL("S"), MEDIUM("L"), EXTRA_LARGE("XL");
	private Size(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbrevation() {
		return abbreviation;
	}

	private String abbreviation;
}