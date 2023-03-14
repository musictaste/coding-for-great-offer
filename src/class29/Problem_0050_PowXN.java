package class29;

public class Problem_0050_PowXN {

	// 求x的n次方，最快
	// 75= 64 + 8 + 2 + 1 = 1001011
	// 10^75 = 1 * 10^1 * 10^2 * 10^8 * 10^64
	public static int pow(int a, int n) {
		int ans = 1;
		int t = a;
		while (n != 0) {
			// n的二进制为1
			if ((n & 1) != 0) {
				ans *= t;
			}
			t *= t;
			n >>= 1;
		}
		return ans;
	}

	// x的n次方，n可能是负数
	// 75= 64 + 8 + 2 + 1 = 1001011
	// 10^75 = 1 * 10^1 * 10^2 * 10^8 * 10^64
	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		// 负数转正数
		int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ans *= t;
			}
			pow >>= 1;
			t = t * t;
		}
		if (n == Integer.MIN_VALUE) {
			ans *= x;
		}
		return n < 0 ? (1D / ans) : ans;
	}

}
