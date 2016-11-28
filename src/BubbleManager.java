import java.util.*;
/**
 * Akash Patel
 * CS 251L
 * Project: Bubble Shooter (Part 1): BubbleManager.java
 *
 * This class doesn't output anything. The testing is left to the BubbleManagerTest.java class.
 * This class implements various methods to add, remove, and detect "random bubbles"
 * in the console. This class is the logic behind the BubbleShooter game.
 */
public class BubbleManager {
    private List<List<String>> grid;
    private List<String> row;
    private List<Integer> x;
    private List<Integer> y;
    private Boolean[][] markVisited;
    private StringBuilder sb;
    private Random rand;
    private int matchingClusterCount;
    private int floatingClusterCount;
    private int rNum;
    private int numRows;
    private int numCols;
    private int numBubbleTypes;

    public BubbleManager() {
        rand = new Random();
        rand.setSeed(2);
    }

    /**
     * Creates a new board using a list of lists
     */
    public void createBoard(){
        grid = new ArrayList<List<String>>();

        for(int i = 0; i < numRows; i++){
            row = new ArrayList<String>();

            for(int j = 0; j < numCols; j++){
                rNum = random(numBubbleTypes);
                row.add(getBubbleType() + " ");
            }
            grid.add(row);
        }

        updateStringBoard();
    }

    /**
     * Adds a bubble to the designated row and column of the list of lists
     */
    public void addBubble(int r, int c){
        rNum = random(numBubbleTypes);

        if(r >= numRows){
            numRows = r + 1;
            grid.add(new ArrayList<String>());
        }

        if(grid.get(r).isEmpty()) {
            for(int k = 0; k < numCols; k++){
                grid.get(r).add(k, "  ");
            }
        }
        grid.get(r).set(c, getBubbleType() + " ");

        updateStringBoard();
    }

