package class34;

// 有关这个游戏更有意思、更完整的内容：
// https://www.bilibili.com/video/BV1rJ411n7ri
// 也推荐这个up主
public class Problem_0289_GameOfLife {

	public static void gameOfLife(int[][] board) {
		int N = board.length;
		int M = board[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int neighbors = neighbors(board, i, j);
				// 什么情况下这个节点可以存活？
				// 情况1：当周围有3个存活节点，当前位置不管死活，更新后必是存活节点
				// 情况2：当前节点是存活节点，周围有两个存活节点
				if (neighbors == 3 || (board[i][j] == 1 && neighbors == 2)) {
					// 将当前节点的第二位标记为1
					board[i][j] |= 2;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 将节点的第二位变成第1位；也就是设置更新后的信息
				board[i][j] >>= 1;
			}
		}
	}

	// b[i][j] 这个位置的数，周围有几个1
	public static int neighbors(int[][] b, int i, int j) {
		return f(b, i - 1, j - 1)
				+ f(b, i - 1, j)
				+ f(b, i - 1, j + 1)
				+ f(b, i, j - 1)
				+ f(b, i, j + 1)
				+ f(b, i + 1, j - 1)
				+ f(b, i + 1, j)
				+ f(b, i + 1, j + 1);
	}

	// b[i][j] 上面有1，就返回1，上面不是1，就返回0
	public static int f(int[][] b, int i, int j) {
		return (i >= 0 && i < b.length && j >= 0 && j < b[0].length && (b[i][j] & 1) == 1) ? 1 : 0;
	}

}
