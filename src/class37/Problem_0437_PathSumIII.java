package class37;

import java.util.HashMap;

public class Problem_0437_PathSumIII {

	public class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static int pathSum(TreeNode root, int sum) {
		// 某个前缀和出现了几次
		HashMap<Long, Integer> preSumMap = new HashMap<>();
		// 0出现了1次，一定要加这条数据，防止扣掉一些可能性
		preSumMap.put(0L, 1);
		return process(root, sum, 0, preSumMap);
	}

	// sum,要求的累加和
	// preAll:之前节点加起来等于多少
	// preSummap:前缀和出现的次数
	// 返回方法数
	public static int process(TreeNode x, int sum, long preAll, HashMap<Long, Integer> preSumMap) {
		if (x == null) {
			return 0;
		}
		long all = preAll + x.val;
		int ans = 0;
		// 词频表中查看是否存在符合要求的前缀和
		if (preSumMap.containsKey(all - sum)) {
			ans = preSumMap.get(all - sum);
		}
		// 词频表中没有出现前缀和，增加记录
		if (!preSumMap.containsKey(all)) {
			preSumMap.put(all, 1);
		} else {
			// 词频表中存在前缀和记录，次数+1
			preSumMap.put(all, preSumMap.get(all) + 1);
		}
		// 求左树和右树的结果
		ans += process(x.left, sum, all, preSumMap);
		ans += process(x.right, sum, all, preSumMap);
		// 出这个节点的时候，如果词频表中只出现1次，那么移除记录；否则次数-1
		if (preSumMap.get(all) == 1) {
			preSumMap.remove(all);
		} else {
			preSumMap.put(all, preSumMap.get(all) - 1);
		}
		return ans;
	}

}
