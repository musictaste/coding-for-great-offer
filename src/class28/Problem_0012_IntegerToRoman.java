package class28;

public class Problem_0012_IntegerToRoman {
	// I：1
	// V:5
	// X:10
	// L:50
	// C:100
	// D:500
	// M:1000
	public static String intToRoman(int num) {
		String[][] c = { 
				{ "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }, // 范围：0到9
				{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }, // 范围：10到90
				{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }, // 范围：100到900
				{ "", "M", "MM", "MMM" } };
		StringBuilder roman = new StringBuilder();
		roman
		.append(c[3][num / 1000 % 10])
		.append(c[2][num / 100 % 10])
		.append(c[1][num / 10 % 10])
		.append(c[0][num % 10]);
		return roman.toString();
	}

}
