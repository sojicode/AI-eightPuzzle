
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Calculate the cost of the path and pick the lower one
 * Based on breadth first search algorithm(BFS).
 * 
 * F(n) = G(n)  
 * (G(n):Cost of getting to n, in this case, H(n) = 0)
 * 
 * Cost : value of the tile being moved
 * For example:  "134862705" 
 *    -------------       
 *    | 1 | 3 | 4 |               
 *    | 8 | 6 | 2 |       
 *    | 7 | 0 | 5 |      
 *    -------------      
 * 
 * Three options to move: UP, LEFT, RIGHT
 *    -------------       -------------      -------------
 *    | 1 | 3 | 4 |       | 1 | 3 | 4 |      | 1 | 3 | 4 |   
 *    | 8 | 0 | 2 |       | 8 | 6 | 2 |      | 8 | 6 | 2 | 
 *    | 7 | 6 | 5 |       | 0 | 7 | 5 |      | 7 | 5 | 0 |
 *    -------------       -------------      ------------- 
 *        UP                  LEFT               RIGHT
 *     COST = 6             COST = 7           COST = 5
 */

public class UniformCost {

	final static String EASY = "134862705";
	final static String MEDIUM = "281043765";
	final static String HARD = "567408321";
	final static String GOAL = "123804765";

	public String startState;
	public int maxSizeQueue;
	boolean isGoal = false;


	public void uniformCostSearch(String level) {

		startState = level;

		//create a set to save visited nodes(record history), get rid of duplicates
		Set<String> visitSet = new HashSet<>(); //get rid of duplicates

		int poppedNodes = 0;
		int totalCost = 0; //create variable to save total cost of movement, initial = 0

		Node nodeNow = new Node(startState); //create node with initial string

		nodeNow.setCost(totalCost); //initial cost = 0

		CostCompare costCompare = new CostCompare(); //compare to the cost of path

		/* create a priorityQueue with initial capacity and also orders elements
		   according to the costCompare comparator (less cost node is going to be the first element) */
		PriorityQueue<Node> priorityQnode = new PriorityQueue<>(3,costCompare); 


		while(!isGoal) { //if the node is goal, escape the while loop

			String nodeNowState = nodeNow.getState();

			if(nodeNowState.equals(GOAL)) {
				isGoal = true;

			} else {

				setMaxQSize(priorityQnode.size());

				visitSet.add(nodeNow.getState());

				List<String> nodeNextList = NodeNext.getNextNode(nodeNow.getState());

				for(String node : nodeNextList) {

					if(visitSet.contains(node)) {
						continue;
						
					} else {
						
						visitSet.add(node); //add the node to the visitSet

						Node subNode = new Node(node); //create subNode with node
						
						nodeNow.addSubNode(subNode);  //the present node added subNode
						
						subNode.setTopNode(nodeNow);  //set the present node to head of subNode

						//* whenever node expands, calculate cost from the present to sub-node
						subNode.setTotalCost(nodeNow.getTotalCost() + getNumberFromChar(subNode));

						priorityQnode.add(subNode); //put subNode to the priorityQnode

					}
				}
				nodeNow = priorityQnode.poll(); //remove the first element from the priorityQnode and set the present node 
				poppedNodes += 1;

			}


		}
		DisplayResult.displayResult(nodeNow, startState, poppedNodes, maxSizeQueue);

	}

	/* 
	 * set a queue size with max number
	 * 
	 * */

	public void setMaxQSize(int maxSizeQueue) {
		this.maxSizeQueue = Math.max(this.maxSizeQueue, maxSizeQueue);
	}

	/*
	 * To get the cost (find a '0' index from topNode and to find a number of sub-node from same index
	 * Change from char to int 
	 * */

	public int getNumberFromChar(Node subNode) {

		int indexTopNode = subNode.getTopNode().getState().indexOf('0');
		char ch = subNode.getState().charAt(indexTopNode);
		int numberChar = Integer.parseInt(String.valueOf(ch));

		return numberChar;
	}
}

/**
 * Creates a comparator to compare to the cost of path
 * 
 * if Node x > Node y, return 1
 * if Node x = Node y, return 0
 * if Node x < Node y, return -1
 * 
 */

class CostCompare implements Comparator<Node> {

	@Override
	public int compare(Node x, Node y) {

		Integer x1 = Integer.valueOf(x.getTotalCost());
		Integer y1 = Integer.valueOf(y.getTotalCost());

		int result = x1.compareTo(y1);
		
		return result;
	}

}
