package class29;

public class Problem_0062_UniquePaths {

	// m 行
	// n 列
	// 下：走m-1步
	// 右：走n-1步
	public static int uniquePaths(int m, int n) {
		int right = n - 1;
		int all = m + n - 2;
		long o1 = 1;
		long o2 = 1;
		// all =10, right = 4
		// all = 5 * 6 * 7 * 8 * 9 * 10
		// right= 1 * 2 * 3 * 4 * 5 * 6
		// o1乘进去的个数 一定等于 o2乘进去的个数
		for (int i = right + 1, j = 1; i <= all; i++, j++) {
			o1 *= i;
			o2 *= j;
			long gcd = gcd(o1, o2);
			o1 /= gcd;
			o2 /= gcd;
		}
		return (int) o1;
	}

	// 调用的时候，请保证初次调用时，m和n都不为0
	// 求最大公约数，辗转相除法
	public static long gcd(long m, long n) {
		return n == 0 ? m : gcd(n, m % n);
	}

}
