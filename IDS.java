
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * Using DFS algorithm with depth-limit to avoid further below.
 * 
 * It saves space better than DFS.
 * 
 *
 */

public class IDS {

	final static String EASY = "134862705";
	final static String MEDIUM = "281043765";
	final static String HARD = "567408321";
	final static String GOAL = "123804765";

	public String startState;
	public int maxSizeQueue;
	boolean isGoal = false;

	public void iterativeDeepening(String level, int depth) {

		startState = level;

		Set<String> visitSet = new HashSet<>(); //to save visited nodes(record history), get rid of duplicates

		int poppedNodes = 0;

		Node nodeNow = new Node(startState);

		for(int limit = 1; limit < depth; limit++) { //* looping until depth-limit

			visitSet.clear();
			
			Node nodeTemp = new Node(startState);

			Stack<Node> nodeStack = new Stack<>(); 

			ArrayDeque<Node> nextNodeQueue = new ArrayDeque<>(); //create ArrayDeque to store the sub node(temporary)

			nodeStack.push(nodeTemp);

			nodeNow = nodeTemp;

			visitSet.add(nodeNow.getState());


			while(!nodeStack.isEmpty()) {
				
				setMaxQSize(nodeStack.size()); //space calculation

				nodeNow = nodeStack.pop(); //* Remove the last element from the nodeStack  and set the present node
				
				poppedNodes += 1;

				if(nodeNow.getState().equals(GOAL)) {
					isGoal = true;
					break;
					
				} else {
					isGoal = false;
				}

				if(nodeNow.getDepth() < limit) { // * not going to deep down beyond the depth-limit
					
					List<String> nodeNextList = NodeNext.getNextNode(nodeNow.getState());

					for(String node : nodeNextList) {
						
						if(visitSet.contains(node)) {
							continue;

						} else {
							
							visitSet.add(node);

							Node subNode = new Node(node); //create subNode with node

							nodeNow.addSubNode(subNode);  //the present node added subNode

							subNode.setTopNode(nodeNow);  // set the present node to head of subNode
							
							nextNodeQueue.push(subNode);  // put subNode to the nextNodeQueue

							subNode.setDepth(nodeNow.getDepth()+1); // * set the depth, go to the next depth
							
						}	

					}
					
					nodeStack.addAll(nextNodeQueue); //nextNodeStack is inserted into nodeStack
					nextNodeQueue.clear(); 
				}
			}

			if(isGoal) break;
		} 

		if(!isGoal) System.out.println("Can't solve the problem.");
		else DisplayResult.displayResult(nodeNow, startState, poppedNodes, maxSizeQueue);

	}
	
	/* 
	 * set a queue size with max number
	 * 
	 * */

	public void setMaxQSize(int maxSizeQueue) {
		this.maxSizeQueue = Math.max(this.maxSizeQueue, maxSizeQueue);
	}


}
