package class35;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Problem_0347_TopKFrequentElements {

	public static class Node {
		public int num;
		public int count;

		public Node(int k) {
			num = k;
			count = 1;
		}
	}

	public static int[] topKFrequent(int[] nums, int k) {
		// 词频统计
		HashMap<Integer, Node> map = new HashMap<>();
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, new Node(num));
			} else {
				map.get(num).count++;
			}
		}
		// 小根堆，门槛堆
		PriorityQueue<Node> heap = new PriorityQueue<>((o1,o2) -> o1.count - o2.count);
		for (Node node : map.values()) {
			// 门槛堆没满 || 堆满了，当前元素出现次数 大于 堆顶元素出现次数
			if (heap.size() < k || (heap.size() == k && node.count > heap.peek().count)) {
				heap.add(node);
			}
			// 堆满了，添加元素，要弹出堆顶元素
			if (heap.size() > k) {
				heap.poll();
			}
		}
		int[] ans = new int[k];
		int index = 0;
		while (!heap.isEmpty()) {
			ans[index++] = heap.poll().num;
		}
		return ans;
	}

}
