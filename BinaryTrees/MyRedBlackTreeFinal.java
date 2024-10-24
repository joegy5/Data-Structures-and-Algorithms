// import org.w3c.dom.Node;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

/**
 * Implemention file for CS 3345.HON.24F Programming Project #2.
 * <p>
 * Student name: Joel Gurivireddy
 * Student NetID: JXG220051
 *
 * @param <E> The element's type.
 */
public class MyRedBlackTree<E extends Comparable<? super E>> {
  private static class Node<E> {
    public E element; // public to simplify remove
    public boolean isRed; // Red if true, black if false
    public Node<E> left, right;
    public Node<E> parent;

    /**
     * Instantiate a new node.
     *
     * @param element The node's element.
     */
    Node(E element, boolean isRed) {
      this.element = element;
      this.left = null;
      this.right = null;
      this.parent = null;
      this.isRed = isRed;
    }
  }
  
  /**
   * Instantiate an empty red-black tree.
   */
  public Node<E> root;
  public int size;

  MyRedBlackTree() {
    root = null;
    size = 0;

  }
  /**
   * Print all elements of tree in sorted order with the color of each element's node.
   * Elements are printed one line at a time, each followed by a space and then
   * its color in paranethes.
   * Also, each element is indented a number of '=' equal to twice the node's depth.
   * <p>
   * For example, a tree containing 5, 10, 15, 20, 25, and 30 might be printed as
   * ```
   * ==5 (black)
   * 10 (black)
   * ====15 (black)
   * ==20 (red)
   * ====25 (black)
   * ======30 (red)
   * ```
   * <p>
   * Implementation should run in O(n) time for a tree of n elements.
   */

  public void printInorderTraversal(Node<E> curr, int currLevel) {
    if(curr == null) return;
    
    printInorderTraversal(curr.left, currLevel + 1);
    
    for(int i = 0; i < 2*currLevel; i++) {
      System.out.print("=");
    }
    System.out.println(curr.element + "(" + (curr.isRed ? "red" : "black") + ")");

    printInorderTraversal(curr.right, currLevel + 1);

  }
  public void printAll() {
    printInorderTraversal(root, 0);
  }

  /**
   * Returns whether or not the tree contains the given element.
   * <p>
   * Implementation should run in O(log n) time for a tree of n elements.
   *
   * @param element The element to find.
   * @returns true if the tree contains the element or false otherwise.
   */

  public boolean contains(E element) {
    Node<E> curr = root;
    while(curr != null) {
      if((int)curr.element == (int)element) return true;
      if((int)element > (int)curr.element) { // e = 5, ce = 2
        curr = curr.right;
      }
      else {
        curr = curr.left;
      }
    }
    return false;
  }

  /**
   * Returns the minimum element of the tree.
   * <p>
   * Implementation should run in O(log n) time for a tree of n elements.
   *
   * @returns The minimum element of the tree or null if tree is empty.
   */
  public E findMin() {
    if(root == null) return null;

    Node<E> curr = root;
    while(curr.left != null) {
      curr = curr.left;
    }
    return curr.element;
  }

  /**
   * Returns the maximum element of the tree.
   * <p>
   * Implementation should run in O(log n) time for a tree of n elements.
   *
   * @returns The maximum element of the tree or null if tree is empty.
   */
  public E findMax() {
    if(root == null) return null;

    Node<E> curr = root;
    while(curr.right != null) {
      curr = curr.right;
    }
    return curr.element;
  }

  /**
   * Inserts a new element into the tree.
   * If the element already exists in the tree, this method makes no changes.
   * <p>
   * Implementation should run in O(log n) time for a tree of n elements.
   *
   * @param element The element to be inserted.
   */

  public Node<E> singleRotateLeft(Node<E> g, Node<E> p, Node<E> v) {
    //System.out.println("srl called");
    Node<E> pright = p.right;
    p.left = v;
    p.right = g;
    g.left = pright;
    if(pright != null) pright.parent = g;
    g.parent = p;
    v.parent = p;
    return p;
  }

  public Node<E> singleRotateRight(Node<E> g, Node<E> p, Node<E> v) {
    //System.out.println("srr called");
    Node<E> pleft = p.left;
    p.left = g;
    p.right = v;
    g.right = pleft;
    if(pleft != null) pleft.parent = g;
    g.parent = p;
    v.parent = p;
    return p;
  }

  public Node<E> doubleRotateLeft(Node<E> g, Node<E> p, Node<E> v) {
    Node<E> vleft = v.left, vright = v.right;
    v.left = p;
    v.right = g;
    p.parent = v;
    g.parent = v;
    p.right = vleft;
    if(vleft != null) vleft.parent = p;
    g.left = vright;
    if(vright != null) vright.parent = g;
    return v;

  }

