package algorithms;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
import puzzle.Node;
import puzzle.Problem;

/**
 *
 * @author eduardo
 */
public class AStar extends Algorithm{
    
    private final PriorityQueue<Node> frontier;
    
    public AStar(Problem problem){
        frontier = new PriorityQueue();
        explored = new HashSet();
        path = new Stack();
        instructionSet = new Stack();
        this.problem = problem;
    }//end constructor
    
    
    public void searchWithMissplacedTiles(){
        
        long startTime = System.currentTimeMillis();

        Node node = new Node();
        node.setState(problem.getInitialState());
        node.setDepth(0);
        node.setHeuristic(calculeMissplacedTiles(node));
        
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
            if (nextNode!=null && !explored.contains(nextNode) && !frontier.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeMissplacedTiles(nextNode));
                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveUp(node);
            if (nextNode!=null && !explored.contains(nextNode) && !frontier.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeMissplacedTiles(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveRight(node);
            if (nextNode!=null && !explored.contains(nextNode) && !frontier.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeMissplacedTiles(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveDown(node);
            if (nextNode!=null && !explored.contains(nextNode) && !frontier.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeMissplacedTiles(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
        }
        if (this.frontier.isEmpty()) {
            System.out.println("SOLUTION NOT FOUND");
        }
        timeElapsed = System.currentTimeMillis() - startTime;
        memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

    }//end searchWithMissplacedTiles
    
    public void searchWithManhattanDistance(){
        
        long startTime = System.currentTimeMillis();

        Node node = new Node();
        node.setState(problem.getInitialState());
        node.setDepth(0);
        node.setHeuristic(calculeManhattanDistance(node));
        
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
                System.out.println(node.getStateRep());
                break;
            }
            
            nextNode = problem.moveLeft(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeManhattanDistance(nextNode));
                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveUp(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeManhattanDistance(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveRight(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeManhattanDistance(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveDown(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeManhattanDistance(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
        }
        if (this.frontier.isEmpty()) {
            System.out.println("SOLUTION NOT FOUND");
        }
        timeElapsed = System.currentTimeMillis() - startTime;
        memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

    }//end searchWithManhattanDistance

    public int calculeManhattanDistance(Node node){
        int manhattanDistance = 0;
        for (int i = 0; i < problem.getSize(); i++) {
            for (int j = 0; j < problem.getSize(); j++) {
                if (node.getState()[i][j] != problem.getGoalState()[i][j]) {
                    manhattanDistance += sumDistance(node, problem.getGoalState()[i][j], i, j);
                }
            }
        }
        return manhattanDistance;
    }//end calculeManhattanDistance
    
    private int sumDistance(Node node, int tile, int correctRow, int correctColumn){
        int distance = 0;
        int missplacedRow = 0;
        int missplacedColumn = 0;
        
        outerloop:
        for (int i = 0; i < problem.getSize(); i++) {
            for (int j = 0; j < problem.getSize(); j++) {
                if (node.getState()[i][j] == tile) {
                    missplacedRow = i;
                    missplacedColumn = j;
                    break outerloop;
                }
            }
        }
        
        if (tile != 0) {
            if (missplacedRow < correctRow) {
                while (missplacedRow <= correctRow) {                
                    if (missplacedRow == correctRow) {
                        break;
                    }
                    missplacedRow++;
                    distance++;
                }
            } else if(missplacedRow > correctRow) {
                while (missplacedRow >= correctRow) {                
                    if (missplacedRow == correctRow) {
                        break;
                    }
                    missplacedRow--;
                    distance++;
                }
            }

            if (missplacedColumn < correctColumn) {
                while (missplacedColumn <= correctColumn) {                
                    if (missplacedColumn == correctColumn) {
                        break;
                    }
                    missplacedColumn++;
                    distance++;
                }
            } else if (missplacedColumn > correctColumn) {
                while (missplacedColumn >= correctColumn) {                
                    if (missplacedColumn == correctColumn) {
                        break;
                    }
                    missplacedColumn--;
                    distance++;
                }
            }
        }
        return distance;
    }//end sumDistance
    
    public int calculeMissplacedTiles(Node node){
        int missplacedTiles = 0;
        for (int i = 0; i < problem.getSize(); i++) {
            for (int j = 0; j < problem.getSize(); j++) {
                if (problem.getGoalState()[i][j] != 0) {
                    if (problem.getGoalState()[i][j] != node.getState()[i][j]) {
                    missplacedTiles += 1;
                    }  
                }
            }
        }
        return missplacedTiles;
    }//end calculeMissplacedTiles
    
    public int getFrontierSize(){
        return this.frontier.size();
    }//end getFrontierSize
    
}//end class AStar