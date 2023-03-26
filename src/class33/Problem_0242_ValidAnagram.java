package class33;

public class Problem_0242_ValidAnagram {

	public static boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		// 长度一定相等，如果互为异位词，那么字符种类一样以及字符的数量也一样
		char[] str1 = s.toCharArray();
		char[] str2 = t.toCharArray();
		int[] count = new int[256];
		for (char cha : str1) {
			count[cha]++;
		}
		for (char cha : str2) {
			// 如果发生某个字符的数量小于0
			if (--count[cha] < 0) {
				return false;
			}
		}
		// 字符种类一样，某个字符的数量也一样
		return true;
	}

	public static void main(String[] args) {
		String str1 = "aabb";
		String str2 = "aacc";
		boolean anagram = isAnagram(str1, str2);
		System.out.println(anagram);
	}

}
