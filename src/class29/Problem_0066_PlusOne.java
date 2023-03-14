package class29;

public class Problem_0066_PlusOne {

	public static int[] plusOne(int[] digits) {
		int n = digits.length;
		// n-1是低位
		for (int i = n - 1; i >= 0; i--) {
			// 如果有一位不需要进位了，那么就直接返回答案
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}
		// 再造数据，最高为填1
		int[] ans = new int[n + 1];
		ans[0] = 1;
		return ans;
	}

}
