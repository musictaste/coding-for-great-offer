package class30;

public class Problem_0079_WordSearch {

	public static boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (f(board, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	// 目前到达了b[i][j]，word[k....]
	// 从b[i][j]出发，能不能搞定word[k....] true false
	public static boolean f(char[][] b, int i, int j, char[] w, int k) {
		// word已经走完了，说明走成了
		if (k == w.length) {
			return true;
		}
		// word[k.....] 有字符
		// 如果(i,j)越界，返回false
		if (i < 0 || i == b.length || j < 0 || j == b[0].length) {
			return false;
		}
		if (b[i][j] != w[k]) {
			return false;
		}
		char tmp = b[i][j];
		// 深度优先遍历，典型的制造现场，恢复现场
		// 走过的路标记为0，防止走回头路
		// 不要用‘0’，而要用0 aciic码
		b[i][j] = 0;
		boolean ans = f(b, i - 1, j, w, k + 1) 
				|| f(b, i + 1, j, w, k + 1) 
				|| f(b, i, j - 1, w, k + 1)
				|| f(b, i, j + 1, w, k + 1);
		b[i][j] = tmp;
		return ans;
	}

}
