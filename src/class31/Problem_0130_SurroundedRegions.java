package class31;

// 体系学习班16节，岛问题
public class Problem_0130_SurroundedRegions {

//	// m -> 二维数组， 不是0就是1
//	// 感染函数
//	public static void infect(int[][] m, int i, int j) {
//	   // 不越界 + 当前位置不是1
//		if (i < 0 || i == m.length || j < 0 || j == m[0].length || m[i][j] != 1) {
//			return;
//		}
//		// m[i][j] == 1  当前位置是1，上下左右进行感染
//		m[i][j] = 2;
//		infect(m, i - 1, j);
//		infect(m, i + 1, j);
//		infect(m, i, j - 1);
//		infect(m, i, j + 1);
//	}

	public static void solve1(char[][] board) {
		boolean[] ans = new boolean[1];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'O') {
					ans[0] = true;
					can(board, i, j, ans);
					board[i][j] = ans[0] ? 'T' : 'F';
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				char can = board[i][j];
				if (can == 'T' || can == 'F') {
					board[i][j] = '.';
					change(board, i, j, can);
				}
			}
		}

	}

	public static void can(char[][] board, int i, int j, boolean[] ans) {
		if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
			ans[0] = false;
			return;
		}
		if (board[i][j] == 'O') {
			board[i][j] = '.';
			can(board, i - 1, j, ans);
			can(board, i + 1, j, ans);
			can(board, i, j - 1, ans);
			can(board, i, j + 1, ans);
		}
	}

	public static void change(char[][] board, int i, int j, char can) {
		if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
			return;
		}
		if (board[i][j] == '.') {
			board[i][j] = can == 'T' ? 'X' : 'O';
			change(board, i - 1, j, can);
			change(board, i + 1, j, can);
			change(board, i, j - 1, can);
			change(board, i, j + 1, can);
		}
	}

	// 从边界开始感染的方法，比第一种方法更好
	// 看这个方法
	public static void solve2(char[][] board) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return;
		}
		int N = board.length;
		int M = board[0].length;
		for (int j = 0; j < M; j++) {
			// 第0行
			if (board[0][j] == 'O') {
				free(board, 0, j);
			}
			// 第N-1行
			if (board[N - 1][j] == 'O') {
				free(board, N - 1, j);
			}
		}
		for (int i = 1; i < N - 1; i++) {
			// 第0列
			if (board[i][0] == 'O') {
				free(board, i, 0);
			}
			// 第M-1列
			if (board[i][M - 1] == 'O') {
				free(board, i, M - 1);
			}
		}
		// 感染为Y的改成0；没有感染的O改成X
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'F') {
					board[i][j] = 'O';
				}
			}
		}
	}

	// 感染过程，将连成一片的0改成Y
	public static void free(char[][] board, int i, int j) {
		if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != 'O') {
			return;
		}
		board[i][j] = 'F';
		free(board, i + 1, j);
		free(board, i - 1, j);
		free(board, i, j + 1);
		free(board, i, j - 1);
	}

}
