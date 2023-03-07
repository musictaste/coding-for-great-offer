package class28;

import java.util.ArrayList;
import java.util.List;

public class Problem_0017_LetterCombinationsOfAPhoneNumber {

	public static char[][] phone = { 
			{ 'a', 'b', 'c' }, // 数字2    数组下标0
			{ 'd', 'e', 'f' }, // 3    1
			{ 'g', 'h', 'i' }, // 4    2
			{ 'j', 'k', 'l' }, // 5    3
			{ 'm', 'n', 'o' }, // 6    
			{ 'p', 'q', 'r', 's' }, // 7 
			{ 't', 'u', 'v' },   // 8
			{ 'w', 'x', 'y', 'z' }, // 9
	};

	// "23"
	public static List<String> letterCombinations(String digits) {
		List<String> ans = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return ans;
		}
		char[] str = digits.toCharArray();
		char[] path = new char[str.length];
		process(str, 0, path, ans);
		return ans;
	}

	// str:拨过的数字
	// index:当前的位置
	// path：用于记录之前拨过的数字
	// index=0 str[0]='2'  -> 支路查出来 -》 indexs==1 -> index==2
	public static void process(char[] str, int index, char[] path, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(path));
		} else {
			// 当前数字的支路可能性
			char[] cands = phone[str[index] - '2'];
			for (char cur : cands) {
				path[index] = cur;
				// 下一个位置，深度优先遍历
				process(str, index + 1, path, ans);
			}
		}
	}

}
