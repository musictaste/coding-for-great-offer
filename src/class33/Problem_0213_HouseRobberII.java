package class33;

public class Problem_0213_HouseRobberII {

	// 打家劫舍I的解法
	// arr 长度大于等于1
	public static int pickMaxSum(int[] arr) {
		int n = arr.length;
		// dp[i] : arr[0..i]范围上，随意选择，但是，任何两数不能相邻。得到的最大累加和是多少？
		int[] dp = new int[n];
		// 0位置就是它自己
		dp[0] = arr[0];
		// 1位置就是0和1位置的最大值
		dp[1] = Math.max(arr[0], arr[1]);
		for (int i = 2; i < n; i++) {
			// 三种可能性
			// i自己
			int p1 = arr[i];
			// 不选i，那么就是i-1
			int p2 = dp[i - 1];
			// 选1，那么就是i-2+i的累加和
			int p3 = arr[i] + dp[i - 2];
			dp[i] = Math.max(p1, Math.max(p2, p3));
		}
		return dp[n - 1];
	}

	// 打家劫舍II
	// 优化方案：通过有限几个变量替代dp表
	public static int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		// 0位置，dp[0]就是它自己
		if (nums.length == 1) {
			return nums[0];
		}
		// i位置，那是0和1取最大值
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		// 可能性1:0到N-2位置求最好
		int pre2 = nums[0];
		int pre1 = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length - 1; i++) {
			// 三种可能性
			// 可能性1：i位置自己
			// 可能性2：i-1位置
			// 可能性3：i-2位置+i自己
			// 因为题意规定，数组为非负，所以可能性1就舍去了
			// 可能性2和可能性3取最大
			int tmp = Math.max(pre1, nums[i] + pre2);
			pre2 = pre1;
			pre1 = tmp;
		}

		// 可能性2：1到N-1位置求最好
		int ans1 = pre1;
		pre2 = nums[1];
		pre1 = Math.max(nums[1], nums[2]);
		for (int i = 3; i < nums.length; i++) {
			int tmp = Math.max(pre1, nums[i] + pre2);
			pre2 = pre1;
			pre1 = tmp;
		}
		int ans2 = pre1;
		return Math.max(ans1, ans2);
	}

}
