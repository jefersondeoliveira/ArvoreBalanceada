/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebalanceada;

/**
 *
 * @author Jeferson
 */
public class ArvoreBalanceada {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tree t = new Tree();
        t.insereNode(10);
        t.insereNode(20);
        t.insereNode(8);
        t.insereNode(5);
        t.insereNode(2);
        t.calculateFB(1);
        
    }
}
