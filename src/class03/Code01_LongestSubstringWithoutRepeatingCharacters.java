package class03;

// 本题测试链接 : https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class Code01_LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		//map[i] = i这个ascii码的字符，上次出现的位置
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		// 别忘了赋默认值
		map[str[0]] = 0;
		int N = str.length;
		int ans = 1;
		// pre,上一个位置，向左推了多长
		int pre = 1;
		for (int i = 1; i < N; i++) {
			pre = Math.min(i - map[str[i]], pre + 1);
			ans = Math.max(ans, pre);
			map[str[i]] = i;
		}
		return ans;
	}

}