  public Node<E> doubleRotateRight(Node<E> g, Node<E> p, Node<E> v) {
    Node<E> vleft = v.left, vright = v.right;
    v.left = g;
    v.right = p;
    p.parent = v;
    g.parent = v;
    p.left = vright;
    if(vright != null) vright.parent = p;
    g.right = vleft;
    if(vleft != null) vleft.parent = g;
    return v;
  }

  public void insert(E element) {
    //NOTE: remember to add the parent connection after inserting
    
    //Case 0: Tree already contains the element
    if(contains(element)) return;

    size++;


    //Case 1: v (node containing element) is root (i.e., first node we inserted) --> color v black
    if(root == null) {
      root = new Node<E>(element, false);
      return;
    }
    
    //Find node that should be v's parent
    Node<E> curr = root;
    while(true) {
      if((int)curr.element < (int)element) {
        if(curr.right == null) break;
        else curr = curr.right;
      }
      else {
        if(curr.left == null) break;
        else curr = curr.left; 
      }
    }

    //Insert v as the node's appropriate child
    Node<E> v = new Node<E>(element, true);
    if((int)element < (int)curr.element) curr.left = v;
    else curr.right = v;
    v.parent = curr;

    insertHelper(v);
   
  }

  public void insertHelper(Node<E> v) {
    //Case 1: v is the root (due to a past promotion, because case of it being root due to being first element 
    //inserted is already covered)
    //We can simply color v black because every path from root will still have same number of black nodes
    if(root == v) {
      v.isRed = false;
      return;
    }

    //Case 2: v has black parent --> parent is either 2-node or 3-node --> we are done
      //Also takes care of scenario where parent is root (since root has to be black)
    if(!v.parent.isRed) {
      return;
    }

    //Case 3: v has red parent --> splits into subcases
    else {
      Node<E> p = v.parent, g = p.parent, gg = g.parent;
      //Case 3a: p has black sibling --> g was 3-node before v, now is misshapen 4-node after v --> need to reshape
      if((p == g.left && (g.right == null || !g.right.isRed)) || (p == g.right && (g.left == null || !g.left.isRed))) {
        //Case 3a-i: v is outer grandchild of g --> single rotation
        if(g.left == p && p.left == v) {
          if(gg == null) { // g is the root
            root = singleRotateLeft(g, p, v);
            root.parent = null;
            root.isRed = false;
            root.right.isRed = true;
            root.left.isRed = true;
          }
          else { // g is NOT the root
            if(gg.left == g) {
              gg.left = singleRotateLeft(g, p, v);
              gg.left.parent = gg;
              gg.left.isRed = false;
              gg.left.right.isRed = true;
              gg.left.left.isRed = true;
            }
            else {
              gg.right = singleRotateLeft(g, p, v);
              gg.right.parent = gg;
              gg.right.isRed = false;
              gg.right.right.isRed = true;
              gg.right.left.isRed = true;
            }
          }
        }
        else if(g.right == p && p.right == v) {
          if(gg == null) { // g is the root
            root = singleRotateRight(g, p, v);
            root.parent = null;
            root.isRed = false;
            root.right.isRed = true;
            root.left.isRed = true;

          }
          else { // g is NOT the root
            if(gg.left == g) {
              gg.left = singleRotateRight(g, p, v);
              gg.left.parent = gg;
              gg.left.isRed = false;
              gg.left.left.isRed = true;
              gg.left.right.isRed = true;
            }
            else {
              gg.right = singleRotateRight(g, p, v);
              gg.right.parent = gg;
              gg.right.isRed = false;
              gg.right.left.isRed = true;
              gg.right.right.isRed = true;
            }
          }
        }
        
        //Case 3a-ii: v is inner grandchild of g
        else if(g.left == p && p.right == v) {
          if(gg == null) { // g is the root
            root = doubleRotateLeft(g, p, v);
            root.parent = null;
            root.isRed = false;
            root.right.isRed = true;
            root.left.isRed = true;
          }
          else { // g is NOT the root
            if(gg.left == g) {
              gg.left = doubleRotateLeft(g, p, v);
              gg.left.parent = gg;
              gg.left.isRed = false;
              gg.left.left.isRed = true;
              gg.left.right.isRed = true;
            }
            else {
              gg.right = doubleRotateLeft(g, p, v);
              gg.right.parent = gg;
              gg.right.isRed = false;
              gg.right.left.isRed = true;
              gg.right.right.isRed = true;
            }
          }
        }
        else if(g.right == p && p.left == v) {
          if(gg == null) { // g is the root
            root = doubleRotateRight(g, p, v);
            root.parent = null;
            root.isRed = false;
            root.right.isRed = true;
            root.left.isRed = true;
          }
          else { // g is NOT the root
            if(gg.left == g) {
              gg.left = doubleRotateRight(g, p, v);
              gg.left.parent = gg;
              gg.left.isRed = false;
              gg.left.left.isRed = true;
              gg.left.right.isRed = true;
            }
            else {
              gg.right = doubleRotateRight(g, p, v);
              gg.right.parent = gg;
              gg.right.isRed = false;
              gg.right.left.isRed = true;
              gg.right.right.isRed = true;
            }
          }
        }
      }

      //Case 3b: p has red sibling --> we overflowed the 4-node
        //Promote g to x-node representation above, which may also be overflowed --> recursively fix problem
      else {
        g.isRed = true;
        g.left.isRed = false;
        g.right.isRed = false;
        insertHelper(g);
        
      }
    }
  }

