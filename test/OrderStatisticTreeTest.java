import ua.leirimnad.OrderStatisticTree;
import ua.leirimnad.RationalNumber;

public class OrderStatisticTreeTest {
    public static void main(String[] args) {
        OrderStatisticTree<RationalNumber> tree = new OrderStatisticTree<>();
        tree.insert(new RationalNumber(1, 1));
        tree.insert(new RationalNumber(5, 2));
        tree.insert(new RationalNumber(1, 7));
        tree.insert(new RationalNumber(3, 3));
        tree.insert(new RationalNumber(-7, 2));
        tree.insert(new RationalNumber(1, 1));
        tree.print();
        System.out.println("=============");
        System.out.println(tree.rank(new RationalNumber(1, 1)));
        System.out.println(tree.rank(new RationalNumber(1, 7)));
        System.out.println(tree.rank(new RationalNumber(-1, 1)));
        System.out.println(tree.rank(new RationalNumber(1, 1)));
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
