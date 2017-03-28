package puzzle;

/**
 *
 * @author eduardo
 */
public class Problem {
    
    // the puzzle's initial state
    private final byte[][] initialState;
    private final byte[][] goalState;

    private final int size;
    private int zeroColumn;
    private int zeroRow;
    
    public Problem(byte[][] initialState, int size){
        this.initialState = initialState;
        if (size < 3) {
            size = 3;
        }
        this.size = size;
        this.goalState = new byte[size][size];
        this.setGoalState();
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                if (initialState[i][j] == 0) {
                    this.zeroRow = i;
                    this.zeroColumn = j;
                }
            }
        }
    }//end constructor
    
    private void findZeroCoordinates(byte[][] state){
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                if (state[i][j] == 0) {
                    this.zeroRow = i;
                    this.zeroColumn = j;
                }
            }
        }
    }//end findZeroCoordinates
    
    public boolean goalTest(byte[][] possibleState){
        for (int i = 0; i < possibleState.length; i++) {
            for (int j = 0; j < possibleState.length; j++) {
                if (possibleState[i][j] != this.goalState[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }//end goalTest
    
    private void setGoalState(){
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                goalState[i][j] = (byte) ((i * size + j)+1);
            }
        }
        goalState[size-1][size-1] = 0;
    }//end setGoalState
    
    public String getGoalStateRep() {
        StringBuilder value = new StringBuilder();
        for (byte[] row : goalState) {
            for (byte cell : row) {
                value.append(cell);
                value.append(',');
            }
        }
        value.deleteCharAt(value.length()-1);
        return value.toString();
    }//end toString

    public Node moveLeft(Node node){
        byte[][] currentState = node.getState();
        findZeroCoordinates(currentState);
        if (zeroColumn > 0) {
            byte temp = currentState[zeroRow][zeroColumn - 1];
            currentState[zeroRow][zeroColumn - 1] = 0;
            currentState[zeroRow][zeroColumn] = temp;
            zeroColumn -= 1;
            Node nextNode = new Node();
            nextNode.setAction(Actions.LEFT);
            nextNode.setState(currentState);
            return nextNode;
        }
        return null;
    }//end moveLeft
    
    public Node moveRight(Node node){
        byte[][] currentState = node.getState();
        findZeroCoordinates(currentState);
        if (zeroColumn < size - 1) {
            byte temp = currentState[zeroRow][zeroColumn + 1];
            currentState[zeroRow][zeroColumn + 1] = 0;
            currentState[zeroRow][zeroColumn] = temp;
            zeroColumn += 1;
            Node nextNode = new Node();
            nextNode.setAction(Actions.RIGHT);
            nextNode.setState(currentState);
            return nextNode;
        }
        return null;
    }//end moveRight
    
    public Node moveUp(Node node){
        byte[][] currentState = node.getState();
        findZeroCoordinates(currentState);
        if (zeroRow > 0) {
            byte temp = currentState[zeroRow - 1][zeroColumn];
            currentState[zeroRow - 1][zeroColumn] = 0;
            currentState[zeroRow][zeroColumn] = temp;
            zeroRow -= 1;
            Node nextNode = new Node();
            nextNode.setAction(Actions.UP);
            nextNode.setState(currentState);
            return nextNode;
        }
        return null;
    }//end moveUp
    
    public Node moveDown(Node node){
        byte[][] currentState = node.getState();
        findZeroCoordinates(currentState);
        if (zeroRow < size - 1) {
            byte temp = currentState[zeroRow + 1][zeroColumn];
            currentState[zeroRow + 1][zeroColumn] = 0;
            currentState[zeroRow][zeroColumn] = temp;
            zeroRow += 1;   
            Node nextNode = new Node();
            nextNode.setAction(Actions.DOWN);
            nextNode.setState(currentState);
            return nextNode;
        }
        return null;
    }//end moveDown

    public byte[][] getInitialState() {
        return initialState;
    }//end getInitialState
    
    public byte[][] getGoalState(){
        return goalState;
    }//end getGoalState
    
    public String getInitialStateRep(){
        StringBuilder value = new StringBuilder();
        for (byte[] row : initialState) {
            for (byte cell : row) {
                value.append(cell);
                value.append(',');
            }
        }
        value.deleteCharAt(value.length()-1);
        return value.toString();
    }//end getInitialStateRep
    
    public int getSize(){
        return this.size;
    }//end getSize
    
}//end class