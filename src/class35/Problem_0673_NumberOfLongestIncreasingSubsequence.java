package class35;

import java.util.ArrayList;
import java.util.TreeMap;

public class Problem_0673_NumberOfLongestIncreasingSubsequence {

	// 好理解的方法，时间复杂度O(N^2)
	public static int findNumberOfLIS1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int[] lens = new int[n];
		int[] cnts = new int[n];
		lens[0] = 1;
		cnts[0] = 1;
		int maxLen = 1;
		int allCnt = 1;
		for (int i = 1; i < n; i++) {
			int preLen = 0;
			int preCnt = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] >= nums[i] || preLen > lens[j]) {
					continue;
				}
				if (preLen < lens[j]) {
					preLen = lens[j];
					preCnt = cnts[j];
				} else {
					preCnt += cnts[j];
				}
			}
			lens[i] = preLen + 1;
			cnts[i] = preCnt;
			if (maxLen < lens[i]) {
				maxLen = lens[i];
				allCnt = cnts[i];
			} else if (maxLen == lens[i]) {
				allCnt += cnts[i];
			}
		}
		return allCnt;
	}

	// 优化后的最优解，时间复杂度O(N*logN)
	public static int findNumberOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		// 长度为i的大于等于X的情况一共有多少种情况，treemap是有序表
		// 如果不能理解，见笔记举例情况
		ArrayList<TreeMap<Integer, Integer>> dp = new ArrayList<>();
		int len = 0;
		int cnt = 0;
		for (int num : nums) {
			// num之前的长度，num到哪个长度呢？len+1
			// 来到数字num，能生成的最大递增子序列的长度是多少呢？len+1
			len = search(dp, num);
			// cnt : 最终要去加底下的记录，才是应该填入的value
			if (len == 0) {
				// 之前的长度为0，那么num的情况数是：1+ 长度为1的大于num的情况数(这个数在下面)
				cnt = 1;
			} else {
				TreeMap<Integer, Integer> p = dp.get(len - 1);
				// 上一个长度的总共的情况数 - 大于num的情况数
				cnt = p.firstEntry().getValue() - (p.ceilingKey(num) != null ? p.get(p.ceilingKey(num)) : 0);
			}
			if (len == dp.size()) {
				// 当前长度来到新的长度，生成一个有序表
				dp.add(new TreeMap<Integer, Integer>());
				dp.get(len).put(num, cnt);
			} else {
				// 如果长度已经存在，那么num的情况数 需要 加上 大于num的情况数d
				dp.get(len).put(num, dp.get(len).firstEntry().getValue() + cnt);
			}
		}
		return dp.get(dp.size() - 1).firstEntry().getValue();
	}

	// 二分查找，返回>=num最左的位置
	// 来到当前数字num，num能生成的最大递增子序列的长度为ans+1
	public static int search(ArrayList<TreeMap<Integer, Integer>> dp, int num) {
		int l = 0, r = dp.size() - 1, m = 0;
		int ans = dp.size();
		while (l <= r) {
			m = (l + r) / 2;
			if (dp.get(m).firstKey() >= num) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

}
