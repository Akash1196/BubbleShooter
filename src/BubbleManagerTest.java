/**
 * Akash Patel
 * CS 251L
 * Project: Bubble Shooter (Part 1): BubbleManagerTest.java
 *
 * This is the testing class modeled relatively close to the example output provided
 * The seed for the random number generator is different than the example therefore,
 * the "bubbles" at the locations are different but this testing class accurately
 * shows all the required functionality of the BubbleManager class.
 */
public class BubbleManagerTest {
    public static void main(String[] args){
        //First bubble manager
        System.out.print("Test first bubble manager:\n" +
                "Initialize bubble manager with 7 rows, 10 cols, 3 bubble types\n");

        BubbleManager bm1 = new BubbleManager();
        bm1.setNumRows(7);
        bm1.setNumCols(10);
        bm1.setNumBubbleTypes(3);
        bm1.createBoard();
        System.out.print(bm1.getStringBoard() + "\n");

        bm1.addBubble(7, 8);
        System.out.print("add new bubble at (7, 8)\n" + bm1.getStringBoard() + "\n");

        bm1.findCluster(7, 8, true);
        System.out.print(bm1.getClusterCount() + " bubbles match.\n");
        bm1.removeMatchingCluster();
        System.out.print("Remove matching bubbles\n" + bm1.getStringBoard() + "\n");

        bm1.findAndRemoveFloatingCluster();
        System.out.print(bm1.getFloatingClusterCount() + " floating bubbles.\n");
        System.out.print("Remove floating bubbles\n" + bm1.getStringBoard() + "\n");

        bm1.addBubble(2, 9);
        System.out.print("add new bubble at (2, 9)\n" + bm1.getStringBoard() + "\n");

        bm1.findCluster(2, 9, true);
        System.out.print(bm1.getClusterCount() + " bubbles match.\n");
        bm1.removeMatchingCluster();
        System.out.print("Remove matching bubbles\n" + bm1.getStringBoard() + "\n");

        bm1.findAndRemoveFloatingCluster();
        System.out.print(bm1.getFloatingClusterCount() + " floating bubbles.\n");
        System.out.print("Remove floating bubbles\n" + bm1.getStringBoard() + "\n");


        //Second bubble manager
        System.out.print("Test second bubble manager:\n" +
                "Initialize bubble manager with 6 rows, 6 cols, 5 bubble types\n");

        BubbleManager bm2 = new BubbleManager();
        bm2.setNumRows(6);
        bm2.setNumCols(6);
        bm2.setNumBubbleTypes(5);
        bm2.createBoard();
        System.out.print(bm2.getStringBoard() + "\n");

        bm2.addBubble(6, 4);
        System.out.print("add new bubble at (6, 4)\n" + bm2.getStringBoard() + "\n");

        bm2.findCluster(6, 4, true);
        System.out.print(bm2.getClusterCount() + " bubbles match.\n");
        bm2.removeMatchingCluster();
        System.out.print("Remove matching bubbles\n" + bm2.getStringBoard() + "\n");

        bm2.addBubble(6, 5);
        System.out.print("add new bubble at (6, 5)\n" + bm2.getStringBoard() + "\n");

        bm2.findCluster(6, 5, true);
        System.out.print(bm2.getClusterCount() + " bubbles match.\n");
        bm2.removeMatchingCluster();
        System.out.print("Remove matching bubbles\n" + bm2.getStringBoard() + "\n");

        bm2.findAndRemoveFloatingCluster();
        System.out.print(bm2.getFloatingClusterCount() + " floating bubbles.\n");
        System.out.print("Remove floating bubbles\n" + bm2.getStringBoard() + "\n");

        bm2.addBubble(5, 3);
        System.out.print("add new bubble at (5, 3)\n" + bm2.getStringBoard() + "\n");

        bm2.findCluster(5, 3, true);
        System.out.print(bm2.getClusterCount() + " bubbles match.\n");
        bm2.removeMatchingCluster();
        System.out.print("Remove matching bubbles\n" + bm2.getStringBoard() + "\n");

        bm2.findAndRemoveFloatingCluster();
        System.out.print(bm2.getFloatingClusterCount() + " floating bubbles.\n");
        System.out.print("Remove floating bubbles\n" + bm2.getStringBoard() + "\n");


        //Go back to testing the first bubble manager
        System.out.print("Go back to the first manager and keep testing\n" + bm1.getStringBoard() + "\n");

        bm1.addBubble(7, 1);
        System.out.print("add new bubble at (7, 1)\n" + bm1.getStringBoard() + "\n");

        bm1.findCluster(7, 1, true);
        System.out.print(bm1.getClusterCount() + " bubbles match.\n");
        bm1.removeMatchingCluster();
        System.out.print("Remove matching bubbles\n" + bm1.getStringBoard() + "\n");

        bm1.addBubble(7, 0);
        System.out.print("add new bubble at (7, 0)\n" + bm1.getStringBoard() + "\n");

        bm1.findCluster(7, 0, true);
        System.out.print(bm1.getClusterCount() + " bubbles match.\n");
        bm1.removeMatchingCluster();
        System.out.print("Remove matching bubbles\n" + bm1.getStringBoard() + "\n");

        bm1.findAndRemoveFloatingCluster();
        System.out.print(bm1.getFloatingClusterCount() + " floating bubbles.\n");
        System.out.print("Remove floating bubbles\n" + bm1.getStringBoard() + "\n");
    }
}