    /**
     * Takes 3 parameters: r and c are the starting locations for the recursion
     *                     matchType is the value that determines if matching or non-matching
     *                     cluster should be found
     *
     * Adds the row and column locations of the bubbles in a cluster to the x and y lists
     */
    public void findCluster(int r, int c, Boolean matchType){
        // check left
        if(c - 1 >= 0) {
            if (grid.get(r).get(c - 1) != null && !markVisited[r][c - 1]) { // test if target cell is not visited
                markVisited[r][c - 1] = true; // mark the current node as visited
                if(matchType){
                    if (grid.get(r).get(c - 1).equals(grid.get(r).get(c))) {
                        x.add(r);
                        y.add(c - 1);
                        matchingClusterCount++;
                        findCluster(r, c - 1, true);
                    }
                }
                else {
                    if (!grid.get(r).get(c - 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r);
                        y.add(c - 1);
                        findCluster(r, c - 1, false);
                    }
                }
            }
        }
        // check right
        if(c + 1 < numCols) {
            if (grid.get(r).get(c + 1) != null && !markVisited[r][c + 1]) { // test if target cell is not visited
                markVisited[r][c + 1] = true; // mark the current node as visited
                if(matchType){
                    if (grid.get(r).get(c + 1).equals(grid.get(r).get(c))) {
                        x.add(r);
                        y.add(c + 1);
                        matchingClusterCount++;
                        findCluster(r, c + 1, true);
                    }
                }
                else {
                    if (!grid.get(r).get(c + 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r);
                        y.add(c + 1);
                        findCluster(r, c + 1, false);
                    }
                }
            }
        }
        // check if row is indented or not
        if(r % 2 == 1) {
            // check lone bubble
            if(!matchType) {
                if (r - 1 >= 0 && c + 1 < numCols && c - 1 >= 0) {
                    if (grid.get(r - 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ") && grid.get(r - 1).get(c + 1).equals("  ")
                            && grid.get(r).get(c - 1).equals("  ") && grid.get(r).get(c + 1).equals("  ")) {
                        x.add(r);
                        y.add(c);
                    }
                }
            }
            // check top left
            if(r - 1 >= 0) {
                if (grid.get(r - 1).get(c) != null && !markVisited[r - 1][c]) { // test if target cell is not visited
                    markVisited[r - 1][c] = true; // mark the current node as visited
                    if(matchType){
                        if (grid.get(r - 1).get(c).equals(grid.get(r).get(c))) {
                            x.add(r - 1);
                            y.add(c);
                            matchingClusterCount++;
                            findCluster(r - 1, c, true);
                        }
                    }
                    else {
                        if (!grid.get(r - 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                            x.add(r - 1);
                            y.add(c);
                            findCluster(r - 1, c, false);
                        }
                    }
                }
            }
            // check top right
            if(r - 1 >= 0 && c + 1 < numCols) {
                if (grid.get(r - 1).get(c + 1) != null && !markVisited[r - 1][c + 1]) { // test if target cell is not visited
                    markVisited[r - 1][c + 1] = true; // mark the current node as visited
                    if(matchType){
                        if (grid.get(r - 1).get(c + 1).equals(grid.get(r).get(c))) {
                            x.add(r - 1);
                            y.add(c + 1);
                            matchingClusterCount++;
                            findCluster(r - 1, c + 1, true);
                        }
                    }
                    else {
                        if (!grid.get(r - 1).get(c + 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                            x.add(r - 1);
                            y.add(c + 1);
                            findCluster(r - 1, c + 1, false);
                        }
                    }
                }
            }
            // check bottom left
            if(r + 1 < numRows) {
                if (grid.get(r + 1).get(c) != null && !markVisited[r + 1][c]) { // test if target cell is not visited
                    markVisited[r + 1][c] = true; // mark the current node as visited
                    if(matchType){
                        if (grid.get(r + 1).get(c).equals(grid.get(r).get(c))) {
                            x.add(r + 1);
                            y.add(c);
                            matchingClusterCount++;
                            findCluster(r + 1, c, true);
                        }
                    }
                    else {
                        if (!grid.get(r + 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                            x.add(r + 1);
                            y.add(c);
                            findCluster(r + 1, c, false);
                        }
                    }
                }
            }
            // check bottom right
            if(r + 1 < numRows && c + 1 < numCols) {
                if (grid.get(r + 1).get(c + 1) != null && !markVisited[r + 1][c + 1]) { // test if target cell is not visited
                    markVisited[r + 1][c + 1] = true; // mark the current node as visited
                    if(matchType){
                        if (grid.get(r + 1).get(c + 1).equals(grid.get(r).get(c))) {
                            x.add(r + 1);
                            y.add(c + 1);
                            matchingClusterCount++;
                            findCluster(r + 1, c + 1, true);
                        }
                    }
                    else {
                        if (!grid.get(r + 1).get(c + 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                            x.add(r + 1);
                            y.add(c + 1);
                            findCluster(r + 1, c + 1, false);
                        }
                    }
                }
            }
        }
        else{
            // check lone bubble
            if(!matchType) {
                if (r - 1 >= 0 && c + 1 < numCols && c - 1 >= 0) {
                    if (grid.get(r - 1).get(c - 1).equals("  ") && !grid.get(r).get(c).equals("  ") && grid.get(r - 1).get(c).equals("  ")
                            && grid.get(r).get(c - 1).equals("  ") && grid.get(r).get(c + 1).equals("  ")) {
                        x.add(r);
                        y.add(c);
                    }
                }
            }
            // check top left
            if(r - 1 >= 0 && c - 1 >= 0) {
                if (grid.get(r - 1).get(c - 1) != null && !markVisited[r - 1][c - 1]) { // test if target cell is not visited
                    markVisited[r - 1][c - 1] = true; // mark the current node as visited
                    if(matchType){
                        if (grid.get(r - 1).get(c - 1).equals(grid.get(r).get(c))) {
                            x.add(r - 1);
                            y.add(c - 1);
                            matchingClusterCount++;
                            findCluster(r - 1, c - 1, true);
                        }
                    }
                    else {
                        if (!grid.get(r - 1).get(c - 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                            x.add(r - 1);
                            y.add(c - 1);
                            findCluster(r - 1, c - 1, false);
                        }
                    }
                }
            }
            // check top right
            if(r - 1 >= 0) {
                if (grid.get(r - 1).get(c) != null && !markVisited[r - 1][c]) { // test if target cell is not visited
                    markVisited[r - 1][c] = true; // mark the current node as visited
                    if(matchType){
                        if (grid.get(r - 1).get(c).equals(grid.get(r).get(c))) {
                            x.add(r - 1);
                            y.add(c);
                            matchingClusterCount++;
                            findCluster(r - 1, c, true);
                        }
                    }
                    else {
                        if (!grid.get(r - 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                            x.add(r - 1);
                            y.add(c);
                            findCluster(r - 1, c, false);
                        }
                    }
                }
            }
            // check bottom left
            if(r + 1 < numRows && c - 1 >= 0) {
                if (grid.get(r + 1).get(c - 1) != null && !markVisited[r + 1][c - 1]) { // test if target cell is not visited
                    markVisited[r + 1][c - 1] = true; // mark the current node as visited
                    if(matchType){
                        if (grid.get(r + 1).get(c - 1).equals(grid.get(r).get(c))) {
                            x.add(r + 1);
                            y.add(c - 1);
                            matchingClusterCount++;
                            findCluster(r + 1, c - 1, true);
                        }
                    }
                    else {
                        if (!grid.get(r + 1).get(c - 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                            x.add(r + 1);
                            y.add(c - 1);
                            findCluster(r + 1, c - 1, false);
                        }
                    }
                }
            }
            // check bottom right
            if(r + 1 < numRows) {
                if (grid.get(r + 1).get(c) != null && !markVisited[r + 1][c]) { // test if target cell is not visited
                    markVisited[r + 1][c] = true; // mark the current node as visited
                    if(matchType){
                        if (grid.get(r + 1).get(c).equals(grid.get(r).get(c))) {
                            x.add(r + 1);
                            y.add(c);
                            matchingClusterCount++;
                            findCluster(r + 1, c, true);
                        }
                    }
                    else {
                        if (!grid.get(r + 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                            x.add(r + 1);
                            y.add(c);
                            findCluster(r + 1, c, false);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is paired with the findMatchingCluster method.
     * It removes any cluster that have 3 or more bubbles in it then
     * it updates the number of rows if necessary
     */
    public void removeMatchingCluster(){
        if(matchingClusterCount >= 3) {
            for (int i = 0; i < x.size(); i++) {
                grid.get(x.get(i)).set(y.get(i), "  ");
            }
            removeEmptyRows();
        }

        updateStringBoard();
    }

    /**
     * Loops through the (x, y) coordinate lists and checks if the clusters are connected
     * to the "ceiling" if they are do nothing, if they are floating then remove them.
     */
    public void removeFloatingCluster(){
        Boolean floating = true;
        floatingClusterCount = 0;

        for (int i = 0; i < x.size(); i++) {
            if (x.get(i) == 0) {
                floating = false;
            }
        }

        if (floating) {
            for (int j = 0; j < x.size(); j++) {
                floatingClusterCount++;
                grid.get(x.get(j)).set(y.get(j), "  ");
            }
            removeEmptyRows();
        }

        updateStringBoard();
    }

    /**
     * Finds all possible floating clusters and removes them
     */
    public void findAndRemoveFloatingCluster(){
        int clusterCount = 0;

        for(int i = 0; i < numCols; i++) {
            findCluster(numRows - 1, i, false);
            removeFloatingCluster();
            clusterCount += floatingClusterCount;
        }
        for(int j = 0; j < numRows; j++) {
            findCluster(j, 0, false);
            removeFloatingCluster();
            clusterCount += floatingClusterCount;
        }

        floatingClusterCount = clusterCount;
    }

    private void removeEmptyRows(){
        int count;

        // if a row is empty then remove it from the grid and update instance variable
        for(int j = 0; j < numRows; j ++){
            count = 0;
            for(int k = 0; k < numCols; k++){
                if(grid.get(j).get(k).equals("  ")){
                    count++;
                    if(count == numCols){
                        grid.remove(j);
                        numRows--;
                        break;
                    }
                }
            }
        }
    }

    private void resetVisitedToFalse(){
        markVisited = new Boolean[numRows][numCols];

        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                markVisited[i][j] = false;
            }
        }
    }

    private void updateStringBoard() {
        StringBuilder localSB = new StringBuilder();
        resetVisitedToFalse();
        x = new ArrayList<>();
        y = new ArrayList<>();
        matchingClusterCount = 0;

        for (int i = 0; i < numRows; i++) {
            if (i % 2 == 1) {
                localSB.append(" ");
            }
            for (int j = 0; j < numCols; j++) {
                localSB.append(grid.get(i).get(j));
            }
            localSB.append("\n");
        }

        sb = localSB;
    }

    @Override
    public String toString() {
        return "BubbleManager{" +
                "grid=" + grid +
                ", row=" + row +
                ", sb=" + sb +
                ", rand=" + rand +
                ", rNum=" + rNum +
                ", numRows=" + numRows +
                ", numCols=" + numCols +
                ", numBubbleTypes=" + numBubbleTypes +
                '}';
    }

    private int random(int i)
    {
        return rand.nextInt(i);
    }

    private String getBubbleType(){
        if(rNum == 0){
            return "+";
        }
        else if(rNum == 1){
            return ".";
        }
        else if(rNum == 2){
            return "*";
        }
        else if(rNum == 3){
            return "@";
        }
        else if(rNum == 4){
            return "$";
        }
        else{
            return " "; // This statement should never be reached
        }
    }

    public String getStringBoard(){
        return sb.toString();
    }

    public int getClusterCount() {
        return matchingClusterCount;
    }

    public int getFloatingClusterCount() {
        return floatingClusterCount;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public void setNumBubbleTypes(int numBubbleTypes) {
        this.numBubbleTypes = numBubbleTypes;
    }
}