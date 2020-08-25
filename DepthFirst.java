
import java.util.*;


/**
 * Depth First Search checks only the node connected to the head. 
 * 
 * It uses the 'Last In First Out'(LIFO)
 * 
 */

public class DepthFirst {

	final static String EASY = "134862705";
	final static String MEDIUM = "281043765";
	final static String HARD = "567408321";
	final static String GOAL = "123804765";

	public String startState;
	public int maxSizeQueue;
	boolean isGoal = false;

	
	public void depthFirstSearch(String level) {

		startState = level;

		//create a set to save visited nodes(record history)
		Set<String> visitSet = new HashSet<>(); //get rid of duplicates

		int poppedNodes = 0;

		Node nodeNow = new Node(startState);

		Stack<Node> nodeStack = new Stack<>(); //create stack to store node

		//* ArrayDeque is used as a stack and working toward to be faster than Stack 
		ArrayDeque<Node> nextNodeQueue = new ArrayDeque<>(); //create ArrayDeque to store the sub node(temporary)


		boolean isGoal = false;

		while(!isGoal) {

			String nodeNowState = nodeNow.getState();

			if(nodeNowState.equals(GOAL)) {
				isGoal = true;

			} else {

				setMaxQSize(nodeStack.size());

				visitSet.add(nodeNow.getState());
				System.out.println("visitSet : "+visitSet);

				//find indexOf("0") and add movement options(create and add subNode) to the List, right order	
				List<String> nodeNextList = NodeNext.getNextNode(nodeNow.getState());

				for(String node : nodeNextList) { //for loop the list of node(nodeNextList)

					if(visitSet.contains(node)) { //if the node already in visitSet, skip to add(check duplicate)
						continue;		        

					} else {

						visitSet.add(node);  //add the node to the visitSet

						Node subNode = new Node(node); //create subNode with node

						nodeNow.addSubNode(subNode);   //the present node added subNode

						subNode.setTopNode(nodeNow);   //set the present node to head of subNode

						nextNodeQueue.push(subNode);   // put subNode to the nextNodeQueue(reverse order compare to nodeNextList)
						
					}

				}

				nodeStack.addAll(nextNodeQueue); 

				nextNodeQueue.clear();  //nextNodeQueue is inserted into nodeStack
			
				nodeNow = nodeStack.pop(); // Remove the last element from the nodeStack  and set the present node

				poppedNodes += 1;
				
				nodeNextList.clear(); //to make empty nodeNextList

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
