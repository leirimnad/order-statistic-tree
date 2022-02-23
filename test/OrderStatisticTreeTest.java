import ua.leirimnad.OrderStatisticTree;

public class OrderStatisticTreeTest {
    public static void main(String[] args) {
        OrderStatisticTree<Integer> tree = new OrderStatisticTree<>();
        tree.insert(4);
        tree.insert(6);
        tree.insert(14);
        tree.insert(5);
        tree.insert(6);
        tree.insert(4);
        tree.insert(5);
        tree.insert(1);
        tree.insert(51);
        tree.delete(51);
        tree.delete(2);
        tree.insert(5);

        tree.print();
        System.out.println(tree.rank(1));
        System.out.println(tree.rank(4));
        System.out.println(tree.rank(5));
        System.out.println(tree.rank(51));
        System.out.println(tree.rank(14));
        System.out.println(tree.rank(6));
        System.out.println("=============");
        System.out.println(tree.select(1));
        System.out.println(tree.select(2));
        System.out.println(tree.select(3));
        System.out.println(tree.select(4));
        System.out.println(tree.select(5));
        System.out.println(tree.select(6));
        System.out.println(tree.select(7));
        System.out.println(tree.select(8));
    }
}
