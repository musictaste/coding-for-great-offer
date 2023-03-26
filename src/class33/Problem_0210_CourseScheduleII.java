package class33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_0210_CourseScheduleII {

	public static class Node {
		public int name;
		public int in;
		public ArrayList<Node> nexts;

		public Node(int n) {
			name = n;
			in = 0;
			nexts = new ArrayList<>();
		}
	}

	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		// 生成课程编号数组,注意给定的数据有可能某些课程是不存在的，那么需要将不存在的课程先打印
		// [[3,0],[0,1]]
		int[] ans = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			ans[i] = i;
		}
		if (prerequisites == null || prerequisites.length == 0) {
			return ans;
		}
		// 课程编号，课程节点信息
		HashMap<Integer, Node> nodes = new HashMap<>();
		for (int[] arr : prerequisites) {
			int to = arr[0];
			int from = arr[1];
			if (!nodes.containsKey(to)) {
				nodes.put(to, new Node(to));
			}
			if (!nodes.containsKey(from)) {
				nodes.put(from, new Node(from));
			}
			Node t = nodes.get(to);
			Node f = nodes.get(from);
			f.nexts.add(t);
			t.in++;
		}
		// 利用已经生成好的课程编号，
		int index = 0;
		Queue<Node> zeroInQueue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (!nodes.containsKey(i)) {
				ans[index++] = i;
			} else {
				if (nodes.get(i).in == 0) {
					zeroInQueue.add(nodes.get(i));
				}
			}
		}
		int needPrerequisiteNums = nodes.size();
		int count = 0;
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			ans[index++] = cur.name;
			count++;
			for (Node next : cur.nexts) {
				if (--next.in == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		return count == needPrerequisiteNums ? ans : new int[0];
	}

	public static void main(String[] args) {
		int numCourses=4;
		int[][] prerequisites = {{3,0},{0,1}};
		int[] order = findOrder(numCourses, prerequisites);
		for (int i = 0; i < order.length; i++) {
			System.out.print(order[i]);
		}
	}
}
