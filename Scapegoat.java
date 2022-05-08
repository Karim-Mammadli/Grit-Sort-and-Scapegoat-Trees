import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.lang.Math;

public class Scapegoat {

    private Node root;
    private int MaxNodeCount = 0;
    private int NodeCount = 0;
    private static final double threshold = 0.57;

    public class Node {
        T data;
        Node parent;
        Node left;
        Node right;

        public Node(T data, Node parent, Node left, Node right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            //if (this.parent != null)
            //return "[data="+data+" => " + this.parent.data + "]";
            return "[data=" + data + "]";
        }
    }

    public Scapegoat() {
        root = null;
    }
    
    public Scapegoat(T data) {
        root = new Node(data, null, null, null);
        NodeCount++;
    }

    public Node root() {
        return this.root;
    }

    private Node scapegoatNode(Node node) {

        Node temp = node.parent;
        while (temp != null) {
            if ((double) size(node)/size(node.parent) > Scapegoat.threshold)
                return node.parent;
            node = node.parent;
            temp = node.parent;
        }

        return null;
    }

    public Node rebuild(Node node) {

        List<Node> nodes = inorder(node);
        Node newRoot = buildBalanced(nodes);
        return newRoot;

    }

    public void add(T data) {
        if (root == null) {
            root = new Node(data, null, null, null);
            NodeCount++;
        } else {

            if (find(data) != null) {
                return;
            }

            Node currentNode = root;
            Node parentNode = null;
            double depth = 0;
            boolean inserted = false;

            if (currentNode != null) {
                do {

                    parentNode = currentNode;
                    depth++;
                    if (data.compareTo((currentNode.data)) == 0) {
                        break;
                    } else if (data.compareTo(currentNode.data) < 0) {
                        currentNode = currentNode.left;
                        inserted = true;
                    } else {
                        currentNode = currentNode.right;
                        inserted = true;
                    }

                } while (currentNode != null);
            }


            Node node = new Node(data, parentNode, null, null);
            if (node.data.compareTo(parentNode.data) == 1) {
                node.parent = parentNode;
                parentNode.right = node;
            } else {
                parentNode.left = node;
                node.parent = parentNode;
            }
            NodeCount++;

            MaxNodeCount = Math.max(MaxNodeCount, NodeCount);

            if (!(depth <= (Math.log10(NodeCount) * -1 / Math.log10(threshold)))) {
                Node scapegoat = scapegoatNode(find(data));
                Node sParent = scapegoat.parent;
                Node sRoot = rebuild(scapegoat);
//                MaxNodeCount = NodeCount;
                if (sParent == null) {
                    root = sRoot;
                } else {

                    if (sRoot.data.compareTo(find(sParent.data).data) < 0) {

                        sParent.left = sRoot;

                    } else {
                        sParent.right = sRoot;
                    }
                    sRoot.parent = sParent;
                }
            }
        }

    }

    public void remove(T data) {

        root = removeNode(root, data);
        NodeCount--;

        MaxNodeCount = Math.max(NodeCount, MaxNodeCount);

        if (NodeCount <= threshold * MaxNodeCount) {
            root = rebuild(root);
            root.parent = null;
//            MaxNodeCount = NodeCount;
        }
    }


    private Node removeNode(Node root, T data) {

        if (root == null) {
            return null;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = removeNode(root.left, data);
        } else if (data.compareTo(root.data) > 0) {

            root.right = removeNode(root.right, data);

        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }


            root.data = findMinValue(root.right);
            root.right = removeNode(root.right, root.data);
        }

        return root;
    }

    T findMinValue(Node root) {
        T minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }


    // preorder traversal
    public List<Node> preorder(Node node) {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        if (node.left != null) {
            nodes.addAll(preorder(node.left));
        }
        if (node.right != null) {
            nodes.addAll(preorder(node.right));
        }
        return nodes;
    }


    // inorder traversal
    public List<Node> inorder(Node node) {
        List<Node> nodes = new ArrayList<Node>();
        if (node.left != null) {
            nodes.addAll(inorder(node.left));
        }
        nodes.add(node);
        if (node.right != null) {
            nodes.addAll(inorder(node.right));
        }
        return nodes;
    }

    public void print() {
        List<Node> nodes = inorder(root);
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(nodes.get(i).toString());
        }
    }


    public Node find(T data) {
        Node current = root;
        int result;
        while (current != null) {
            result = data.compareTo(current.data);
            if (result == 0) {
                return current;
            } else if (result > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }


    // find the successor node
    public Node succNode(Node node) {
        Node succ = null;
        int result;
        Node current = node;
        while (current != null) {
            result = node.data.compareTo(current.data);
            if (result < 0) {
                succ = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return succ;
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }


    public List<Node> breadthFirstSearch() {
        System.out.println("root: " + this.root);
        Node node = root;
        List<Node> nodes = new ArrayList<Node>();
        Deque<Node> deque = new ArrayDeque<Node>();
        if (node != null) {
            deque.offer(node);
        }
        while (!deque.isEmpty()) {
            Node first = deque.poll();
            nodes.add(first);
            if (first.left != null) {
                deque.offer(first.left);
            }
            if (first.right != null) {
                deque.offer(first.right);
            }
        }
        return nodes;
    }


    public Node buildBalanced(List<Node> arr) {
        if (arr.size() == 0)
            return null;
        int middle = arr.size() / 2;
        Node subRoot = arr.get(middle);
        subRoot.left = buildBalanced(arr.subList(0, middle));
        if (subRoot.left != null)
            subRoot.left.parent = subRoot;
        subRoot.right = buildBalanced(arr.subList(middle + 1, arr.size()));
        if (subRoot.right != null)
            subRoot.right.parent = subRoot;
        return subRoot;
    }


    public static void main(String[] args) {
        // write your code here
        Scapegoat tree = new Scapegoat();
        tree.add(new T(40));
        tree.add(new T(10));
        tree.remove(new T(40));
        tree.add(new T(8));
        tree.add(new T(12));
        tree.add(new T(7));
        tree.add(new T(9));
        tree.add(new T(11));
        tree.add(new T(14));
        tree.add(new T(16));
        tree.add(new T(18));
        tree.remove(new T(14));
        tree.remove(new T(16));
        tree.remove(new T(12));
        tree.remove(new T(18));
        //tree.remove();
        System.out.println(tree.preorder(tree.root));
    }


}
