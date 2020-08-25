

import java.util.ArrayList;



/**
 * Show how to build a node, using ArrayList for sub nodes.
 * I generated getter and setter to set and get the value for a node.
 *
 */

public class Node {
	
	
	public Node topNode;
	
	public ArrayList<Node> subNodes;
	
	public String state;
	
	public int depth;
	
	public int cost;
	
	public int totalCost;
	
	final static String UP = "UP";
	final static String DOWN = "DOWN";
	final static String LEFT = "LEFT";
	final static String RIGHT = "RIGHT";
	
	public Node(String state) {
		super();
		this.state = state;
		subNodes = new ArrayList<Node>();
	}

	//head
	public Node getTopNode() {
		return topNode;
	}
	
	//set present node to head node
	public void setTopNode(Node topNode) {
		this.topNode = topNode;
	}

	//tail(ArrayList)
	public ArrayList<Node> getSubNode() {
		return subNodes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	//IDS
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	//get a value of the cost
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	//for uniform cost search 
	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	//create to make a sub-node' list
	public void addSubNode(Node subNode) {
		subNodes.add(subNode);
	}
	
	//for A* search
	public void setTotalCost(int totalCost, int predict) {
		this.totalCost = totalCost + predict;
	}

	@Override //check the state of node easily for debugging
	public String toString() {
		return "Node [state=" + state + "]";
	}
	
	
	
}





