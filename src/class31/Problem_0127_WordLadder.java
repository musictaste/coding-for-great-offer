package class31;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 高频题
// 时间复杂度O(K^2 * 25 * N) N是list的个数
// 单个单词的复杂度：O(K *25 *K) ,一个单词的长度为K，一个单词产生的邻居(可能性)为K*25,产生的每一个邻居(可能性)在哈希表中比对的复杂度为O(K)[注：因为每一个可能性的长度都为K，每个字母都要比对]
// 哈希表查询的时间复杂度为O(1),指的样本本身不大的的情况，例如int类型、内存地址、对象的hashcode
// 如果哈希表中存放的是长度为K的字符串，那么哈希表查询的时间复杂度为O(K)
public class Problem_0127_WordLadder {

	// start，出发的单词
	// to, 目标单词
	// list, 列表
	// to 一定属于list
	// start未必属于list
	// 返回变幻的最短路径长度
	public static int ladderLength1(String start, String to, List<String> list) {
		list.add(start);

		// key : 列表中的单词，每一个单词都会有记录！
		// value : key这个单词，有哪些邻居！
		HashMap<String, ArrayList<String>> nexts = getNexts(list);
		// abc  出发     abc  -> abc  0
		// 
		// bbc  1
		HashMap<String, Integer> distanceMap = new HashMap<>();
		distanceMap.put(start, 1);
		// 宽度优先遍历，避免重复进队列
		HashSet<String> set = new HashSet<>();
		set.add(start);
		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			Integer distance = distanceMap.get(cur);
			for (String next : nexts.get(cur)) {
				if (next.equals(to)) {
					return distance + 1;
				}
				if (!set.contains(next)) {
					set.add(next);
					queue.add(next);
					distanceMap.put(next, distance + 1);
				}
			}

		}
		return 0;
	}

	public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
		HashSet<String> dict = new HashSet<>(words);
		HashMap<String, ArrayList<String>> nexts = new HashMap<>();
		for (int i = 0; i < words.size(); i++) {
			nexts.put(words.get(i), getNext(words.get(i), dict));
		}
		return nexts;
	}

	// 应该根据具体数据状况决定用什么来找邻居
	// 1)如果字符串长度比较短，字符串数量比较多，以下方法适合
	// 2)如果字符串长度比较长，字符串数量比较少，以下方法不适合
	public static ArrayList<String> getNext(String word, HashSet<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		char[] chs = word.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			for (char cur = 'a'; cur <= 'z'; cur++) {
				if (chs[i] != cur) {
					char tmp = chs[i];
					chs[i] = cur;
					if (dict.contains(String.valueOf(chs))) {
						res.add(String.valueOf(chs));
					}
					chs[i] = tmp;
				}
			}
		}
		return res;
	}

	// 优化方法：从start和end,从两头向中间逼近，如果碰上就说明有效
	// start产生的nextset邻居，和end产生的nextset邻居，谁的数量小谁就变成startset
	public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
		HashSet<String> dict = new HashSet<>(wordList);
		if (!dict.contains(endWord)) {
			return 0;
		}
		HashSet<String> startSet = new HashSet<>();
		HashSet<String> endSet = new HashSet<>();
		HashSet<String> visit = new HashSet<>();
		startSet.add(beginWord);
		endSet.add(endWord);
		for (int len = 2; !startSet.isEmpty(); len++) {
			// startSet是较小的，endSet是较大的
			HashSet<String> nextSet = new HashSet<>();
			for (String w : startSet) {
				// w -> a(nextSet)
				// a b c
				// 0 
				//   1
				//     2
				for (int j = 0; j < w.length(); j++) {
					char[] ch = w.toCharArray();
					for (char c = 'a'; c <= 'z'; c++) {
						if (c != w.charAt(j)) {
							ch[j] = c;
							String next = String.valueOf(ch);
							if (endSet.contains(next)) {
								return len;
							}
							if (dict.contains(next) && !visit.contains(next)) {
								nextSet.add(next);
								visit.add(next);
							}
						}
					}
				}
			}
			// startSet(小) -> nextSet(某个大小)   和 endSet大小来比
			startSet = (nextSet.size() < endSet.size()) ? nextSet : endSet;
			endSet = (startSet == nextSet) ? endSet : nextSet;
		}
		return 0;
	}

}
