package class10;

// 本题测试链接 : https://leetcode.com/problems/jump-game-ii/
public class Code01_JumpGame {

	public static int jump(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// 小人跳step步最远能到cur位置，如果允许小人多跳一步，最远可以导到next位置
		int step = 0;
		int cur = 0;
		int next = 0;
		for (int i = 0; i < arr.length; i++) {
			if (cur < i) {
				step++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return step;
	}

}
