package algorithms;

import java.util.HashSet;
import java.util.Stack;
import puzzle.Node;
import puzzle.Problem;

/**
 *
 * @author eduardo
 */
public abstract class Algorithm {
    
    protected HashSet explored;
    protected Stack path;
    protected Stack instructionSet;
    protected Problem problem;
    protected int numberOfMoves;
    protected int solutionNodeDepth;
    protected long timeElapsed;
    protected long memoryUsed;
    protected long memoryTotal;
    
    protected void feedPath(Node node){
        path.add(node.getAction());
        if (node.getParent()!=null) {
            feedPath(node.getParent());
        }
    }//end feedPath
    
    public Stack getSolution(){
        try {
            this.path.pop();
            while (!path.isEmpty()) {            
                instructionSet.add(path.pop());
            }
        } catch (Exception e) {
        }
        return this.instructionSet;
    }//end getSolution
    
    
    public int getNumberOfMoves(){
        return this.numberOfMoves;
    }//end getNumberOfMoves
    
    public int getSolutionNodeDepth(){
        return this.solutionNodeDepth;
    }//end getSolutionNodeDepth

    public long getTimeElapsed() {
        return timeElapsed;
    }//end getTimeElapsed

    public long getMemoryUsed() {
        return memoryUsed;
    }//end getMemoryUsed
    
    public int getExplorados(){
        return this.explored.size();
    }//end getExplorados

    public long getMemoryTotal() {
        return memoryTotal;
    }//end getMemoryTotal
    
}//end class