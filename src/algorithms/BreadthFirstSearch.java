package algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import puzzle.Node;
import puzzle.Problem;

/**
 *
 * @author eduardo
 */
public class BreadthFirstSearch extends Algorithm{
            
    protected final Queue<Node> frontier;
    
    public BreadthFirstSearch(Problem problem){
        frontier = new LinkedList();
        explored = new HashSet();
        path = new Stack();
        instructionSet = new Stack();
        this.problem = problem;
    }//end constructor
    
    public void search(){
        
        long startTime = System.currentTimeMillis();

        Node node = new Node();
        node.setState(problem.getInitialState());
        node.setDepth(0);
        
        Node nextNode;
        frontier.add(node);
        
        while (!frontier.isEmpty()) {

            node = frontier.poll();                
            explored.add(node);

            if (problem.goalTest(node.getState())) {
                feedPath(node);
                solutionNodeDepth = node.getDepth();
                numberOfMoves = path.size()-1;
                System.out.println("SOLUTION FOUND");
                break;
            }
            
            nextNode = problem.moveLeft(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveUp(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveRight(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveDown(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                frontier.add(nextNode);
                explored.add(nextNode);
            }
        }
        if (this.frontier.isEmpty()) {
            System.out.println("SOLUTION NOT FOUND");
        }
        timeElapsed = System.currentTimeMillis() - startTime;
        memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }//end search

    public int getFrontierSize(){
        return this.frontier.size();
    }//end getFrontierSize

}//end class BreadthFirstSearch