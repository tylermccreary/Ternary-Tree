
/**
 * An implementation of a ternary tree (tri-nary)
 * @author Tyler McCreary
 */
public class TernaryTree {
	public static final String EMPTY_TREE = "The tree is empty.";
	public static final String NOT_IN_LIST = " is not in the list.";
	public static final String EMPTY_DELETE = "The tree is empty. There is nothing to delete.";
	
	private Node root;

	/**
	 * TernaryTree Constructor
	 */
	public TernaryTree() {
		root = null;
	}
	
	/**
	 * Insert an integer into the TernaryTree
	 * @param dataValue the integer being inserted
	 */
	public void insert(int dataValue) {
		Node currentNode = root;

		if (root == null) {
			root = new Node(dataValue);
		} else {
			while (currentNode != null) {
				if (dataValue < currentNode.data) {
					if (currentNode.leftChild == null) {
						currentNode.leftChild = new Node(dataValue);
						currentNode = null;
					} else {
						currentNode = currentNode.leftChild;
					}
				} else if (dataValue == currentNode.data) {
					if (currentNode.centerChild == null) {
						currentNode.centerChild = new Node(dataValue);
						currentNode = null;
					} else {
						currentNode = currentNode.centerChild;
					}
				} else {
					if (currentNode.rightChild == null) {
						currentNode.rightChild = new Node(dataValue);
						currentNode = null;
					} else {
						currentNode = currentNode.rightChild;
					}
				}
			}
		}
	}
	
	/**
	 * Delete an integer from the TernaryTree. When the correct node to be deleted is found,
	 * the method will search for a replacement node by checking for a centerChild, followed
	 * by the highest lesser value (right-most descendant of leftChild), followed by checking
	 * for a rightChild.
	 * @param dataValue the integer being deleted
	 * @throws IllegalArgumentException
	 */
	public void delete(int dataValue) throws IllegalArgumentException {
		boolean deleted = false;
		Node currentNode = root;
		Node parentNode = null;
		
		if (empty()) {
			throw new IllegalArgumentException(EMPTY_DELETE);
		}
		while (!deleted) {
			if (dataValue < currentNode.data) {
				if (currentNode.leftChild != null) {
					parentNode = currentNode;
					currentNode = currentNode.leftChild;
				} else {
					throw new IllegalArgumentException(dataValue + NOT_IN_LIST);
				}
			} else if (dataValue > currentNode.data) {
				if (currentNode.rightChild != null) {
					parentNode = currentNode;
					currentNode = currentNode.rightChild;
				} else {
					throw new IllegalArgumentException(dataValue + NOT_IN_LIST);
				}
			} else {
				if (currentNode.centerChild != null) {
					Node replaceNode = currentNode.centerChild;
					replaceNode.leftChild = currentNode.leftChild;
					replaceNode.rightChild = currentNode.rightChild;
					assignParentReferences(parentNode, currentNode, replaceNode);
				} else if (currentNode.leftChild != null) {
					rightMost(currentNode, currentNode.leftChild, parentNode, currentNode);
				} else if (currentNode.rightChild != null) {
					assignParentReferences(parentNode, currentNode, currentNode.rightChild);
				} else {
					assignParentReferences(parentNode, currentNode, null);
				}
				deleted = true;
			}
		}
	}
	
	/**
	 * Helper method for delete. Delete will call this function if the Node being deleted
	 * is being replaced by a Node with a lesser data value (to the left). The Node will be
	 * replaced by its right-most, lesser descendant.
	 * @param nodeToDelete the node to be deleted
	 * @param replacement the node that may replace the node to be deleted
	 * @param parentNode the parent of the node being replaced
	 * @param parentOfReplacement the parent of the node doing the replacing
	 */
	private void rightMost(Node nodeToDelete, Node replacement, Node parentNode, Node parentOfReplacement) {
		if (replacement.rightChild != null) {
			rightMost(nodeToDelete, replacement.rightChild, parentNode, replacement);
		} else {
			if (parentOfReplacement != nodeToDelete) {
				parentOfReplacement.rightChild = replacement.leftChild;
				replacement.leftChild = nodeToDelete.leftChild;
			}
			replacement.rightChild = nodeToDelete.rightChild;
			assignParentReferences(parentNode, nodeToDelete, replacement);
		}
	}
	
	/**
	 * Helper method of Delete that will reassign parent references
	 * @param parentNode the parent of the node being deleted
	 * @param nodeToDelete the node being deleted
	 * @param replacement the replacement node
	 */
	private void assignParentReferences(Node parentNode, Node nodeToDelete, Node replacement){
		if (parentNode != null){
			if (parentNode.leftChild == nodeToDelete) {
				parentNode.leftChild = replacement;
			} else if (parentNode.rightChild == nodeToDelete) {
				parentNode.rightChild = replacement;
			} else {
				parentNode.centerChild = replacement;
			}
		} else {
			root = replacement;
		}
	}
	
	/**
	 * Checks if the tree is empty or not
	 * @return whether or not the tree is empty
	 */
	public boolean empty() {
		if (root == null) {
			return true;
		}
			return false;
	}

	@Override
	public String toString() {
		String result = toString(root);
		return result;
	}
	
	/**
	 * Creates a string representation of the tree starting at the root and doing a
	 * depth-first-search, pre-order search (Root, Left, Center, Right)
	 * @param currentNode the node being examined
	 * @return the tree as a string
	 */
	private String toString(Node currentNode) {
		String result = "";
		if (empty()) {
			System.out.print(EMPTY_TREE);
		} else {
			result = Integer.toString(currentNode.data);
			if (currentNode.leftChild != null || currentNode.centerChild != null || currentNode.rightChild != null) {
				if (currentNode.leftChild != null) {
					result = result + toString(currentNode.leftChild);
				}
				if (currentNode.centerChild != null) {
					result = result + toString(currentNode.centerChild);
				}
				if (currentNode.rightChild != null) {
					result = result + toString(currentNode.rightChild);
				}
			}
		}
		return result;
	}
		
	/**
	 * A Node in a TernaryTree
	 * @author Tyler McCreary
	 */
	private class Node {
		int data;
		Node leftChild;
		Node rightChild;
		Node centerChild;
		
		/**
		 * Node Constructor
		 * @param dataValue the value of that given Node
		 */
		public Node(int dataValue) {
			data = dataValue;
			leftChild = null;
			rightChild = null;
			centerChild = null;
		}
	}
}