  /**
   * Removes the element from the tree.
   * If the element does not exist in the tree, this method makes no changes.
   * <p>
   * Implementation should run in O(log n) time for a tree of n elements.
   *
   * @param element The element to be removed.
   */
  private Node<E> findMinNode(Node<E> node) {
    if (node == null) {
      return null;
    }

    while (node.left != null) {
      node = node.left;
    }

    return node;
  }

  public void remove(E element) {
    if(!contains(element)) return;

    E elementOriginal = element;
    Node<E> parent = null;
    Node<E> node = root;
    if (node == null) {
      return; // tree is already empty
    }

    while (node != null) { // will return when done
      int compareResult = element.compareTo(node.element);

      if (compareResult < 0) { // element < node.element
        parent = node;
        node = node.left;
      } else if (compareResult > 0) { // element > node.element
        parent = node;
        node = node.right;
      } else if (node.left != null && node.right != null) { // two children
        element = findMinNode(node.right).element; // will now search for successor
        node.element = element;
        parent = node;
        node = node.right;
      } else { // at most one child
        size--; // this branch actually removes the node
        Node<E> child = null;
        if (node.left != null) { // only child on left
          child = node.left;
        } else {
          child = node.right;
        }
        node.element = elementOriginal;
        removeHelper(node, child, parent);
        break;
      }
    }
  }

