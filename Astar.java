
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * A* : F(n) = G(n) + H(n)
 * 
 * G(n): Cost of getting to n
 *  
 * H1 = number of tiles that are not in the correct place  
 * H2 = total value of Manhattan distances(all tiles and their correct position, not include zero tile) 
 * H3 = H3 = total value of (manhattanDistance * cost)
 * 
 * A* algorithms are faster than BFS because it skips the expanding paths which are expensive. 
 *
 */

public class Astar {

	final static String EASY = "134862705";
	final static String MEDIUM = "281043765";
	final static String HARD = "567408321";
	final static String GOAL = "123804765";

	public String startState;
	public int maxSizeQueue;

	public void aStar(String level, String heuristic) {

		startState = level;

		Set<String> visitSet = new HashSet<>();

		int poppedNodes = 0;
		
		int totalCost = 0; //create variable to save total cost of movement, initial = 0

		Node nodeNow = new Node(startState);

		nodeNow.setCost(totalCost);

		CostCompare costCompare = new CostCompare();

		PriorityQueue<Node> priorityQnode = new PriorityQueue<>(11, costCompare);

		while(!nodeNow.getState().equals(GOAL)) {

			setMaxQSize(priorityQnode.size());

			visitSet.add(nodeNow.getState()); 
			
			List<String> nodeNextList = NodeNext.getNextNode(nodeNow.getState());

			for(String node : nodeNextList) {
				
				if(visitSet.contains(node))
					continue;
				
				visitSet.add(node);

				Node subNode = new Node(node);  //create subNode with node
				
				nodeNow.addSubNode(subNode);   //the present node added subNode
				
				subNode.setTopNode(nodeNow);   //set the present node to head of subNode

				// * go to an option among h1, h2 and h3 and get a value of cost
				getCostfromSelection(subNode, nodeNow, heuristic);

				priorityQnode.add(subNode);				

			}

			nodeNow = priorityQnode.poll();
			poppedNodes += 1; 		

		}

		DisplayResult.displayResult(nodeNow, startState, poppedNodes, maxSizeQueue);


	}

	/* 
	 * set a queue size with max number
	 * 
	 */
	public void setMaxQSize(int maxSizeQueue) {
		this.maxSizeQueue = Math.max(this.maxSizeQueue, maxSizeQueue);
	}


	/*
	 * To get the cost (find a '0' index from topNode and to find a number of sub-node from same index
	 * Change from char to int 
	 */
	public int getNumberFromChar(Node subNode) {

		int indexTopNode = subNode.getTopNode().getState().indexOf('0');
		char ch = subNode.getState().charAt(indexTopNode);
		int numberChar = Integer.parseInt(String.valueOf(ch));

		return numberChar;
	}

	/* 
	 * get and set a cost from the different heuristic that you choose
	 * 
	 */
	public void getCostfromSelection(Node subNode, Node nodeNow, String heuristic) {

		switch(heuristic) {

		case "h1": 
			subNode.setTotalCost(nodeNow.getTotalCost()
					+ getNumberFromChar(subNode), heuristic1(subNode.getState()));
			          //F(n) = G(n) + H(n)
			break;

		case "h2": 
			subNode.setTotalCost(nodeNow.getTotalCost()
					+ getNumberFromChar(subNode), heuristic2(subNode.getState()));
			break;

		case "h3":
			subNode.setTotalCost(nodeNow.getTotalCost()
					+ getNumberFromChar(subNode), heuristic3(subNode.getState()));
			break;

		}
	}


	/* 
	 * H1 = number of tiles that are not in the correct place 
	 */
	
	public static int heuristic1(String stateNow) {

		int H = 0;
		for(int h = 0; h < stateNow.length(); h++ ) { //iterate through the each tile 
			if(stateNow.charAt(h) != GOAL.charAt(h)) { //find the tile which isn't in the correct location 
				H++;
			}		
		}

		return H;
	}
	
	/*
	 * H2 = total value of Manhattan distances(all tiles and their correct position, not include zero tile) 
	 * 
	 * For example: startState: "540618732" >> GOAL: "123804765"
	 * 
	 * 3-rows, 3-columns
	 * 
	 * tile 5: (h=0),(j=8) 
	 * Math.abs(0%3-8%3) + Math.abs(0/3-8/3) = |0-2|+|0-2| = 4
	 * 
	 * tile 2: (h=8),(j=1)
	 * Math.abs(8%3-1%3) + Math.abs(8/3-1/3) = |2-1|+|2-0| = 1+2=3	
	*/
	
	public static int heuristic2(String stateNow) {

		int manhattanDistance = 0;
		for(int h = 0; h < stateNow.length(); h++) { //iterate through the present node's state
			for(int k = 0; k < GOAL.length(); k++) { //iterate through goalState 
				
				char ch = stateNow.charAt(h);

				if(ch == GOAL.charAt(k)) { //check if it's correct position
					
					if(ch == '0') //skip the zero tile
						continue;
					
					manhattanDistance += (Math.abs(h % 3 - k % 3)+ Math.abs(h / 3 - k / 3));

				}
			}
		}
		return manhattanDistance;
	}
	

	/*
	 * H3 = total value of (manhattanDistance * cost)
	 */

	public static int heuristic3(String stateNow) {

		int H3 = 0;
		int manhattanDistance = 0;
		for(int h = 0; h < stateNow.length(); h ++ ) { //iterate through the present node's state
			for(int k = 0; k < GOAL.length(); k++) { //iterate through goalState 

				char ch = stateNow.charAt(h);
				
				if(ch == GOAL.charAt(k)) { //check if it's correct position

					if(ch == '0') //skip the zero tile
						continue;
					
					manhattanDistance += (Math.abs(h % 3 - k % 3)+ Math.abs(h / 3 - k / 3));
					
					H3 = manhattanDistance * h; //multiply with value of tile
				}
			}
		}
		
		return H3;

	}
	


}
