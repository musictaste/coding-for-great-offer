package class30;

public class Problem_0116_PopulatingNextRightPointersInEachNode {

	// 不要提交这个类
	public static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;
	}

	// 提交下面的代码
	public static Node connect(Node root) {
		if (root == null) {
			return root;
		}
		MyQueue queue = new MyQueue();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 第一个弹出的节点
			// 每遍历一层，pre都先是null
			Node pre = null;
			int size = queue.size;
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
				// 如果pre为null，当前节点为pre；如果pre不为空，则将当前节点放到pre节点后面
				if (pre != null) {
					pre.next = cur;
				}
				pre = cur;
			}
		}
		return root;
	}

	public static class MyQueue {
		public Node head;
		public Node tail;
		public int size;

		public MyQueue() {
			head = null;
			tail = null;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		// 从尾部加节点
		public void offer(Node cur) {
			size++;
			if (head == null) {
				// 没有头结点，当前节点为头尾节点
				head = cur;
				tail = cur;
			} else {
				// 如果有头结点，在尾结点加节点
				tail.next = cur;
				tail = cur;
			}
		}

		// 从头部弹出节点
		public Node poll() {
			size--;
			Node ans = head;
			head = head.next;
			// 弹出的节点跟队列断连
			ans.next = null;
			return ans;
		}

	}

}
