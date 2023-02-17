
import Myqueue.MyQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmnhn
 */
class TreeFunc {

    Node root;

    TreeFunc() {
        root = null;
    }
    void insert(int x) {
        if (root == null) {
            root = new Node(x);
            return;
        }
        Node f, p;
        p = root;
        f = null;
        while (p != null) {
            if (p.info == x) {
                System.out.println(" The key " + x + " already exists, no insertion");
                return;
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x < f.info) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }


//    void insert(int x) {
//        root = insert(root, x);
//    }
//
//    Node insert(Node p, int x) {
//        if (p == null) {
//            p = new Node(x);
//        } else if (x < p.info) {
//            p.left = insert(p.left, x);
//        } else if (x > p.info) {
//            p.right = insert(p.right, x);
//        }
//        return p;
//    }

    void visit(Node p) {
        System.out.print(p.info + " ");
    }

    int height() {
//        if (p == null) {
//            return -1;
//        } else {
//            int hL = height(p.left);
//            int hR = height(p.right);
//            if (hL > hR) {
//                return 1 + hL;
//            } else {
//                return 1 + hR;
//            }
//        }
          return height(root);
    }
    private int height (Node node){
        if (node == null) return 0;
        return 1 + Math.max(height(node.left),height(node.right));
    }
    void breadth() throws Exception {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            p = (Node) q.dequeue();
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
            visit(p);
        }

//        if (root == null) {
//            return;
//        }
//        Queue<Node> q = new LinkedList<>();
//        q.add(root);
//        while (!q.isEmpty()) {
//            Node p = q.remove();
//            visit(p);
//            if (p.left != null) {
//                q.add(p.left);
//            }
//            if (p.right != null) {
//                q.add(p.right);
//            }
//        }
    }

    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    Node search(int x) {
        return search(root, x);
    }

    Node search(Node p, int x) {
        if (p == null) {
            return null;
        } else if (x == p.info) {
            return p;
        } else if (x < p.info) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }
    
    void deleteByMerging(int x) {
        root = deleteByMergingRec(root, x);
    }

    Node deleteByMergingRec(Node root, int x) {
        if (root == null) {
            return root;
        }
        if (x < root.info) {
            root.left = deleteByMergingRec(root.left, x);
        } else if (x > root.info) {
            root.right = deleteByMergingRec(root.right, x);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.info = minValue(root.right);
            root.right = deleteByMergingRec(root.right, root.info);
        }
        return root;
    }

    int minValue(Node root) {
        int minv = root.info;
        while (root.left != null) {
            minv = root.left.info;
            root = root.left;
        }
        return minv;
    }

    void deleteByCopying(int x) {
        root = deleteByCopyingRec(root, x);
    }

    Node deleteByCopyingRec(Node root, int x) {
        if (root == null) {
            return null;
        }
        if (x < root.info) {
            root.left = deleteByCopyingRec(root.left, x);
        } else if (x > root.info) {
            root.right = deleteByCopyingRec(root.right, x);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            Node temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            root.info = temp.info;
            root.right = deleteByCopyingRec(root.right, temp.info);
        }
        return root;
    }
    void deleteNode(int x) {
        root = deleteNodeRec(root, x);
    }

    Node deleteNodeRec(Node root, int x) {
        
        //check null
        if (root == null) {
            return root;
        }
        //neu ma be hon thi goi lai ham va truyen Node.left
        if (x < root.info) {
            root.left = deleteNodeRec(root.left, x);
        //neu ma lon hon thi truyen Node.right
        } else if (x > root.info) {
            root.right = deleteNodeRec(root.right, x);
            
        //code chay den day nghia la Node.info = x
        } else {
            //check Node có child kh neu co thi replace Node = Node.left/right
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            
            // case that this Node has 2 child or more
            Node temp = root.right;
            //find the smallest node
            while (temp.left != null) {
                temp = temp.left;
            }
            root.info = temp.info;
            root.right = deleteNodeRec(root.right, temp.info);
        }
        return root;
    }

}
