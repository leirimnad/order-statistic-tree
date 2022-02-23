package ua.leirimnad;

public class OrderStatisticTree<T extends Comparable<T>> {
    protected Node root;

    public OrderStatisticTree() {
        this.root = nullNode;
    }

    public void insert(T key){
        Node y = nullNode;
        Node x = this.root;
        Node z = new Node(key);
        while (!isNullNode(x)){
            y = x;
            x.size++;
            if (key.compareTo(x.key) < 0)
                x = x.leftChild;
            else
                x = x.rightChild;
        }
        z.parent = y;
        if (isNullNode(y))
            this.root = z;
        else if (z.key.compareTo(y.key) < 0)
            y.leftChild = z;
        else
            y.rightChild = z;

        z.size = 1;
        this.insertFixup(z);
    }

    private void insertFixup(Node z) {
        while (!z.parent.black){
            if (z.parent.equals(z.parent.parent.leftChild)){
                Node y = z.parent.parent.rightChild;
                if (!y.black){
                    z.parent.black = true;
                    y.black = true;
                    z.parent.parent.black = false;
                }
                else {
                    if (z.parent.rightChild.equals(z)) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.black = true;
                    z.parent.parent.black = false;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node y = z.parent.parent.leftChild;
                if (!y.black){
                    z.parent.black = true;
                    y.black = true;
                    z.parent.parent.black = false;
                }
                else {
                    if (z.parent.leftChild.equals(z)) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.black = true;
                    z.parent.parent.black = false;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.black = true;
    }

    public void delete(T key){
        Node z = find(key);
        if (isNullNode(z))
            return;

        Node y;
        if (isNullNode(z.leftChild) || isNullNode(z.rightChild))
            y = z;
        else
            y = this.treeSuccessor(z);

        Node x;
        if (!isNullNode(y.leftChild))
            x = y.leftChild;
        else
            x = y.rightChild;

        Node i = y;
        while (!isNullNode(i)){
            i.size--;
            i = i.parent;
        }

        x.parent = y.parent;
        if (isNullNode(y.parent))
            this.root = x;
        else {
            if (y.equals(y.parent.leftChild))
                y.parent.leftChild = x;
            else
                y.parent.rightChild = x;
        }

        if (!y.equals(z))
            z.key = y.key;

        if (y.black)
            this.deleteFixup(x);
    }

    private void deleteFixup(Node x){
        while (!x.equals(this.root) && x.black){
            if (x.equals(x.parent.leftChild)){
                Node w = x.parent.rightChild;
                if (!w.black){
                    w.black = true;
                    x.parent.black = false;
                    leftRotate(x.parent);
                    w = x.parent.rightChild;
                }
                if (w.leftChild.black && w.rightChild.black){
                    w.black = false;
                    x = x.parent;
                }
                else {
                    if (w.rightChild.black){
                        w.leftChild.black = true;
                        w.black = false;
                        rightRotate(w);
                        w = x.parent.rightChild;
                    }
                    w.black = x.parent.black;
                    x.parent.black = true;
                    w.rightChild.black = true;
                    leftRotate(x.parent);
                    x = this.root;
                }
            } else {
                Node w = x.parent.leftChild;
                if (!w.black){
                    w.black = true;
                    x.parent.black = false;
                    rightRotate(x.parent);
                    w = x.parent.leftChild;
                }
                if (w.rightChild.black && w.leftChild.black){
                    w.black = false;
                    x = x.parent;
                }
                else {
                    if (w.leftChild.black){
                        w.rightChild.black = true;
                        w.black = false;
                        leftRotate(w);
                        w = x.parent.leftChild;
                    }
                    w.black = x.parent.black;
                    x.parent.black = true;
                    w.leftChild.black = true;
                    rightRotate(x.parent);
                    x = this.root;
                }
            }
        }
        x.black = true;
    }

    protected void leftRotate(Node first){
        Node second = first.rightChild;
        first.rightChild = second.leftChild;

        if (!isNullNode(second.leftChild))
            second.leftChild.parent = first;

        second.parent = first.parent;
        if (isNullNode(first.parent))
            this.root = second;
        else if (first.equals(first.parent.leftChild))
            first.parent.leftChild = second;
        else
            first.parent.rightChild = second;

        second.leftChild = first;
        first.parent = second;

        second.size = first.size;
        first.size = first.leftChild.size + first.rightChild.size + 1;
    }

    protected void rightRotate(Node first){
        Node second = first.leftChild;
        first.leftChild = second.rightChild;

        if (!isNullNode(second.rightChild))
            second.rightChild.parent = first;

        second.parent = first.parent;
        if (isNullNode(first.parent))
            this.root = second;
        else if (first.equals(first.parent.leftChild))
            first.parent.leftChild = second;
        else
            first.parent.rightChild = second;

        second.rightChild = first;
        first.parent = second;

        second.size = first.size;
        first.size = first.leftChild.size + first.rightChild.size + 1;
    }

    protected int blackHeight(Node x){
        int c = 0;
        while (!isNullNode(x)){
            x = x.rightChild;
            if (x.black)
                c++;
        }
        return c;
    }

    private Node treeSuccessor(Node x){
        if (!isNullNode(x.rightChild))
            return this.treeMinimum(x.rightChild);
        Node y = x.parent;
        while (!isNullNode(y) && x.equals(y.rightChild)){
            x = y;
            y = y.parent;
        }
        return y;
    }

    private Node treeMinimum(Node x) {
        while (!isNullNode(x.leftChild))
            x = x.leftChild;
        return x;
    }

    public T select(int rank){
        if (rank <= 0 || rank > this.root.size)
            return null;
        return select(this.root, rank);
    }

    private T select(Node x, int i){
        int r = x.leftChild.size + 1;
        if (i == r)
            return x.key;
        else if (i < r)
            return select(x.leftChild, i);
        return select(x.rightChild, i-r);
    }

    public int rank(T key){
        Node z = findSymmetric(key);
        if (isNullNode(z))
            return 0;
        return rank(z);
    }

    private int rank(Node x){
        int r = x.leftChild.size + 1;
        Node y = x;
        while (!isNullNode(y)){
            if (y.equals(y.parent.rightChild))
                r = r + y.parent.leftChild.size + 1;
            y = y.parent;
        }
        return r;
    }

    private Node find(T key){
        Node z = this.root;
        while (!isNullNode(z)){
            if (key.compareTo(z.key) == 0)
                break;
            else if (key.compareTo(z.key) < 0)
                z = z.leftChild;
            else
                z = z.rightChild;
        }

        return z;
    }

    private Node findSymmetric(T key){
        return findSymmetric(this.root, key);
    }

    private Node findSymmetric(Node z, T key){
        if (isNullNode(z))
            return nullNode;

        Node leftRes = findSymmetric(z.leftChild, key);
        if (!isNullNode(leftRes))
            return leftRes;

        if (z.key.equals(key))
            return z;

        return findSymmetric(z.rightChild, key);
    }

    protected class Node {
        public boolean black;
        public Node parent, leftChild, rightChild;
        public T key;
        public int size;

        public Node(T key, Node parent, Node leftChild, Node rightChild, boolean black) {
            this.black = black;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.key = key;
            this.size = 0;
        }

        public Node(T key) {
            this.black = false;
            this.parent = nullNode;
            this.leftChild = nullNode;
            this.rightChild = nullNode;
            this.key = key;
            this.size = 0;
        }
    }

    protected final Node nullNode = new Node(null, null, null, null, true);
    protected boolean isNullNode(Node x){
        return x.equals(nullNode);
    }

    public void print(){
        print(this.root, 0);
    }

    private void print(Node x, int tabs){
        for (int i = 0; i < tabs; i++)
            System.out.print("\t");
        if (isNullNode(x))
            System.out.println(">  < null");
        else {
            System.out.println(">" + x.key + "< "+ (x.black ? "black" : "red") + ", s=" + x.size);
            print(x.leftChild, tabs+1);
            print(x.rightChild, tabs+1);
        }
    }
}
