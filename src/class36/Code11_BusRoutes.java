package class36;

import java.util.ArrayList;
import java.util.HashMap;

// 来自三七互娱
// Leetcode原题 : https://leetcode.com/problems/bus-routes/
public class Code11_BusRoutes {

	// 0 : [1,3,7,0]
	// 1 : [7,9,6,2]
	// ....
	// 返回：返回换乘几次+1 -> 返回一共坐了多少条线的公交。
	public static int numBusesToDestination(int[][] routes, int source, int target) {
		if (source == target) {
			return 0;
		}
		int n = routes.length;
		// key : 车站
		// value : list -> 该车站拥有哪些线路！
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < routes[i].length; j++) {
				if (!map.containsKey(routes[i][j])) {
					map.put(routes[i][j], new ArrayList<>());
				}
				map.get(routes[i][j]).add(i);
			}
		}
		ArrayList<Integer> queue = new ArrayList<>();
		boolean[] set = new boolean[n];
		// 从出发站，得到出发站有几条线路；入队列的线路标记为已经考查过
		for (int route : map.get(source)) {
			queue.add(route);
			set[route] = true;
		}
		int len = 1;
		// 宽度优先遍历
		while (!queue.isEmpty()) {
			// 下一层的公交线路，就是通过上一层的公交线路能找到哪些新的公交线路
			ArrayList<Integer> nextLevel = new ArrayList<>();
			// 遍历上一层，收集下一层
			for (int route : queue) {
				// 公交路线
				int[] bus = routes[route];
				// 公交站点
				for (int station : bus) {
					// 公交站=目标站
					if (station == target) {
						return len;
					}
					// 通过公交站得到新的公交路线
					for (int nextRoute : map.get(station)) {
						// 公交站所有在公交路线，如果没有考查过，进队列，
						if (!set[nextRoute]) {
							nextLevel.add(nextRoute);
							set[nextRoute] = true;
						}
					}
				}
			}
			// 收集好的下一层，成为新的队列
			queue = nextLevel;
			len++;
		}
		return -1;
	}

}
