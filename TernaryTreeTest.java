import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for the TernaryTree class
 * @author Tyler McCreary
 */
public class TernaryTreeTest {
	
	@Test
	public void testInsert() {
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 5, 4, 9, 5, 7, 2, 2);
		String s = tree.toString();
		System.out.println(s);
		assertTrue(s.equals("5422597"));
		tree.delete(9);
		s = tree.toString();
		assertTrue(s.equals("542257"));
		insertMultiple(tree, 8, 7, 20, 20, 14, 12, 17, 30, 25, 30, 32, 14, 20, 20, 20);
		s = tree.toString();
		assertTrue(s.equals("5422577820141214172020202030253032"));
	}
	
	@Test
	public void testEmpty() {
		TernaryTree tree = new TernaryTree();
		boolean thrown1 = false;
		boolean thrown2 = false;
		try {
			tree.delete(0);			
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The tree is empty. There is nothing to delete."));
			thrown1 = true;
		}
		tree.insert(7);
		String s = tree.toString();
		assertTrue(s.equals("7"));
		tree.delete(7);
		try {
			tree.delete(7);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The tree is empty. There is nothing to delete."));
			thrown2 = true;
		}
		assertTrue(thrown1 && thrown2);		
	}
	
	@Test
	public void testNotInList() {
		int notInList = 6;
		boolean thrown = false;
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 5, 4, 9, 5, 7, 2, 2);
		try {
			tree.delete(notInList);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals(notInList + " is not in the list."));
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testDuplicates() {
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 2, 2, 1, 3, 2, 1, 3);
		String s = tree.toString();
		assertTrue(s.equals("2112233"));
		deleteMultiple(tree, 2, 1, 2);
		s = tree.toString();
		assertTrue(s.equals("2133"));
	}
	
	@Test
	public void testRootReplacement() {
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 20, 20, 10, 30, 5, 10, 15, 30, 25, 40);
		String s = tree.toString();
		assertTrue(s.equals("2010510152030253040"));
		tree.delete(20);
		s = tree.toString();
		assertTrue(s.equals("20105101530253040"));
		tree.delete(20);
		s = tree.toString();
		assertTrue(s.equals("151051030253040"));
		deleteMultiple(tree, 10, 10, 5, 15);
		s = tree.toString();
		assertTrue(s.equals("30253040"));
	}
	
	@Test
	public void testReplaceWithCenter() {
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 5, 4, 9, 5, 7, 9, 2, 2, 10);
		tree.delete(9);
		String s = tree.toString();
		assertTrue(s.equals("542259710"));
	}

	@Test
	public void testReplaceWithLeft() {
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 5, 4, 9, 5, 7, 2, 2, 10);
		tree.delete(9);
		String s = tree.toString();
		assertTrue(s.equals("54225710"));
	}
	
	@Test
	public void testReplaceWithRight() {
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 5, 4, 9, 5, 2, 2, 10, 12);
		tree.delete(9);
		String s = tree.toString();
		assertTrue(s.equals("542251012"));
	}
	
	@Test
	public void testRightMost() {
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 20, 10, 5, 10, 15, 12, 15, 18, 30, 25, 30, 40);
		String s = tree.toString();
		assertTrue(s.equals("20105101512151830253040"));
		tree.delete(20);
		s = tree.toString();
		assertTrue(s.equals("181051015121530253040"));
		tree.delete(18);
		s = tree.toString();
		assertTrue(s.equals("1510510121530253040"));
		tree.delete(15);
		s = tree.toString();
		assertTrue(s.equals("15105101230253040"));
		tree.delete(15);
		s = tree.toString();
		assertTrue(s.equals("121051030253040"));
		tree.delete(12);
		s = tree.toString();
		assertTrue(s.equals("1051030253040"));
	}
	
	@Test
	public void testDeleteLeaf() {
		TernaryTree tree = new TernaryTree();
		insertMultiple(tree, 20, 10, 5, 10, 15, 12, 15, 18, 30, 25, 30, 40);
		String s = tree.toString();
		assertTrue(s.equals("20105101512151830253040"));
		tree.delete(40);
		s = tree.toString();
		assertTrue(s.equals("201051015121518302530"));
	}
	
	
	private void insertMultiple(TernaryTree tree, Integer...integers) {
		for(int i = 0; i < integers.length; i++) {
			tree.insert(integers[i]);
		}
	}
	
	private void deleteMultiple(TernaryTree tree, Integer...integers) {
		for(int i = 0; i < integers.length; i++) {
			tree.delete(integers[i]);
		}
	}
}
