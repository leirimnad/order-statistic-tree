import ua.leirimnad.RedBlackTree;

public class RedBlackTest {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(4);
        tree.insert(6);
        tree.insert(14);
        tree.insert(5);
        tree.insert(6);
        tree.insert(4);
        tree.insert(5);
        tree.insert(1);
        tree.insert(51);
        tree.delete(14);
        tree.delete(51);
        tree.delete(6);
        tree.delete(4);
        tree.print();
    }
}
