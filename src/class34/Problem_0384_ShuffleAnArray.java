package class34;

public class Problem_0384_ShuffleAnArray {

	class Solution {
		private int[] origin;
		private int[] shuffle;
		private int N;

		public Solution(int[] nums) {
			origin = nums;
			N = nums.length;
			shuffle = new int[N];
			for (int i = 0; i < N; i++) {
				shuffle[i] = origin[i];
			}
		}

		public int[] reset() {
			return origin;
		}

		// 0到N-1位置，随机一个数，放到 N-1位置
		// 0到N-2位置，随机一个数，放到 N-2位置
		public int[] shuffle() {
			for (int i = N - 1; i >= 0; i--) {
				int r = (int) (Math.random() * (i + 1));
				int tmp = shuffle[r];
				shuffle[r] = shuffle[i];
				shuffle[i] = tmp;
			}
			return shuffle;
		}
	}

}
