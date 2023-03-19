package class32;

import java.util.HashMap;

public class Problem_0166_FractionToRecurringDecimal {

	public static String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		// "+" or "-"
		// boolean异或，只有不同才会true，需要补符号
		res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
		long num = Math.abs((long) numerator);
		long den = Math.abs((long) denominator);
		// integral part 整数部分
		res.append(num / den);
		num %= den;
		// 可以整除
		if (num == 0) {
			return res.toString();
		}
		// fractional part 小数部分
		res.append(".");
		// 用哈希表记录小数部分数字出现的位置
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		map.put(num, res.length());
		while (num != 0) {
			// 余数*10,继续
			num *= 10;
			res.append(num / den);
			num %= den;
			// 如果哈希表中如果有当前余数，那么前面加（字符
			if (map.containsKey(num)) {
				int index = map.get(num);
				res.insert(index, "(");
				res.append(")");
				break;
			} else {
				map.put(num, res.length());
			}
		}
		return res.toString();
	}

	public static void main(String[] args) {
		System.out.println(fractionToDecimal(127, 999));
	}

}
