
import java.util.*;

/**
 * find the next node(=successor)
 * 
 * example) "134862705"
 *    -------------       -------------------------------
 *    | 1 | 3 | 4 |       | index:0 | index:1 | index:2 |        
 *    | 8 | 6 | 2 |       | index:3 | index:4 | index:5 |
 *    | 7 | 0 | 5 |       | index:6 | index:7 | index:8 |
 *    -------------       ------------------------------- 
 *    
 * index of "0" = 7, go to the option 7, there are 3 options.
 * The 3 options >> LEFT, UP, RIGHT.
 * 
 * LEFT -> "134862075"
 * UP -> "134802675"
 * RIGHT -> "134862570"
 * 
 * I chose the order 'UP, LEFT, RIGHT, DOWN'.
 *
 */

public class NodeNext {
	
	
	public static List<String> getNextNode(String state) {
		
		List<String> nextN = new ArrayList<>(); //create a list to store nodes
		
		char x = 'x';
		char a0 = state.charAt(0);
		char a1 = state.charAt(1);
		char a2 = state.charAt(2);
		char a3 = state.charAt(3);
		char a4 = state.charAt(4);
		char a5 = state.charAt(5);
		char a6 = state.charAt(6);
		char a7 = state.charAt(7);
		char a8 = state.charAt(8);
		
		int location = state.indexOf('0'); // S: example. "134862705", (indexOf('0') = 7)
		
		if(location == 0) { // S: OPTION: RIGHT, DOWN
			
			nextN.add(switchKeys(state, a0, a1, x));
			//System.out.println(nextN);
			
			nextN.add(switchKeys(state, a0, a3, x));
			//System.out.println(nextN);
			
			
		} else if(location == 1) { //  3 OPTION: LEFT, RIGHT, DOWN
			
			nextN.add(switchKeys(state, a1, a0, x));
			
			nextN.add(switchKeys(state, a1, a2, x));
			
			nextN.add(switchKeys(state, a1, a4, x));
			
		} else if(location == 2) { // 2 OPTION: LEFT, DOWN
			
			nextN.add(switchKeys(state, a2, a1, x));
			
			nextN.add(switchKeys(state, a2, a5, x));
			
		} else if(location == 3) { // 2 OPTION: UP, RIGHT, DOWN
			
			nextN.add(switchKeys(state, a3, a0, x));
			
			nextN.add(switchKeys(state, a3, a4, x));
			
			nextN.add(switchKeys(state, a3, a6, x));
			
		} else if(location == 4) { // 3 OPTION: UP, LEFT, RIGHT, DOWN
			
			nextN.add(switchKeys(state, a4, a1, x));
			
			nextN.add(switchKeys(state, a4, a3, x));
			
			nextN.add(switchKeys(state, a4, a5, x));
			
			nextN.add(switchKeys(state, a4, a7, x));
			
			
		} else if(location == 5) { // 3 OPTION: UP, LEFT, DOWN
			
			nextN.add(switchKeys(state, a5, a2, x));
			
			nextN.add(switchKeys(state, a5, a4, x));
			
			nextN.add(switchKeys(state, a5, a8, x));
			
		} else if(location == 6) { // 2 OPTION: UP, RIGHT
			
			nextN.add(switchKeys(state, a6, a3, x));
			
			nextN.add(switchKeys(state, a6, a7, x));
			
			
		} else if(location == 7) { // 3 OPTION: UP, LEFT, RIGHT
			
			nextN.add(switchKeys(state, a7, a4, x));
			
			nextN.add(switchKeys(state, a7, a6, x));
			
			nextN.add(switchKeys(state, a7, a8, x));
			
			
		} else if(location == 8) { // 2 OPTION: UP, LEFT
			
			nextN.add(switchKeys(state, a8, a5, x));
			
			nextN.add(switchKeys(state, a8, a7, x));
			
		}
				
		return nextN;
		
	
	}
	
	//switch the number (a <-> b) using temporary value x 
	public static String switchKeys(String str, char a, char b, char x) {
		
		str = str.replace(a, x);
		str = str.replace(b, a);
		str = str.replace(x, b);
		
		return str;
		
		
	}

}