  public void removeHelper(Node<E> v, Node<E> vchild, Node<E> p) {
    //Case 1: v is red --> v is leaf, just get rid of it
    if(v.isRed) {
      if(p != null) {
        if(p.left == v) p.left = vchild;
        else p.right = vchild;
        if(vchild != null) vchild.parent = p;
      } else {
        root = vchild;
        if(vchild != null) vchild.parent = null;
      }
      v = null;
      return;
    }
    

    //Case 2: v is black with single red child --> remove v, make red child black
    else if(vchild != null && vchild.isRed) {
      vchild.isRed = false;
      if(p != null) {
        if(p.left == v) p.left = vchild;
        else p.right = vchild;
        if(vchild != null) vchild.parent = p;
      } else {
        root = vchild;
        if(vchild != null) vchild.parent = null;
      }
      v = null;
      return;
    }

    //Case 3: v is black leaf (no red children) --> splits into subcases
    else {
      //Case 3a: v is root --> remove it, make its one child the new root
      if(root == v) {
        root = vchild;
        if(vchild != null) vchild.parent = null;
        v = null;
        return;
      }

      //Case 3b: v is NOT the root --> splits into further subcases
      else {
        Node<E> g = p.parent;

        //Preprocess if v's sibling w is red (meaning that p is black) by doing single rotation
        if(p.left == v && (p.right != null && p.right.isRed)) {
          if(root == p) {
            root = singleRotateRight(p, p.right, p.right.right);
            root.parent = null;
            root.isRed = false;
            root.left.isRed = true;
          }
          else {
            if(g.left == p) {
              g.left = singleRotateRight(p, p.right, p.right.right);
              g.left.parent = g;
              g.left.isRed = false;
              g.left.left.isRed = true;
              p = g.left.left;
              v = p.left;
            }
            else {
              g.right = singleRotateRight(p, p.right, p.right.right);
              g.right.parent = g;
              g.right.isRed = false;
              g.right.left.isRed = true;
              p = g.right.left;
              v = p.left;
            }
          }
        }
        else if(p.right == v && (p.left != null && p.left.isRed)) {
          if(root == p) {
            root = singleRotateLeft(p, p.left, p.left.left);
            root.parent = null;
            root.isRed = false;
            root.right.isRed = true;
            p = root.right;
            v = p.right;
          }
          else {
            if(g.left == p) {
              g.left = singleRotateLeft(p, p.left, p.left.left);
              g.left.parent = g;
              g.left.isRed = false;
              g.left.right.isRed = true;


            }
            else {
              g.right = singleRotateLeft(p, p.left, p.left.left);
              g.right.parent = g;
              g.right.isRed = false;
              g.right.right.isRed = true;
            }
          }
        }

        //Case 3b-i: v's sibling w is black with red child c
        if(p.left == v && ((p.right.left != null && p.right.left.isRed) || (p.right.right != null && p.right.right.isRed))) {
          boolean pIsRed = p.isRed;
          //Case 3b-i-a: c is on side of w closer to v
          if(p.left == v && (p.right.left != null && p.right.left.isRed)) {
            p.left = v.left != null ? v.left : v.right;
            if(p.left != null) p.left.parent = p;
            v = null;
            g = p.parent;

            if(root == p) {
              root = doubleRotateRight(p, p.right, p.right.left);
              root.parent = null;
              root.isRed = pIsRed;
              root.left.isRed = false;
              root.right.isRed = false;
              p.isRed = false;
              p.parent.isRed = false;
              if(p.parent.left != null) p.parent.left.isRed = false;
              if(p.parent.right != null) p.parent.right.isRed = false;
            }
            else {
              if(g.left == p) {
                g.left = doubleRotateRight(p, p.right, p.right.left);
                g.left.parent = g;
                g.left.isRed = pIsRed;
                g.left.left.isRed = false;
                g.left.right.isRed = false;
                p.isRed = false;
                p.parent.isRed = false;
                if(p.parent.left != null) p.parent.left.isRed = false;
                if(p.parent.right != null) p.parent.right.isRed = false;
              }
              else {
                g.right = doubleRotateRight(p, p.right, p.right.left);
                g.right.parent = g;
                g.right.isRed = pIsRed;
                g.right.left.isRed = false;
                g.right.right.isRed = false;
                p.isRed = false;
                p.parent.isRed = false;
                if(p.parent.left != null) p.parent.left.isRed = false;
                if(p.parent.right != null) p.parent.right.isRed = false;
              }
            }
            return;
          }

          //Case 3b-i-b: c is on side of w farther from v
          else if(p.left == v && (p.right.right != null && p.right.right.isRed)) {
            p.left = v.left != null ? v.left : v.right;
            if(p.left != null) p.left.parent = p;
            v = null;
            g = p.parent;
            if(root == p) {
              root = singleRotateRight(p, p.right, p.right.right);
              root.parent = null;
              root.isRed = pIsRed;
              root.left.isRed = false;
              p.isRed = false;
              p.parent.isRed = false;
              if(p.parent.left != null) p.parent.left.isRed = false;
              if(p.parent.right != null) p.parent.right.isRed = false;
            }
            else {
              if(g.left == p) {
                g.left = singleRotateRight(p, p.right, p.right.right);
                g.left.parent = g;
                g.left.isRed = pIsRed;
                g.left.left.isRed = false;
                p.isRed = false;
                p.parent.isRed = false;
                if(p.parent.left != null) p.parent.left.isRed = false;
                if(p.parent.right != null) p.parent.right.isRed = false;
              }
              else {
                g.right = singleRotateRight(p, p.right, p.right.right);
                g.right.parent = g;
                g.right.isRed = pIsRed;
                g.right.left.isRed = false;
                p.isRed = false;
                p.parent.isRed = false;
                if(p.parent.left != null) p.parent.left.isRed = false;
                if(p.parent.right != null) p.parent.right.isRed = false;
              }
            }
            return;
          }

        }
        else if(p.right == v && ((p.left.left != null && p.left.left.isRed) || (p.left.right != null && p.left.right.isRed))) {
          boolean pIsRed = p.isRed;
          //Case 3b-i-a: c is on side of w closer to v
          if(p.right == v && (p.left.right != null && p.left.right.isRed)) {
            p.right = v.left != null ? v.left : v.right;
            if(p.right != null) p.right.parent = p;
            v = null;
            g = p.parent;

            if(root == p) {
              root = doubleRotateLeft(p, p.left, p.left.right);
              root.parent = null;
              root.isRed = pIsRed;
              root.left.isRed = false;
              root.right.isRed = false;
              p.isRed = false;
              p.parent.isRed = false;
              if(p.parent.left != null) p.parent.left.isRed = false;
              if(p.parent.right != null) p.parent.right.isRed = false;
            }
            else {
              if(g.left == p) {
                g.left = doubleRotateLeft(p, p.left, p.left.right);
                g.left.parent = g;
                g.left.isRed = pIsRed;
                g.left.left.isRed = false;
                g.left.right.isRed = false;
                p.isRed = false;
                p.parent.isRed = false;
                if(p.parent.left != null) p.parent.left.isRed = false;
                if(p.parent.right != null) p.parent.right.isRed = false;
              }
              else {
                g.right = doubleRotateLeft(p, p.left, p.left.right);
                g.right.parent = g;
                g.right.isRed = pIsRed;
                g.right.left.isRed = false;
                g.right.right.isRed = false;
                p.isRed = false;
                p.parent.isRed = false;
                if(p.parent.left != null) p.parent.left.isRed = false;
                if(p.parent.right != null) p.parent.right.isRed = false;
              }
            }
          }

          //Case 3b-i-b: c is on side of w farther from v
          else if(p.right == v && (p.left.left != null && p.left.left.isRed)) {
            p.right = v.left != null ? v.left : v.right;
            if(p.right != null) p.right.parent = p;
            v = null;
            g = p.parent;

            if(root == p) {
              root = singleRotateLeft(p, p.left, p.left.left);
              root.parent = null;
              root.isRed = pIsRed;
              root.right.isRed = false;
              p.isRed = false;
              p.parent.isRed = false;
              if(p.parent.left != null) p.parent.left.isRed = false;
              if(p.parent.right != null) p.parent.right.isRed = false;
            }
            else {
              if(g.left == p) {
                g.left = singleRotateLeft(p, p.left, p.left.left);
                g.left.parent = g;
                g.left.isRed = pIsRed;
                g.left.right.isRed = false;
                p.isRed = false;
                p.parent.isRed = false;
                if(p.parent.left != null) p.parent.left.isRed = false;
                if(p.parent.right != null) p.parent.right.isRed = false;
              }
              else {
                g.right = singleRotateLeft(p, p.left, p.left.left);
                g.right.parent = g;
                g.right.isRed = pIsRed;
                g.right.right.isRed = false;
                p.isRed = false;
                p.parent.isRed = false;
                if(p.parent.left != null) p.parent.left.isRed = false;
                if(p.parent.right != null) p.parent.right.isRed = false;
              }
            }
          }
        }

        //Case 3b-ii: w has no RED children (either null or only black children) --> splits into subcases
        else if(p.left == v && (p.right == null || ((p.right.left == null || !p.right.left.isRed) && (p.right.right == null || !p.right.right.isRed)))) {
          //Case 3b-ii-a: p is red --> demote p, remove v
          if(p.isRed) {
            p.isRed = false;
            p.left = v.left != null ? v.left : v.right;
            if(p.left != null) p.left.parent = p;
            v = null;
            if(p.right != null) p.right.isRed = true;
          }

          //Case 3b-ii-b: p is black --> recursively fix problem
          else {
            p.left = v.left != null ? v.left : v.right;
            if(p.left != null) p.left.parent = p;
            v = null;
            if(p.right != null) p.right.isRed = true;
            if(root != p) { // if p is the root, we are done. Otherwise, demote p and recurse
              g = p.parent;
              Node<E> dummy = new Node<E>(null, false);
              dummy.left = p;
              p.parent = dummy;

              if(g.left == p) {g.left = dummy; dummy.parent = g;}
              else {g.right = dummy; dummy.parent = g;}
              removeHelper(dummy, p, g);
            
            }
          }
        }
        else if(p.right == v && (p.left == null || ((p.left.left == null || !p.left.left.isRed) && (p.left.right == null || !p.left.right.isRed)))) {
          if(p.isRed) {
            p.isRed = false;
            p.right = v.left != null ? v.left : v.right;
            if(p.right != null) p.right.parent = p;
            v = null;
            if(p.left != null) p.left.isRed = true;
          }
          else {
            p.right = v.left != null ? v.left : v.right;
            if(p.right != null) p.right.parent = p;
            v = null;
            if(p.left != null) p.left.isRed = true;
            if(root != p) { // if p is the root, we are done. Otherwise, demote p and recurse
              g = p.parent;
              Node<E> dummy = new Node<E>(null, false);
              dummy.left = p;

              if(g.left == p) {g.left = dummy; dummy.parent = g;}
              else {g.right = dummy; dummy.parent = g;}
              removeHelper(dummy, p, g);
            }
          }
        }
      }
    }
  }

  /**
   * Returns number of elements in tree.
   * <p>
   * Implementation should run in O(1) time for a tree of n elements.
   *
   * @returns Number of elements in list.
   */
  public int size() {
    return this.size;
  }
}

