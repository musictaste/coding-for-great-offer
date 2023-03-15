package class30;

import java.util.ArrayList;
import java.util.List;

public class Problem_0118_PascalTriangle {

	// 杨辉三角
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> ans = new ArrayList<>();
		// 先生成每一层的list，并且第一个元素放1
		for (int i = 0; i < numRows; i++) {
			ans.add(new ArrayList<>());
			ans.get(i).add(1);
		}
		// 下标从0开始，这里从1开始，因为第一行只有一个1，上面已经生成好了
		for (int i = 1; i < numRows; i++) {
			// 当前层不超过上一层的范围，上一层i-1位置 + i位置
			for (int j = 1; j < i; j++) {
				ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
			}
			// 超过上一层范围，直接补1
			ans.get(i).add(1);
		}
		return ans;
	}

}
