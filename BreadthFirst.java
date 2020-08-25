

import java.util.*;


/**
 * 
 * This Breadth First Search checks all connected nodes from the head until it finds a goal.
 * Add sub-nodes and then poll() the first element in Queue. (First In Last Out(FIFO) 
 * Using a hashSet to save a node that already checked, 
 * we set a current node with startState and create Queue with this node.
 * And then, we figure out sub-node depend on option that we have.
 * We check whether node visited or not and if it didn't visit, add to the hashSet.
 * Remove head of queue and check whether current node is goal or not.
 *
 */



public class BreadthFirst {

	final static String EASY = "134862705";
	final static String MEDIUM = "281043765";
	final static String HARD = "567408321";
	final static String GOAL = "123804765";

	public String startState;
	public int maxSizeQueue;
	boolean isGoal = false;

	public void breadthFirstSearch(String level) {

		startState = level;
		
		//create a set to save visited nodes(record history), get rid of duplicates
		Set<String> visitSet = new HashSet<>(); //get rid of duplicates

		int poppedNodes = 0; //to count the number of nodes popped off

		Node nodeNow = new Node(startState); //create node with initial string

		Queue<Node> queueNode = new LinkedList<>(); //create queue with linkedList to track sub node

		while(!isGoal) { //if the node is goal, escape the while loop
			
			String nodeNowState = nodeNow.getState();

			if(nodeNowState.equals(GOAL)) {
				isGoal = true;
				
			} else {

				setMaxQSize(queueNode.size()); //to set the max size of queue
				visitSet.add(nodeNowState); //add the nodes to the visitSet
				
				//find indexOf('0') and add move options(create and add subNode) to the List	
				List<String> nodeNextList = NodeNext.getNextNode(nodeNowState);

				for(String node : nodeNextList) { //for loop the list of node(nodeNextList)

					if(visitSet.contains(node)) { //if the node is already in visitSet, skip to add(check duplicate)
						continue;
						
					} else {

						visitSet.add(node); //add the node to the visitSet

						Node subNode = new Node(node); //create subNode with node

						nodeNow.addSubNode(subNode);   //the present node added subNode

						subNode.setTopNode(nodeNow);   //set the present node to the head of subNode

						queueNode.add(subNode);        //put subNode to the queueNode

					}

				}

				nodeNow = queueNode.poll(); //remove the first element from the queueNode and set the present node 

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



}
