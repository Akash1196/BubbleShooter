import java.util.*;
/**
 * Akash Patel
 * CS 251L
 * Project: Bubble Shooter (Part 1): BubbleManager.java
 */
public class BubbleManager {
    private List<List<String>> grid;
    private List<String> row;
    private List<Integer> x;
    private List<Integer> y;
    private Boolean[][] markVisited;
    private StringBuilder sb;
    private Random rand;
    private Boolean floating;
    private int matchingClusterCount;
    private int floatingClusterCount;
    private int rNum;
    private int numRows;
    private int numCols;
    private int numBubbleTypes;

    public BubbleManager() {
        rand = new Random();
        rand.setSeed(2);
        resetVisitedToFalse();
    }


    public void createBoard(){
        grid = new ArrayList<List<String>>();

        for(int i = 0; i < numRows; i++){
            row = new ArrayList<String>();

            for(int j = 0; j < numCols; j++){
                setrNum(random(numBubbleTypes));
                row.add(getBubbleType() + " ");
            }
            grid.add(row);
        }

        updateStringBoard();
    }


    public void addBubble(int r, int c){
        setrNum(random(numBubbleTypes));

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

    public void findMatchingCluster(int r, int c){
        // check left
        if(c - 1 >= 0) {
            if (grid.get(r).get(c - 1) != null && !markVisited[r][c - 1]) { // test if target cell is not visited
                markVisited[r][c - 1] = true; // mark the current node as visited
                if (grid.get(r).get(c - 1).equals(grid.get(r).get(c))) {
                    x.add(r);
                    y.add(c - 1);
                    matchingClusterCount++;
                    findMatchingCluster(r, c - 1);
                }
            }
        }
        // check right
        if(c + 1 < numCols) {
            if (grid.get(r).get(c + 1) != null && !markVisited[r][c + 1]) { // test if target cell is not visited
                markVisited[r][c + 1] = true; // mark the current node as visited
                if (grid.get(r).get(c + 1).equals(grid.get(r).get(c))) {
                    x.add(r);
                    y.add(c + 1);
                    matchingClusterCount++;
                    findMatchingCluster(r, c + 1);
                }
            }
        }
        // check if row is indented or not
        if(r % 2 == 1) {
            // check top left
            if(r - 1 >= 0) {
                if (grid.get(r - 1).get(c) != null && !markVisited[r - 1][c]) { // test if target cell is not visited
                    markVisited[r - 1][c] = true; // mark the current node as visited
                    if (grid.get(r - 1).get(c).equals(grid.get(r).get(c))) {
                        x.add(r - 1);
                        y.add(c);
                        matchingClusterCount++;
                        findMatchingCluster(r - 1, c);
                    }
                }
            }
            // check top right
            if(r - 1 >= 0 && c + 1 < numCols) {
                if (grid.get(r - 1).get(c + 1) != null && !markVisited[r - 1][c + 1]) { // test if target cell is not visited
                    markVisited[r - 1][c + 1] = true; // mark the current node as visited
                    if (grid.get(r - 1).get(c + 1).equals(grid.get(r).get(c))) {
                        x.add(r - 1);
                        y.add(c + 1);
                        matchingClusterCount++;
                        findMatchingCluster(r - 1, c + 1);
                    }
                }
            }
            // check bottom left
            if(r + 1 < numRows) {
                if (grid.get(r + 1).get(c) != null && !markVisited[r + 1][c]) { // test if target cell is not visited
                    markVisited[r + 1][c] = true; // mark the current node as visited
                    if (grid.get(r + 1).get(c).equals(grid.get(r).get(c))) {
                        x.add(r + 1);
                        y.add(c);
                        matchingClusterCount++;
                        findMatchingCluster(r + 1, c);
                    }
                }
            }
            // check bottom right
            if(r + 1 < numRows && c + 1 < numCols) {
                if (grid.get(r + 1).get(c + 1) != null && !markVisited[r + 1][c + 1]) { // test if target cell is not visited
                    markVisited[r + 1][c + 1] = true; // mark the current node as visited
                    if (grid.get(r + 1).get(c + 1).equals(grid.get(r).get(c))) {
                        x.add(r + 1);
                        y.add(c + 1);
                        matchingClusterCount++;
                        findMatchingCluster(r + 1, c + 1);
                    }
                }
            }
        }
        else{
            // check top left
            if(r - 1 >= 0 && c - 1 >= 0) {
                if (grid.get(r - 1).get(c - 1) != null && !markVisited[r - 1][c - 1]) { // test if target cell is not visited
                    markVisited[r - 1][c - 1] = true; // mark the current node as visited
                    if (grid.get(r - 1).get(c - 1).equals(grid.get(r).get(c))) {
                        x.add(r - 1);
                        y.add(c - 1);
                        matchingClusterCount++;
                        findMatchingCluster(r - 1, c - 1);
                    }
                }
            }
            // check top right
            if(r - 1 >= 0) {
                if (grid.get(r - 1).get(c) != null && !markVisited[r - 1][c]) { // test if target cell is not visited
                    markVisited[r - 1][c] = true; // mark the current node as visited
                    if (grid.get(r - 1).get(c).equals(grid.get(r).get(c))) {
                        x.add(r - 1);
                        y.add(c);
                        matchingClusterCount++;
                        findMatchingCluster(r - 1, c);
                    }
                }
            }
            // check bottom left
            if(r + 1 < numRows && c - 1 >= 0) {
                if (grid.get(r + 1).get(c - 1) != null && !markVisited[r + 1][c - 1]) { // test if target cell is not visited
                    markVisited[r + 1][c - 1] = true; // mark the current node as visited
                    if (grid.get(r + 1).get(c - 1).equals(grid.get(r).get(c))) {
                        x.add(r + 1);
                        y.add(c - 1);
                        matchingClusterCount++;
                        findMatchingCluster(r + 1, c - 1);
                    }
                }
            }
            // check bottom right
            if(r + 1 < numRows) {
                if (grid.get(r + 1).get(c) != null && !markVisited[r + 1][c]) { // test if target cell is not visited
                    markVisited[r + 1][c] = true; // mark the current node as visited
                    if (grid.get(r + 1).get(c).equals(grid.get(r).get(c))) {
                        x.add(r + 1);
                        y.add(c);
                        matchingClusterCount++;
                        findMatchingCluster(r + 1, c);
                    }
                }
            }
        }
    }

    public void findGeneralCluster(int r, int c){
        // check left
        if(c - 1 >= 0) {
            if (grid.get(r).get(c - 1) != null && !markVisited[r][c - 1]) { // test if target cell is not visited
                markVisited[r][c - 1] = true; // mark the current node as visited
                if (!grid.get(r).get(c - 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                    x.add(r);
                    y.add(c - 1);
                    findGeneralCluster(r, c - 1);
                }
            }
        }
        // check right
        if(c + 1 < numCols) {
            if (grid.get(r).get(c + 1) != null && !markVisited[r][c + 1]) { // test if target cell is not visited
                markVisited[r][c + 1] = true; // mark the current node as visited
                if (!grid.get(r).get(c + 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                    x.add(r);
                    y.add(c + 1);
                    findGeneralCluster(r, c + 1);
                }
            }
        }
        // check if row is indented or not
        if(r % 2 == 1) {
            // check lone bubble
            if(r - 1 >= 0 && c + 1 < numCols && c - 1 >= 0) {
                if (grid.get(r - 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ") && grid.get(r - 1).get(c + 1).equals("  ")
                        && grid.get(r).get(c - 1).equals("  ") && grid.get(r).get(c + 1).equals("  ")) {
                    x.add(r);
                    y.add(c);
                }
            }
            // check top left
            if(r - 1 >= 0) {
                if (grid.get(r - 1).get(c) != null && !markVisited[r - 1][c]) { // test if target cell is not visited
                    markVisited[r - 1][c] = true; // mark the current node as visited
                    if (!grid.get(r - 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r - 1);
                        y.add(c);
                        findGeneralCluster(r - 1, c);
                    }
                }
            }
            // check top right
            if(r - 1 >= 0 && c + 1 < numCols) {
                if (grid.get(r - 1).get(c + 1) != null && !markVisited[r - 1][c + 1]) { // test if target cell is not visited
                    markVisited[r - 1][c + 1] = true; // mark the current node as visited
                    if (!grid.get(r - 1).get(c + 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r - 1);
                        y.add(c + 1);
                        findGeneralCluster(r - 1, c + 1);
                    }
                }
            }
            // check bottom left
            if(r + 1 < numRows) {
                if (grid.get(r + 1).get(c) != null && !markVisited[r + 1][c]) { // test if target cell is not visited
                    markVisited[r + 1][c] = true; // mark the current node as visited
                    if (!grid.get(r + 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r + 1);
                        y.add(c);
                        findGeneralCluster(r + 1, c);
                    }
                }
            }
            // check bottom right
            if(r + 1 < numRows && c + 1 < numCols) {
                if (grid.get(r + 1).get(c + 1) != null && !markVisited[r + 1][c + 1]) { // test if target cell is not visited
                    markVisited[r + 1][c + 1] = true; // mark the current node as visited
                    if (!grid.get(r + 1).get(c + 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r + 1);
                        y.add(c + 1);
                        findGeneralCluster(r + 1, c + 1);
                    }
                }
            }
        }
        else{
            // check lone bubble
            if(r - 1 >= 0 && c + 1 < numCols && c - 1 >= 0) {
                if (grid.get(r - 1).get(c - 1).equals("  ") && !grid.get(r).get(c).equals("  ") && grid.get(r - 1).get(c).equals("  ")
                        && grid.get(r).get(c - 1).equals("  ") && grid.get(r).get(c + 1).equals("  ")) {
                    x.add(r);
                    y.add(c);
                    }
            }
            // check top left
            if(r - 1 >= 0 && c - 1 >= 0) {
                if (grid.get(r - 1).get(c - 1) != null && !markVisited[r - 1][c - 1]) { // test if target cell is not visited
                    markVisited[r - 1][c - 1] = true; // mark the current node as visited
                    if (!grid.get(r - 1).get(c - 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r - 1);
                        y.add(c - 1);
                        findGeneralCluster(r - 1, c - 1);
                    }
                }
            }
            // check top right
            if(r - 1 >= 0) {
                if (grid.get(r - 1).get(c) != null && !markVisited[r - 1][c]) { // test if target cell is not visited
                    markVisited[r - 1][c] = true; // mark the current node as visited
                    if (!grid.get(r - 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r - 1);
                        y.add(c);
                        findGeneralCluster(r - 1, c);
                    }
                }
            }
            // check bottom left
            if(r + 1 < numRows && c - 1 >= 0) {
                if (grid.get(r + 1).get(c - 1) != null && !markVisited[r + 1][c - 1]) { // test if target cell is not visited
                    markVisited[r + 1][c - 1] = true; // mark the current node as visited
                    if (!grid.get(r + 1).get(c - 1).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r + 1);
                        y.add(c - 1);
                        findGeneralCluster(r + 1, c - 1);
                    }
                }
            }
            // check bottom right
            if(r + 1 < numRows) {
                if (grid.get(r + 1).get(c) != null && !markVisited[r + 1][c]) { // test if target cell is not visited
                    markVisited[r + 1][c] = true; // mark the current node as visited
                    if (!grid.get(r + 1).get(c).equals("  ") && !grid.get(r).get(c).equals("  ")) {
                        x.add(r + 1);
                        y.add(c);
                        findGeneralCluster(r + 1, c);
                    }
                }
            }
        }
    }

    public void findAndRemoveFloatingCluster(){
        int clusterCount = 0;

        for(int i = 0; i < numCols; i++) {
            findGeneralCluster(numRows - 1, i);
            removeFloatingCluster();
            clusterCount += floatingClusterCount;
        }
        for(int j = 0; j < numRows; j++) {
            findGeneralCluster(j, 0);
            removeFloatingCluster();
            clusterCount += floatingClusterCount;
        }

        floatingClusterCount = clusterCount;
    }

    public void removeCluster(){
        int count;

        if(matchingClusterCount >= 3) {
            for (int i = 0; i < x.size(); i++) {
                grid.get(x.get(i)).set(y.get(i), "  ");
            }
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

        updateStringBoard();
    }

    public void removeFloatingCluster(){
        floating = true;
        floatingClusterCount = 0;
        int count;

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

        //System.out.print("\nx: " + x + "\n");
        //System.out.print("y: " + y + "\n");

        updateStringBoard();
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

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    private void setrNum(int rNum) {
        this.rNum = rNum;
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