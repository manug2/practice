package epi.hackathon;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCh10BinaryTree {
    @Test public void should_find_height_for_root_only_tree() {
        Ch10BinaryTree root = new Ch10BinaryTree('A');
        assertEquals(0, root.height());
        assertTrue(root.isBalanced());
    }

    @Test public void should_find_height_for_root_with_one_level_children() {
        Ch10BinaryTree root = new Ch10BinaryTree('A');
        Ch10BinaryTree left = new Ch10BinaryTree('B');
        Ch10BinaryTree right = new Ch10BinaryTree('C');
        root.setLeft(left);
        root.setRight(right);
        assertEquals(1, root.height());
        assertTrue(root.isBalanced());
    }

    @Test public void should_find_height_for_one_level_child() {
        Ch10BinaryTree root = new Ch10BinaryTree('A');
        Ch10BinaryTree left = new Ch10BinaryTree('B');
        Ch10BinaryTree right = new Ch10BinaryTree('C');
        root.setLeft(left);
        root.setRight(right);
        assertEquals(0, left.height());
        assertEquals(0, right.height());
        assertTrue(left.isBalanced());
        assertTrue(right.isBalanced());
    }

    @Test public void should_find_height_for_root_with_2_level_on_left() {
        Ch10BinaryTree root = new Ch10BinaryTree('A');
        Ch10BinaryTree left = new Ch10BinaryTree('B');
        Ch10BinaryTree right = new Ch10BinaryTree('C');
        root.setLeft(left);
        root.setRight(right);
        left.setLeft(new Ch10BinaryTree('D'));
        assertEquals(2, root.height());
        assertTrue(root.isBalanced());
    }

    @Test public void should_find_height_for_root_with_2_level_on_right() {
        Ch10BinaryTree root = new Ch10BinaryTree('A');
        Ch10BinaryTree left = new Ch10BinaryTree('B');
        Ch10BinaryTree right = new Ch10BinaryTree('C');
        root.setLeft(left);
        root.setRight(right);
        right.setRight(new Ch10BinaryTree('D'));
        assertEquals(2, root.height());
        assertTrue(root.isBalanced());
    }

    @Test public void should_report_unbalanced_when_root_with_2_extra_level_on_right() {
        Ch10BinaryTree root = new Ch10BinaryTree('A');
        Ch10BinaryTree left = new Ch10BinaryTree('B');
        Ch10BinaryTree right = new Ch10BinaryTree('C');
        root.setLeft(left);
        root.setRight(right);

        Ch10BinaryTree rr = new Ch10BinaryTree('E');
        right.setRight(rr);
        rr.setRight(new Ch10BinaryTree('D'));

        assertEquals(3, root.height());
        assertFalse(root.isBalanced());
    }

    @Test public void should_report_unbalanced_when_root_with_2_extra_level_on_left() {
        Ch10BinaryTree root = new Ch10BinaryTree('A');
        Ch10BinaryTree left = new Ch10BinaryTree('B');
        Ch10BinaryTree right = new Ch10BinaryTree('C');
        root.setLeft(left);
        root.setRight(right);

        Ch10BinaryTree ll = new Ch10BinaryTree('E');
        left.setLeft(ll);
        ll.setLeft(new Ch10BinaryTree('D'));

        assertEquals(3, root.height());
        assertFalse(root.isBalanced());
    }

    @Test public void should_report_unbalanced_when_root_with_2_extra_balanced_levels_on_left() {
        Ch10BinaryTree root = new Ch10BinaryTree('A');
        Ch10BinaryTree left = new Ch10BinaryTree('B');
        Ch10BinaryTree right = new Ch10BinaryTree('C');
        root.setLeft(left);
        root.setRight(right);

        Ch10BinaryTree ll = new Ch10BinaryTree('E');
        left.setLeft(ll);
        Ch10BinaryTree lr = new Ch10BinaryTree('F');
        left.setRight(lr);

        ll.setLeft(new Ch10BinaryTree('D'));
        ll.setRight(new Ch10BinaryTree('H'));

        lr.setLeft(new Ch10BinaryTree('I'));
        lr.setRight(new Ch10BinaryTree('J'));

        assertEquals(3, root.height());
        assertFalse(root.isBalanced());
    }

}
