/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebalanceada;

/**
 *
 * @author Jeferson
 */
public class TreeNode {

    TreeNode lNode;
    int info;
    TreeNode rNode;
    int altura;
    int fb;

    public TreeNode(int nodeInfo) {
        info = nodeInfo;
        lNode = rNode = null;
    }

    public void insereNode(int insertValue) {
        if (insertValue < info) {
            if (lNode == null) {
                lNode = new TreeNode(insertValue);
            } else {
                lNode.insereNode(insertValue);
            }
        } else if (insertValue > info) {
            if (rNode == null) {
                rNode = new TreeNode(insertValue);
            } else {
                rNode.insereNode(insertValue);
            }
        }
    }

    public void setFB(int fatbal) {
        fb = fatbal;
    }

    public int getFB() {
        return fb;
    }
} //end of TreeNode class

