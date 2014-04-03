/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebalanceada;

/**
 *
 * @author Jeferson
 */
public class Tree {

    private TreeNode raiz;
    private boolean balanceada;

    public Tree() {
        raiz = null;
    }

    public void insereNode(int insertValue) {
        if (raiz == null) {
            raiz = new TreeNode(insertValue);
            System.out.print("[Root: " + raiz + "]");
        } else {
            raiz.insereNode(insertValue);

            calculateFB(0); // recalculate all FatBalls  -- 0 to not show FB during calculting ...
            balanceada = false; // used in setFailNode() to control recursion
            setFailNode(raiz, null, -1);  // locate the first preOrder node with FB > |-1,0,1|

            if (balanceada) {

                System.out.println("\n****************************************************");
                System.out.println("BALANCEADA");
                System.out.println("\n****************************************************");
            }
        }
    }

    public void showAltura() {
        showAlturaHelper(raiz);
    }

    private void showAlturaHelper(TreeNode node) {
        if (node == null) {
            return;
        }

        node.altura = qualAlturaHelper(node) - 1;
        System.out.print("[" + (node.info) + "=" + (node.altura) + "]");
        showAlturaHelper(node.lNode);
        showAlturaHelper(node.rNode);
    }

    private int qualAlturaHelper(TreeNode node) {
        if ((node == null)) {
            return 0;
        };

        return Math.max(1 + qualAlturaHelper(node.lNode), 1
                + qualAlturaHelper(node.rNode));
    }

    public void calculateFB(int show) // calculate and show FB = 1 or just calculate = 0
    {
        calculateFBHelper(raiz, show);
    }

    private void calculateFBHelper(TreeNode node, int show) {
        if (node == null) {
            return;
        };

        node.fb = (qualAlturaHelper(node.rNode) - 1) - (qualAlturaHelper(node.lNode) - 1);

        if (show == 1) {
            System.out.print("\n[fatbal(" + (node.info) + ")=" + (node.fb) + "]");
        }

        calculateFBHelper(node.lNode, show);
        calculateFBHelper(node.rNode, show);
    }

    public TreeNode rotateSimpleLeft(TreeNode p) {
        TreeNode temp = p.rNode;
        p.rNode = temp.lNode;
        temp.lNode = p;
        return temp;
    }

    public TreeNode rotateSimpleRight(TreeNode p) {
        TreeNode temp = p.lNode;
        p.lNode = temp.rNode;
        temp.rNode = p;
        return temp;
    }

    public TreeNode rotateDoubleLeft(TreeNode p) {
        TreeNode ret;

        p.rNode = rotateSimpleRight(p.rNode);
        ret = rotateSimpleLeft(p);
        return ret;
    }

    public TreeNode rotateDoubleRight(TreeNode p) {
        TreeNode ret;

        p.lNode = rotateSimpleLeft(p.lNode);
        ret = rotateSimpleRight(p);
        return ret;
    }

    private void setFailNode(TreeNode node, TreeNode pai, int lado) {
        if (node == null || balanceada) {
            return;
        }

        setFailNode(node.lNode, node, 0);
        setFailNode(node.rNode, node, 1);

        if (Math.abs(node.fb) > 1 && !balanceada) {
            TreeNode ret;

            ret = balance(node);

            if (node == raiz) {
                raiz = ret;
            } else if (lado == 0) {
                pai.lNode = ret;
            } else if (lado == 1) {
                pai.rNode = ret;
            }

            balanceada = true;
            return;
        }
    }

    private TreeNode balance(TreeNode p) {
        TreeNode ret;

        System.out.println(" >>>[" + p.fb + "][" + p.info + "][" + p
                + "]<<<");

        if (p.fb < 0) //toRight
        {
            if (p.lNode.fb < 0) {
                ret = rotateSimpleRight(p);
            } else {
                ret = rotateDoubleRight(p);
            }
        } else //toLeft
        {
            if (p.rNode.fb > 0) {
                ret = rotateSimpleLeft(p);
            } else {
                ret = rotateDoubleLeft(p);
            }
        }

        return ret;
    }
} // end of Tree class

