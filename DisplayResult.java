

import java.util.*;

/**
 * 
 * Display and organize the result
 *
 */


public class DisplayResult {


	final static String UP = "UP";
	final static String DOWN = "DOWN";
	final static String LEFT = "LEFT";
	final static String RIGHT = "RIGHT";

	public static void displayResult(Node solutionNode, String startState,int poppedNodes,long maxSizeQueue) {

		int totalCost = 0;

		Stack<Node> processStack = new Stack<>(); //create a stack to store the result of the process

		processStack.push(solutionNode); //LIFO: last in first out(the solution will be shown last)

		while(!solutionNode.getState().equals(startState)) {
			processStack.push(solutionNode.getTopNode());
			solutionNode = solutionNode.getTopNode(); //until from solution to solution
		}

		String headState = startState;
		
		String tailState;
		
		int cost = 0; 

		for(int i = processStack.size()-1; i >= 0; i--) { //iterate through the stack

			tailState = processStack.get(i).getState();

			if(!headState.equals(tailState)) {

				String move = null; 

				//get the difference between the states(index of zero)
				int moveZero = tailState.indexOf('0') - headState.indexOf('0');
				

				//to find the direction of movement   
				if(moveZero == 1) {
					move = Node.RIGHT;

				} else if(moveZero == -1) {
					move = Node.LEFT;

				} else if(moveZero == 3) {
					move = Node.DOWN;

				} else if(moveZero == -3) {
					move = Node.UP;	
				}

				int index = headState.indexOf('0');

				System.out.println(move + " " + tailState.charAt(index));
			
				char ch = tailState.charAt(index);
				cost = Integer.parseInt(String.valueOf(ch)); //get the number from char

				totalCost += cost;
			}

			headState = tailState;
			
			//cost of each step
			System.out.println("cost: "+ cost +" >>>> "+ processStack.get(i).getState());
			System.out.println("-------------------------------------------------------------");

		}
		//overall 
		System.out.println("Length : length of the solution path = "+(processStack.size()-1));
		System.out.println("Cost : cost of the solution path = "+ totalCost);
		System.out.println("Time : number of nodes popped off the queue = "+ poppedNodes);
		System.out.println("Space : size of the queue at its max = "+ maxSizeQueue);

	}
	/*
	 * To get the cost (find a '0' index from topNode and to find a number of sub-node from same index)
	 * Change from char to int 
	 */
	public int getNumberFromChar(Node subNode) {

		int indexTopNode = subNode.getTopNode().getState().indexOf('0');
		char ch = subNode.getState().charAt(indexTopNode);
		int numberChar = Integer.parseInt(String.valueOf(ch));

		return numberChar;
	}


}
