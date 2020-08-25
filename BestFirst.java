

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * H(n) = number of tiles are not in right position
 * It finds a goal very fast but it cannot find an optimal solution.
 * 
 * F(n) = H(n)  (in this case, G(n):sum of the move cost = 0)
 *
 */


public class BestFirst {

	final static String EASY = "134862705";
	final static String MEDIUM = "281043765";
	final static String HARD = "567408321";
	final static String GOAL = "123804765";

	public String startState;
	public int maxSizeQueue;
	boolean isGoal = false;

	public void BestFirstSearch(String level) {

		startState = level;

		//create a set to save visited nodes(record history), get rid of duplicates
		Set<String> visitSet = new HashSet<>(); //get rid of duplicates

		int poppedNodes = 0;

		Node nodeNow = new Node(startState); //create node with initial string

		nodeNow.setCost(0); //initial cost = 0

		CostCompare costCompare = new CostCompare(); //compare to the cost of path

		/* create a priorityQueue with initial capacity and also orders elements
		   according to the costCompare comparator (less cost node is going to be the first element) */
		PriorityQueue<Node> priorityQnode = new PriorityQueue<>(11, costCompare);

		while(!isGoal) {

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
						
						nodeNow.addSubNode(subNode);   //the present node added subNode
						
						subNode.setTopNode(nodeNow);   //set the present node to head of subNode

						/*  In this algorithm, total cost = 0(G(n) = 0)  */
						subNode.setTotalCost(0, Astar.heuristic1(subNode.getState()));
						
						priorityQnode.add(subNode);  //put subNode to the priorityQnode
					}
				}
				nodeNow = priorityQnode.poll(); //remove the first element from the priorityQnode and set the present node 
				poppedNodes += 1;

			}
		}

		DisplayResult.displayResult(nodeNow, startState, poppedNodes, maxSizeQueue);

	}

	public void setMaxQSize(int maxSizeQueue) {
		this.maxSizeQueue = Math.max(this.maxSizeQueue, maxSizeQueue);
	}




}
