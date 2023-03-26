package class35;

import java.util.HashMap;

public class Problem_0454_4SumII {

	public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		// 累加和，出现的情况数
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				sum = A[i] + B[j];
				if (!map.containsKey(sum)) {
					map.put(sum, 1);
				} else {
					map.put(sum, map.get(sum) + 1);
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < D.length; j++) {
				sum = C[i] + D[j];
				// 优化：不需要再创建一个哈希表，可以利用上面的哈希表，查看CD累加和的相反数是否在哈希表中出现
				if (map.containsKey(-sum)) {
					ans += map.get(-sum);
				}
			}
		}
		return ans;
	}

}
