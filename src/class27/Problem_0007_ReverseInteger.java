package class27;

public class Problem_0007_ReverseInteger {

	public static int reverse(int x) {
		boolean neg = ((x >>> 31) & 1) == 1;
		x = neg ? x : -x;
		int m = Integer.MIN_VALUE / 10;
		int o = Integer.MIN_VALUE % 10;
		int res = 0;
		while (x != 0) {
			// 溢出的情况
			// 当前负数除10的值 比 最小数除10的值更小 || 除10的情况相等时，取模的值不能更小
			if (res < m || (res == m && x % 10 < o)) {
				return 0;
			}
			// 取模的数*10
			res = res * 10 + x % 10;
			x /= 10;
		}
		return neg ? res : Math.abs(res);
	}
}
