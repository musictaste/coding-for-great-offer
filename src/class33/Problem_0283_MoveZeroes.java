package class33;

public class Problem_0283_MoveZeroes {

	public static void moveZeroes(int[] nums) {
		// 有效位置下标从0开始，如果要跟有效位置的下一个无效位置交换，那么to应该从-1开始
		int to = 0;
		for (int i = 0; i < nums.length; i++) {
			// 当前位置不是0，跟最后一个有效位置交换，有效位置+1
			if (nums[i] != 0) {
				swap(nums, to++, i);
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
