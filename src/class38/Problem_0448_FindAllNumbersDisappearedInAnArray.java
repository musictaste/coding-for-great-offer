package class38;

import java.util.ArrayList;
import java.util.List;

public class Problem_0448_FindAllNumbersDisappearedInAnArray {

	// 下标循环怼，之前的课程：数字缺失问题
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return ans;
		}
		int N = nums.length;
		for (int i = 0; i < N; i++) {
			// 从i位置出发，去玩下标循环怼
			walk(nums, i);
		}
		// 遍历数组，看哪些位置的数字不是下标+1，那么哪些数字就缺失
		for (int i = 0; i < N; i++) {
			if (nums[i] != i + 1) {
				ans.add(i + 1);
			}
		}
		return ans;
	}

	public static void walk(int[] nums, int i) {
		while (nums[i] != i + 1) { // 不断从i发货
			int nexti = nums[i] - 1;
			// 如果发货的位置上的数字已经符合要求，那么跳下一个位置
			if (nums[nexti] == nexti + 1) {
				break;
			}
			swap(nums, i, nexti);
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
