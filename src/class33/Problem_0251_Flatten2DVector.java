package class33;

public class Problem_0251_Flatten2DVector {

	public static class Vector2D {
		private int[][] matrix;
		// row和col是当前游标的位置
		private int row;
		private int col;
		// curUse当前数有没有被使用过
		private boolean curUse;

		public Vector2D(int[][] v) {
			matrix = v;
			// 游标刚开始的位置是0行-1列，并且使用过，接着调用hasNext
			row = 0;
			col = -1;
			curUse = true;
			hasNext();
		}

		public int next() {
			int ans = matrix[row][col];
			curUse = true;
			hasNext();
			return ans;
		}

		public boolean hasNext() {
			// 行来到最后一行
			if (row == matrix.length) {
				return false;
			}
			if (!curUse) {
				return true;
			}
			// (row，col)用过了
			// 如果列还有，列++
			// 如果列没有了，行++，但是需要注意下一行有可能是空数据，所以需要判断下一行是否为空数据
			if (col < matrix[row].length - 1) {
				col++;
			} else {
				col = 0;
				do {
					row++;
				} while (row < matrix.length && matrix[row].length == 0);
			}
			// 新的(row，col)
			if (row != matrix.length) {
				curUse = false;
				return true;
			} else {
				return false;
			}
		}

	}

}
