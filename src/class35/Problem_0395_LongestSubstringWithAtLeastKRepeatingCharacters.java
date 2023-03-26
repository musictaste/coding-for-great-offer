package class35;

public class Problem_0395_LongestSubstringWithAtLeastKRepeatingCharacters {

	public static int longestSubstring1(String s, int k) {
		char[] str = s.toCharArray();
		int N = str.length;
		int max = 0;
		for (int i = 0; i < N; i++) {
			int[] count = new int[256];
			int collect = 0;
			int satisfy = 0;
			for (int j = i; j < N; j++) {
				if (count[str[j]] == 0) {
					collect++;
				}
				if (count[str[j]] == k - 1) {
					satisfy++;
				}
				count[str[j]]++;
				if (collect == satisfy) {
					max = Math.max(max, j - i + 1);
				}
			}
		}
		return max;
	}

	// 课上讲的
	// 因为L和R都不回退，所以时间复杂度O(N)
	public static int longestSubstring2(String s, int k) {
		char[] str = s.toCharArray();
		int N = str.length;
		int max = 0;
		// 只有i个字符出现次数都大于等于k次，滑动窗口具有单调性
		for (int require = 1; require <= 26; require++) {
			// a~z 出现次数
			// 词频统计，可以帮助我们知道当前字符串中出现了几种字符，以及满足大于等于k次的字符的数量
			// 所以count和collect和satisfy是联动的
			int[] count = new int[26];
			// 目前窗口内收集了几种字符了，词频统计
			int collect = 0;
			// 目前窗口内出现次数>=k次的字符，满足了几种
			int satisfy = 0;
			// 窗口右边界
			int R = -1;
			// L要尝试每一个窗口的最左位置
			for (int L = 0; L < N; L++) {
				// [L..R] R+1
				// collect == require && count[str[R + 1] - 'a'] == 0
				// 收集的字符 == 满足的 && 窗口下个元素出现的次数为0，也就是当下一个元素进来的时候，就不满足了，R停；否则R就往右扩
				while (R + 1 < N && !(collect == require && count[str[R + 1] - 'a'] == 0)) {
					// R往右扩；收集的字符++，满足的字符++
					R++;
					if (count[str[R] - 'a'] == 0) {
						collect++;
					}
					if (count[str[R] - 'a'] == k - 1) {
						satisfy++;
					}
					count[str[R] - 'a']++;
				}
				// [L...R]范围满足要求，子串长度=R-L+1
				if (satisfy == require) {
					max = Math.max(max, R - L + 1);
				}
				// L++ 左窗口往右扩，收集的字符--，满足的字符--
				if (count[str[L] - 'a'] == 1) {
					collect--;
				}
				if (count[str[L] - 'a'] == k) {
					satisfy--;
				}
				count[str[L] - 'a']--;
			}
		}
		return max;
	}

	// 会超时，但是思路的确是正确的
	public static int longestSubstring3(String s, int k) {
		return process(s.toCharArray(), 0, s.length() - 1, k);
	}

	public static int process(char[] str, int L, int R, int k) {
		if (L > R) {
			return 0;
		}
		int[] counts = new int[26];
		for (int i = L; i <= R; i++) {
			counts[str[i] - 'a']++;
		}
		char few = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 26; i++) {
			if (counts[i] != 0 && min > counts[i]) {
				few = (char) (i + 'a');
				min = counts[i];
			}
		}
		if (min >= k) {
			return R - L + 1;
		}
		int pre = 0;
		int max = Integer.MIN_VALUE;
		for (int i = L; i <= R; i++) {
			if (str[i] == few) {
				max = Math.max(max, process(str, pre, i - 1, k));
				pre = i + 1;
			}
		}
		if (pre != R + 1) {
			max = Math.max(max, process(str, pre, R, k));
		}
		return max;
	}

}
