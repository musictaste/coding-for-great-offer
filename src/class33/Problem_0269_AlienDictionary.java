package class33;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_0269_AlienDictionary {

	public static String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}
		int N = words.length;
		// 入度数组，初始化，所有字符的入度为0
		HashMap<Character, Integer> indegree = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (char c : words[i].toCharArray()) {
				indegree.put(c, 0);
			}
		}
		// <当前点，相邻节点>
		HashMap<Character, HashSet<Character>> graph = new HashMap<>();
		for (int i = 0; i < N - 1; i++) {
			char[] cur = words[i].toCharArray();
			char[] nex = words[i + 1].toCharArray();
			// 找到短的字符串，共同长度，前缀，字符不同，生成边
			int len = Math.min(cur.length, nex.length);
			int j = 0;
			for (; j < len; j++) {
				// 字符不同，生成图和入度信息，然后遍历下一条数据
				if (cur[j] != nex[j]) {
					if (!graph.containsKey(cur[j])) {
						graph.put(cur[j], new HashSet<>());
					}
					// 生成图以及入度数组
					if (!graph.get(cur[j]).contains(nex[j])) {
						graph.get(cur[j]).add(nex[j]);
						indegree.put(nex[j], indegree.get(nex[j]) + 1);
					}
					break;
				}
			}
			// 一个字符是另一个字符的子串，无法生成边，所以直接返回没有字典序
			if (j < cur.length && j == nex.length) {
				return "";
			}
		}
		StringBuilder ans = new StringBuilder();
		Queue<Character> q = new LinkedList<>();
		// 入度为0的点进队列
		for (Character key : indegree.keySet()) {
			if (indegree.get(key) == 0) {
				q.offer(key);
			}
		}
		while (!q.isEmpty()) {
			char cur = q.poll();
			ans.append(cur);
			// 入度为0的点出队列，消除影响
			if (graph.containsKey(cur)) {
				for (char next : graph.get(cur)) {
					indegree.put(next, indegree.get(next) - 1);
					if (indegree.get(next) == 0) {
						q.offer(next);
					}
				}
			}
		}
		// 图的拓扑排序的节点个数是否等于节点个数
		return ans.length() == indegree.size() ? ans.toString() : "";
	}

	public static void main(String[] args) {
//		String[] words ={"wrt","wrf","er","ett","rftt"};
		String[] words ={"abc","ab"};
		String s = alienOrder(words);
//		System.out.println(s.equals("wertf"));
		System.out.println(s.equals(""));
	}
}
