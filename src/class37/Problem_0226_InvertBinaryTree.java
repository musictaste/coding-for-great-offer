package class37;

public class Problem_0226_InvertBinaryTree {

	public class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 类似镜像树
	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode left = root.left;
		// 右树反转给左树
		root.left = invertTree(root.right);
		// 左树反转给右树
		root.right = invertTree(left);
		return root;
	}

}
